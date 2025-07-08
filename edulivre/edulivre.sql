DROP DATABASE IF EXISTS edulivre;
CREATE DATABASE edulivre;

\c edulivre; 

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    senha VARCHAR(200) NOT NULL,
    perfil TEXT NOT NULL CHECK(perfil IN ('aluno', 'professor', 'admin'))
);

INSERT INTO usuario (id, nome, email, senha, perfil) VALUES
('11111111-1111-1111-1111-111111111111', 'Alice Oliveira', 'alice@example.com', 'senha123', 'aluno'),
('22222222-2222-2222-2222-222222222222', 'Bruno Silva', 'bruno@example.com', 'seguro456', 'professor'),
('33333333-3333-3333-3333-333333333333', 'Carlos Lima', 'carlos@example.com', 'admin789', 'admin');

CREATE TABLE curso (
    id UUID PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL UNIQUE,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    avaliacao JSONB
);

INSERT INTO curso (id, titulo, descricao, avaliacao) VALUES
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Java Basico', 'Curso introdutório de Java para iniciantes.',
'{
  "media": 4.5,
  "comentarios": [
    {
      "usuario_id": "11111111-1111-1111-1111-111111111111",
      "nota": 5,
      "comentario": "Excelente curso!",
      "data": "2025-05-20"
    },
    {
      "usuario_id": "22222222-2222-2222-2222-222222222222",
      "nota": 4,
      "comentario": "Gostei, mas poderia ter mais exemplos.",
      "data": "2025-05-21"
    }
  ]
}'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Banco de Dados PostgreSQL', 'Aprenda a modelar e consultar dados com PostgreSQL.',
'{
  "media": 4.0,
  "comentarios": []
}');

CREATE TABLE matricula (
    id SERIAL PRIMARY KEY, 
    usuario_id UUID NOT NULL,
    curso_id UUID NOT NULL,
    data_matricula DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

INSERT INTO matricula (usuario_id, curso_id) VALUES
('11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
('11111111-1111-1111-1111-111111111111', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
('22222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb');

CREATE TABLE conteudo (
    id SERIAL PRIMARY KEY,
    curso_id UUID NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT,
    tipo TEXT NOT NULL CHECK(tipo IN ('video', 'pdf', 'imagem', 'audio', 'quiz', 'slide')),
    arquivo BYTEA NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

INSERT INTO conteudo (curso_id, titulo, descricao, tipo, arquivo) VALUES
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Introdução ao Java', 'Vídeo explicando o básico do Java.', 'video',
 decode('ffd8ffe000104a46494600010101006000600000ffe1', 'hex')),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Material PDF - Java', 'Arquivo PDF com material complementar.', 'pdf',
 decode('255044462d312e350a25d0d4c5d80a34', 'hex')),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Slide: Introdução ao PostgreSQL', 'Apresentação em slides com conceitos fundamentais.', 'slide',
 decode('504b030414000600080000002100', 'hex'));

SELECT c.titulo, 
       COALESCE((c.avaliacao->>'media')::float, 0) AS media, 
       COUNT(m.id) AS total_matriculados
FROM curso c
LEFT JOIN matricula m ON c.id = m.curso_id
GROUP BY c.id, c.titulo, c.avaliacao;

SELECT c.titulo, 
       COALESCE((c.avaliacao->>'media')::float, 0) AS media, 
       COUNT(m.id) AS total_matriculados
FROM curso c
LEFT JOIN matricula m ON c.id = m.curso_id
WHERE c.titulo ILIKE 'Banco de Dados PostgreSQL'
GROUP BY c.id, c.titulo, c.avaliacao;
