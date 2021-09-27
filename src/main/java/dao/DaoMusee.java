package dao;

import modele.Salle;

public interface DaoMusee {
    /**
     * Permet de créer une salle avec le nom nomSalle
     * @param nomSalle
     * @return l'identifiant de la salle créée
     */
    int creerSalle(String nomSalle) throws ConnexionBDException;


    /**
     * Permet de récupérer la salle correspondant à l'identifiant
     * @param idSalle : identifiant de la salle que l'on veut récupérer
     * @return la salle concernée
     */
    Salle getSalleById(int idSalle);
}
