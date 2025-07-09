package persistencia;

import java.sql.*;
import java.util.UUID;

public class MatriculaDAO {
    
    public String inserirMatricula(UUID usuarioId, UUID cursoId) throws Exception {
        String checkSql = "SELECT COUNT(*) FROM matricula WHERE usuario_id = ? AND curso_id = ?";
        
        try (Connection con = new ConexaoPostgreSQL().getConnection()) {
            try (PreparedStatement psCheck = con.prepareStatement(checkSql)) {
                psCheck.setObject(1, usuarioId);
                psCheck.setObject(2, cursoId);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new Exception("Validação falhou: Usuário já está matriculado neste curso.");
                }
            }

            String insertSql = "INSERT INTO matricula (usuario_id, curso_id) VALUES (?, ?)";
            try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setObject(1, usuarioId);
                psInsert.setObject(2, cursoId);
                
                int rowsAffected = psInsert.executeUpdate();
                if (rowsAffected > 0) {
                    return "Matrícula realizada com sucesso!";
                } else {
                    throw new SQLException("A inserção falhou, nenhuma linha foi afetada.");
                }
            }
        }

    }
}