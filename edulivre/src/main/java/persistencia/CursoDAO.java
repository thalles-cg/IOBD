package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import negocio.CursoResumo;

public class CursoDAO {
   
    public List<CursoResumo> listarResumoCursos() throws SQLException {
        List<CursoResumo> lista = new ArrayList<>();

        String sql = """
            SELECT 
                c.titulo, 
                COALESCE((c.avaliacao->>'media')::float, 0) AS media, 
                COUNT(m.curso_id) AS total_matriculados
            FROM 
                curso c
            LEFT JOIN 
                matricula m ON c.id = m.curso_id
            GROUP BY 
                c.id, c.titulo, c.avaliacao;
        """;

        try (Connection con = new ConexaoPostgreSQL().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                double media = rs.getDouble("media");
                int total = rs.getInt("total_matriculados");

                CursoResumo resumo = new CursoResumo(titulo, media, total);
                lista.add(resumo);
            }
        }
        return lista;
    }

    public UUID obterCursoIdPeloTitulo(String titulo) {
        UUID cursoId = null;
        String sql = "SELECT id FROM curso WHERE titulo ILIKE ?"; 

        try (Connection con = new ConexaoPostgreSQL().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + titulo + "%"); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cursoId = rs.getObject("id", UUID.class);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursoId;
    }

    public void adicionarComentarioCurso(UUID cursoId, UUID usuarioId, int nota, String comentario, String data) throws SQLException {
        String sql = """
            UPDATE curso
            SET avaliacao = jsonb_set(
                avaliacao,
                '{comentarios}',
                (
                    (avaliacao->'comentarios')::jsonb || 
                    jsonb_build_object(
                        'usuario_id', ?,
                        'nota', ?,
                        'comentario', ?,
                        'data', ?
                    )
                )
            )
            WHERE id = ?
        """;

        try (Connection con = new ConexaoPostgreSQL().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, usuarioId);
            ps.setInt(2, nota);
            ps.setString(3, comentario);
            ps.setString(4, data);
            ps.setObject(5, cursoId);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum curso encontrado com o ID fornecido.");
            }
        }
    }
}
