package apresentacao;

import java.sql.SQLException;
import java.util.UUID;

import negocio.Usuario;
import persistencia.ConteudoDAO;
import persistencia.CursoDAO;
import persistencia.MatriculaDAO;
import persistencia.UsuarioDAO;

public class Main {
    public static void main(String[] args) {
        
        try {
            System.out.println(new CursoDAO().listarResumoCursos());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(new ConteudoDAO().buscarConteudosPorCurso("Banco de Dados PostgreSQL"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Usuario u = new UsuarioDAO().buscarUsuarioPorEmail("carlos@example.com");
        System.out.println(u);
        try {
            System.out.println(new MatriculaDAO().inserirMatricula(u.getId(), new CursoDAO().obterCursoIdPeloTitulo("Java Basico")));            
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UUID cursoId = new CursoDAO().obterCursoIdPeloTitulo("Java Basico");
            UUID usuarioId = new UsuarioDAO().buscarUsuarioPorEmail("alice@example.com").getId();

            new CursoDAO().adicionarComentarioCurso(
                cursoId,
                usuarioId,
                5,
                "Curso excelente, muito claro!",
                "2025-07-08"
            );

            System.out.println("Comentário adicionado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}