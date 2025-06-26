package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private String host;
    private String user;
    private String password;
    private int port;
    private String database;

    public ConexaoPostgreSQL(){
        this.host = "localhost";
        this.user = "postgres";
        this.password = "123";
        this.port = 5432;
        this.database = "edulivre";
    }

    public Connection getConnection(){
        String url = "jdbc:postgresql://"+ this.host + ":"+this.port+"/"+this.database;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }
}
