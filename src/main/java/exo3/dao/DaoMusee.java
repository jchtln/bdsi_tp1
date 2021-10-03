package exo3.dao;

import exo3.modele.Deplacement;
import exo3.modele.Oeuvre;
import exo3.modele.Salle;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

public interface DaoMusee {

    /**
     * Ajoute une salle.
     * @param nomSalle le nom de la salle à ajouter
     * @return l'identifiant de la salle ajoutée.
     */
    Integer addSalle(String nomSalle) throws BDException;

    /**
     * Ajoute une oeuvre au musée.
     * @param nom le nom de l'oeuvre à ajouter
     * @param dateArrivee la date d'arrivée de l'oeuvre dans le musée
     * @return l'identifiant de l'oeuvre ajoutée.
     */
    Integer addOeuvre(String nom, LocalDate dateArrivee) throws BDException;

    /**
     * Déplace une oeuvre dans une salle.
     * @param idOeuvre l'identifiant de l'oeuvre à déplacer
     * @param idSalle l'identifiant de la salle où déplacer l'oeuvre
     * @param dateDeplacement la date de déplaement de l'oeuvre
     * @return l'identifiant du déplacement ajouté.
     */
    Integer addDeplacement(Integer idOeuvre, Integer idSalle, LocalDate dateDeplacement) throws BDException;

    /**
     * Récupère une salle selon son identifiant.
     * @param idSalle l'identifiant de la salle à récupérer
     * @return la salle.
     */
    Salle getSalleById(Integer idSalle) throws BDException;

    /**
     * Récupère une oeuvre selon son identifiant.
     * @param idOeuvre l'identifiant de l'oeuvre à récupérer
     * @return l'oeuvre.
     */
    Oeuvre getOeuvreById(Integer idOeuvre) throws BDException;

    /**
     * Récupère un déplacement selon son identifiant.
     * @param idDeplacement l'identifiant du déplacement à récupérer
     * @return le déplacement.
     */
    Deplacement getDeplacementById(Integer idDeplacement) throws BDException;

    /**
     * Récupère l'ensemble des déplacements réalisés dans le SI.
     * @return l'ensemble des déplacements.
     */
    Collection<Deplacement> getAllDeplacements() throws BDException;

    /**
     * Initialise la base de données et les tables du SI.
     *
     * /!\ Cette méthode supprime le contenu de la base de données, si elle existe déjà.
     * Elle ne doit être utilisée que lors d'une nouvelle installation, ou au début d'une migration complète.
     */
    void initialisation() throws SQLException;
}
