package negocio;

import java.util.ArrayList;

public class Avaliacao {
    private double media;
    private ArrayList<Comentario> comentarios;
    
    public Avaliacao(double media, ArrayList<Comentario> comentarios) {
        this.media = media;
        this.comentarios = comentarios;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    
}
