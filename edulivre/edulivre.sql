DROP DATABASE IF EXISTS edulivre;
CREATE DATABASE edulivre;

\c edulivre;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    senha VARCHAR(200) NOT NULL,
    perfil TEXT NOT NULL CHECK(perfil IN ('aluno', 'professor', 'admin'))
);

INSERT INTO usuario (id, nome, email, senha, perfil) VALUES
(uuid_generate_v4(), 'Alice Oliveira', 'alice@example.com', 'senha123', 'aluno'),
(uuid_generate_v4(), 'Bruno Silva', 'bruno@example.com', 'seguro456', 'professor'),
(uuid_generate_v4(), 'Carlos Lima', 'carlos@example.com', 'admin789', 'admin');


CREATE TABLE curso (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    titulo VARCHAR(200) NOT NULL UNIQUE,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    avaliacao JSONB
);

INSERT INTO curso (id, titulo, descricao, avaliacao) VALUES
(uuid_generate_v4(), 'Java Básico', 'Curso introdutório de Java para iniciantes.',
'{
  "media": 4.5,
  "comentarios": [
    {
      "usuario_id": "550f733c-9f9c-41c7-b269-6182cacecf1d",
      "nota": 5,
      "comentario": "Excelente curso!",
      "data": "2025-05-20"
    },
    {
      "usuario_id": "dc1a3c83-15b0-4a39-99f9-bb6d0b6157f5",
      "nota": 4,
      "comentario": "Gostei, mas poderia ter mais exemplos.",
      "data": "2025-05-21"
    }
  ]
}'),

(uuid_generate_v4(), 'Banco de Dados PostgreSQL', 'Aprenda a modelar e consultar dados com PostgreSQL.',
'{
  "media": 4.0,
  "comentarios": []
}');


CREATE TABLE matricula (
    id SERIAL PRIMARY KEY, 
    usuario_id UUID NOT NULL,
    curso_id UUID NOT NULL,
    data_matricula DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

INSERT INTO matricula (usuario_id, curso_id) VALUES
(
    (SELECT id FROM usuario WHERE email = 'alice@example.com'),
    (SELECT id FROM curso WHERE titulo = 'Java Básico')
),
(
    (SELECT id FROM usuario WHERE email = 'alice@example.com'),
    (SELECT id FROM curso WHERE titulo = 'Banco de Dados PostgreSQL')
),
(
    (SELECT id FROM usuario WHERE email = 'bruno@example.com'),
    (SELECT id FROM curso WHERE titulo = 'Banco de Dados PostgreSQL')
);


CREATE TABLE conteudo (
    id SERIAL PRIMARY KEY,
    curso_id UUID NOT NULL,
    titulo VARCHAR(200) NOT NULL UNIQUE,
    descricao TEXT,
    tipo TEXT NOT NULL CHECK(tipo IN ('video', 'pdf', 'imagem', 'audio', 'quiz', 'slide')),
    arquivo BYTEA NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

INSERT INTO conteudo (curso_id, titulo, descricao, tipo, arquivo) VALUES
(
    (SELECT id FROM curso WHERE titulo = 'Java Básico'),
    'Introdução ao Java',
    'Vídeo explicando o básico do Java.',
    'video',
    decode('ffd8ffe000104a46494600010101006000600000ffe1', 'hex') 
),
(
    (SELECT id FROM curso WHERE titulo = 'Java Básico'),
    'Material PDF - Java',
    'Arquivo PDF com material complementar.',
    'pdf',
    decode('255044462d312e350a25d0d4c5d80a34', 'hex') 
),
(
    (SELECT id FROM curso WHERE titulo = 'Banco de Dados PostgreSQL'),
    'Slide: Introdução ao PostgreSQL',
    'Apresentação em slides com conceitos fundamentais.',
    'slide',
    decode('504b030414000600080000002100', 'hex') 
);

SELECT curso.titulo, curso.avaliacao->'media' as media, count(matricula.curso_id) 
FROM curso INNER JOIN matricula ON (curso.id = matricula.curso_id)
GROUP BY curso.titulo, curso.avaliacao;