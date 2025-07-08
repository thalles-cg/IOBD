package negocio;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String password;
    private Perfil perfil;

    public Usuario(UUID id, String nome, String email, String password, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.perfil = perfil;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", password=" + password + ", perfil="
                + perfil + "]";
    }

    
}
