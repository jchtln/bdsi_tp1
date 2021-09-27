package modele;

public class Salle {

    private int idSalle;
    private String nomSalle;


    public Salle(int idSalle, String nomSalle) {
        this.idSalle = idSalle;
        this.nomSalle = nomSalle;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }
}
