package exo3.modele;

import java.time.LocalDate;

public class Oeuvre {

    private Integer id;
    private String nom;
    private LocalDate dateArrivee;

    public Oeuvre(Integer id, String nom, LocalDate dateArrivee) {
        this.id = id;
        this.nom = nom;
        this.dateArrivee = dateArrivee;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

}
