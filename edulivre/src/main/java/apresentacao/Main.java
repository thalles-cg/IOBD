package apresentacao;

import java.sql.Connection;

import persistencia.ConexaoPostgreSQL;

public class Main {
    public static void main(String[] args) {
        
        Connection con = new ConexaoPostgreSQL().getConnection();
    }
}