package exo3;

import exo3.dao.BDException;
import exo3.dao.DaoMuseeMySQLImpl;
import exo3.facade.FacadeMusee;
import exo3.facade.FacadeMuseeImpl;
import exo3.modele.Deplacement;
import exo3.modele.Oeuvre;
import exo3.modele.Salle;

import java.time.LocalDate;
import java.util.Collection;

public class TestMySqlConnection {

    public static void main(String[] args) throws BDException {
        FacadeMusee facadeMusee = new FacadeMuseeImpl(new DaoMuseeMySQLImpl());

        // Création des salles
        Salle reserve = facadeMusee.creerSalle("Réserve");
        Salle grandHall = facadeMusee.creerSalle("Grand Hall");
        Salle grandeSalle1 = facadeMusee.creerSalle("Grande salle 1");

        // Réception du tyrannosaure "Rex", stocké dans la réserve le jour de la réception
        Oeuvre rex = facadeMusee.ajouterOeuvre("Rex", LocalDate.of(2015, 9, 15));
        facadeMusee.deplacerOeuvre(rex, reserve, LocalDate.of(2015, 9, 15));
        System.out.printf("%s est arrivé au musée le %s%n", rex.getNom(), rex.getDateArrivee());

        // Déplacements de Rex
        facadeMusee.deplacerOeuvre(rex, grandHall, LocalDate.of(2016, 1, 1));
        facadeMusee.deplacerOeuvre(rex, grandeSalle1, LocalDate.of(2016, 9, 1));
        facadeMusee.deplacerOeuvre(rex, grandHall,  LocalDate.of(2017, 3, 15));
        facadeMusee.deplacerOeuvre(rex, reserve, LocalDate.of(2018, 12, 20));
        facadeMusee.deplacerOeuvre(rex, grandeSalle1, LocalDate.of(2019, 4, 2));

        // Affichage de l'ensemble des déplacemens stockés dans le SI
        Collection<Deplacement> deplacements = facadeMusee.recupererDeplacements();
        for (Deplacement deplacement : deplacements) {
            System.out.printf("%s a été déplacé dans la salle \"%s\" le %s%n", deplacement.getOeuvre().getNom(),
                    deplacement.getSalle().getNom(), deplacement.getDateDeplacement());
        }
    }

}
