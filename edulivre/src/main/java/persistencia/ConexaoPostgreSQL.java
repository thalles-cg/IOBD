package persistencia;

public class ConexaoPostgreSQL {
    private String host;
    private String user;
    private String password;
    private int port;
    private String dbname;

    public ConexaoPostgreSQL(){
        this.host = "localhost";
        this.user = "postgres";
        this.password = "123";
        this.port = 5432;
        this.dbname = "edulivre";
    }
}
