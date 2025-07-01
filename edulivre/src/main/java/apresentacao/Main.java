package apresentacao;

import java.sql.Connection;
import java.sql.SQLException;

import persistencia.ConexaoPostgreSQL;
import persistencia.CursoDAO;

public class Main {
    public static void main(String[] args) {
        
        try {
            System.out.println(new CursoDAO().listarResumoCursos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}