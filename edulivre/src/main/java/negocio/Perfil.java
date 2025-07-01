package negocio;

public enum Perfil {
    ALUNO, PROFESSOR, ADMIN;

    public static Perfil fromString(String value) {
        return Perfil.valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
