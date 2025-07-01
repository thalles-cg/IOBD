package persistencia;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Conteudo;
import negocio.TipoConteudo;

public class ConteudoDAO {
    
    public List<Conteudo> buscarConteudosPorCurso(String tituloCurso) throws SQLException {
    List<Conteudo> conteudos = new ArrayList<>();
    String sql = """
        SELECT c.id, c.titulo, c.descricao, c.tipo, octet_length(c.arquivo) AS tamanho
        FROM conteudo c
        INNER JOIN curso ON (c.curso_id = curso.id)
        WHERE curso.titulo ILIKE ?
    """;
    Connection con = new ConexaoPostgreSQL().getConnection();

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, tituloCurso);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Conteudo conteudo = new Conteudo(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    TipoConteudo.valueOf(rs.getString("tipo").toUpperCase()),
                    null 
                );
                System.out.println("Tipo: " + conteudo.getTipoConteudo() + " | Tamanho: " + rs.getInt("tamanho") + " bytes");
                conteudos.add(conteudo);
            }
        }
    }
    return conteudos;
}

}
