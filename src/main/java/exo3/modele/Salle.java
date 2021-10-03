package exo3.modele;

public class Salle {

    private Integer id;
    private String nom;

    public Salle(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

}
