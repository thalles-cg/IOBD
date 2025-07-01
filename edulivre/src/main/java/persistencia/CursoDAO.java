package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import negocio.CursoResumo;

public class CursoDAO {
   
    public List<CursoResumo> listarResumoCursos() throws SQLException {
        List<CursoResumo> lista = new ArrayList<>();

        String sql = """
            SELECT curso.titulo, curso.avaliacao->>'media' AS media, COUNT(matricula.curso_id) AS total_matriculados
            FROM curso
            INNER JOIN matricula ON curso.id = matricula.curso_id
            GROUP BY curso.titulo, curso.avaliacao;
        """;

        Connection con = new ConexaoPostgreSQL().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                double media = Double.parseDouble(rs.getString("media"));
                int total = rs.getInt("total_matriculados");

                CursoResumo resumo = new CursoResumo(titulo, media, total);
                lista.add(resumo);
            }
        }

        return lista;
    }
}
