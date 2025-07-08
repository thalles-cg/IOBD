package apresentacao;

import java.sql.Connection;
import java.sql.SQLException;

import negocio.Usuario;
import persistencia.ConexaoPostgreSQL;
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
    }
}