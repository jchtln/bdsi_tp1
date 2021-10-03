package exo3.modele;

import java.time.LocalDate;

public class Deplacement {

    private Oeuvre oeuvre;
    private Salle salle;
    private LocalDate dateDeplacement;

    public Deplacement(Oeuvre oeuvre, Salle salle, LocalDate dateDeplacement) {
        this.oeuvre = oeuvre;
        this.salle = salle;
        this.dateDeplacement = dateDeplacement;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public Salle getSalle() {
        return salle;
    }

    public LocalDate getDateDeplacement() {
        return dateDeplacement;
    }

}
