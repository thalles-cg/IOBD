package negocio;

public class Conteudo {
    private int id;
    private Curso curso;
    private String titulo;
    private String descricao;
    private TipoConteudo tipoConteudo;
    private byte[] arquivo;

    public Conteudo(int id, Curso curso, String titulo, String descricao, TipoConteudo tipoConteudo, byte[] arquivo) {
        this.id = id;
        this.curso = curso;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoConteudo = tipoConteudo;
        this.arquivo = arquivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoConteudo getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(TipoConteudo tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
    
}
