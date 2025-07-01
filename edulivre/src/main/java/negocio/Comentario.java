package negocio;

import java.time.LocalDate;

public class Comentario {
    private Usuario usuario;
    private double nota;
    private String comentario;
    private LocalDate data;
    
    public Comentario(Usuario usuario, double nota, String comentario, LocalDate data) {
        this.usuario = usuario;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    
}
