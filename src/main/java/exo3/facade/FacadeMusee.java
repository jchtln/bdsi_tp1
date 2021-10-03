package exo3.facade;

import exo3.dao.BDException;
import exo3.modele.Deplacement;
import exo3.modele.Oeuvre;
import exo3.modele.Salle;

import java.time.LocalDate;
import java.util.Collection;

public interface FacadeMusee {

    /**
     * Crée une nouvelle salle.
     * @param nomSalle le nom de la salle à créer
     * @return la salle créée.
     */
    Salle creerSalle(String nomSalle) throws BDException;

    /**
     * Retourne l'ensemble des déplacements réalisés.
     * @return l'ensemble des déplacements présents dans le SI.
     */
    Collection<Deplacement> recupererDeplacements() throws BDException;

    /**
     * Ajoute une nouvelle oeuvre au musée.
     * @param nom le nom de l'oeuvre
     * @param dateArrivee la date d'arrivée de l'oeuvre au musée
     * @return l'oeuvre ajoutée.
     */
    Oeuvre ajouterOeuvre(String nom, LocalDate dateArrivee) throws BDException;

    /**
     * Déplace une oeuvre dans une autre salle.
     * @param oeuvre l'oeuvre à déplacer
     * @param salle la salle où déplacer l'oeuvre
     * @param dateDeplacement la date de déplacement de l'oeuvre
     * @return le déplacement réalisé.
     */
    Deplacement deplacerOeuvre(Oeuvre oeuvre, Salle salle, LocalDate dateDeplacement) throws BDException;

}
