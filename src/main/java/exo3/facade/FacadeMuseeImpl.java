package exo3.facade;

import exo3.dao.BDException;
import exo3.dao.DaoMusee;
import exo3.modele.Deplacement;
import exo3.modele.Oeuvre;
import exo3.modele.Salle;

import java.time.LocalDate;
import java.util.Collection;

public class FacadeMuseeImpl implements FacadeMusee {

    private final DaoMusee daoMusee;

    public FacadeMuseeImpl(DaoMusee daoMusee) {
        this.daoMusee = daoMusee;
    }

    @Override
    public Salle creerSalle(String nomSalle) throws BDException {
        Integer idSalle = daoMusee.addSalle(nomSalle);
        return daoMusee.getSalleById(idSalle);
    }

    @Override
    public Collection<Deplacement> recupererDeplacements() throws BDException {
        return daoMusee.getAllDeplacements();
    }

    @Override
    public Oeuvre ajouterOeuvre(String nom, LocalDate dateArrivee) throws BDException {
        Integer idOeuvre = daoMusee.addOeuvre(nom, dateArrivee);
        return daoMusee.getOeuvreById(idOeuvre);
    }

    @Override
    public Deplacement deplacerOeuvre(Oeuvre oeuvre, Salle salle, LocalDate dateDeplacement) throws BDException {
        Integer idDeplacement = daoMusee.addDeplacement(oeuvre.getId(), salle.getId(), dateDeplacement);
        return daoMusee.getDeplacementById(idDeplacement);
    }

}
