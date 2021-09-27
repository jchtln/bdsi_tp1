package facade;

import dao.ConnexionBDException;
import dao.DaoMusee;
import modele.Salle;

public class FacadeMuseeImpl implements FacadeMusee{

    private DaoMusee daoMusee;

    public FacadeMuseeImpl(DaoMusee daoMusee) {
        this.daoMusee = daoMusee;
    }

    @Override
    public int creerSalle(String nomSalle) throws ConnexionBDException {
        return this.daoMusee.creerSalle(nomSalle);
    }

    @Override
    public Salle getSalleById(int idSalle) {
        return this.daoMusee.getSalleById(idSalle);
    }
}
