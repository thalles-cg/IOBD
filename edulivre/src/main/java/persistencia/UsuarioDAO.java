package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import negocio.Perfil;
import negocio.Usuario;

public class UsuarioDAO {
    
    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = null; 
        String sql = "SELECT id, nome, email, senha, perfil FROM usuario WHERE email = ?";
        
        try (Connection con = new ConexaoPostgreSQL().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // MELHORIA: Peça o objeto diretamente ao driver JDBC. É mais seguro.
                    UUID id = rs.getObject("id", UUID.class); 
                    String nome = rs.getString("nome");
                    String emailDoBanco = rs.getString("email");
                    String senha = rs.getString("senha");
                    String perfil = rs.getString("perfil");

                    usuario = new Usuario(id, nome, emailDoBanco, senha, Perfil.fromString(perfil));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por email: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

}
