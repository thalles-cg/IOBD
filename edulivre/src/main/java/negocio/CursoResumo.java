package negocio;

public class CursoResumo {
    private String titulo;
    private double media;
    private int totalMatriculados;

    public CursoResumo(String titulo, double media, int totalMatriculados) {
        this.titulo = titulo;
        this.media = media;
        this.totalMatriculados = totalMatriculados;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getMedia() {
        return media;
    }

    public int getTotalMatriculados() {
        return totalMatriculados;
    }

    @Override
    public String toString() {
        return "CursoResumo [titulo=" + titulo + ", media=" + media + ", totalMatriculados=" + totalMatriculados + "]";
    }
}
