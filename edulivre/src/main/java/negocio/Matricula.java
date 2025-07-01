package negocio;

import java.time.LocalDate;

public class Matricula {
    private int id;
    private Usuario usuario;
    private Curso curso;
    private LocalDate dataMatricula;
    
    public Matricula(int id, Usuario usuario, Curso curso, LocalDate dataMatricula) {
        this.id = id;
        this.usuario = usuario;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    
}
