package exo3.dao;

import exo3.modele.Deplacement;
import exo3.modele.Oeuvre;
import exo3.modele.Salle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DaoMuseeMySQLImpl implements DaoMusee {

    private final static String URL = "jdbc:mysql://localhost:3306";
    private final static String DB = "musee";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "foobar";

    private final static String T_OEUVRE = "oeuvre";
    private final static String T_SALLE = "salle";
    private final static String T_DEPLACEMENT = "deplacement";

    ///////////////////////////////////////////////////////////////////////////

    public DaoMuseeMySQLImpl() throws BDException {
        try {
            // !! Supprime l'intégralité du contenu de la base de données si elle existe déjà !!
            initialisation();
        } catch (SQLException e) {
            throw new BDException(e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Integer addSalle(String nomSalle) throws BDException {
        String sql = String.format("INSERT INTO %s (nom) VALUES (?)", T_SALLE);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, nomSalle);
            return executeAndRetrieveId(ps);
        } catch (Exception e) {
            throw new BDException(e);
        }
    }

    @Override
    public Integer addOeuvre(String nom, LocalDate dateArrivee) throws BDException {
        String sql = String.format("INSERT INTO %s (nom, dateArrivee) VALUES (?,?)", T_OEUVRE);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, nom);
            ps.setDate(2, Date.valueOf(dateArrivee));
            return executeAndRetrieveId(ps);
        } catch (Exception e) {
            throw new BDException(e);
        }
    }

    @Override
    public Integer addDeplacement(Integer idOeuvre, Integer idSalle, LocalDate dateDeplacement) throws BDException {
        String sql = String.format("INSERT INTO %s (idOeuvre, idSalle, dateDeplacement) VALUES (?,?,?)", T_DEPLACEMENT);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, idOeuvre);
            ps.setInt(2, idSalle);
            ps.setDate(3, Date.valueOf(dateDeplacement));
            return executeAndRetrieveId(ps);
        } catch (Exception e) {
            throw new BDException(e);
        }
    }


    @Override
    public Salle getSalleById(Integer idSalle) throws BDException {
        Salle salle = null;

        String sql = String.format("SELECT id, nom FROM %s WHERE id=?", T_SALLE);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, idSalle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               salle = new Salle(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            throw new BDException(e);
        }

        return salle;
    }

    @Override
    public Oeuvre getOeuvreById(Integer idOeuvre) throws BDException {
        Oeuvre oeuvre = null;

        String sql = String.format("SELECT id, nom, dateArrivee FROM %s WHERE id=?", T_OEUVRE);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, idOeuvre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                oeuvre = new Oeuvre(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate());
            }
        } catch (Exception e) {
            throw new BDException(e);
        }

        return oeuvre;
    }

    @Override
    public Deplacement getDeplacementById(Integer idDeplacement) throws BDException {
        Deplacement deplacement = null;

        String sql = String.format("SELECT idOeuvre, idSalle, dateDeplacement FROM %s WHERE id=?", T_DEPLACEMENT);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, idDeplacement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Oeuvre oeuvre = getOeuvreById(rs.getInt(1));
                Salle salle = getSalleById(rs.getInt(2));
                LocalDate dateDeplacement = rs.getDate(3).toLocalDate();
                deplacement = new Deplacement(oeuvre, salle, dateDeplacement);
            }
        } catch (Exception e) {
            throw new BDException(e);
        }

        return deplacement;
    }

    @Override
    public Collection<Deplacement> getAllDeplacements() throws BDException {
        Collection<Deplacement> deplacements = new ArrayList<>();

        String sql = String.format("SELECT id FROM %s", T_DEPLACEMENT);

        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
        ) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                deplacements.add(getDeplacementById(rs.getInt(1)));
            }
        } catch (Exception e) {
            throw new BDException(e);
        }

        return deplacements;
    }

    @Override
    public void initialisation() throws SQLException {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();

            // Création de la base de données
            String sql = String.format("CREATE DATABASE IF NOT EXISTS %s", DB);
            stmt.execute(sql);

            // Suppression des tables, si elles existent déjà
            // (suppression dans l'ordre inverse, pour tenir compte des contraintes liées aux clés étrangères)
            String sqlDropTableDeplacements = String.format("DROP TABLE IF EXISTS %s", T_DEPLACEMENT);
            String sqlDropTableSalles = String.format("DROP TABLE IF EXISTS %s", T_SALLE);
            String sqlDropTableOeuvres = String.format("DROP TABLE IF EXISTS %s", T_OEUVRE);
            stmt.execute(sqlDropTableDeplacements);
            stmt.execute(sqlDropTableSalles);
            stmt.execute(sqlDropTableOeuvres);

            // Création des tables
            // Table des oeuvres
            String sqlCreationTableOeuvres = String.format(
                    "CREATE TABLE %s (" +
                            "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                            "nom VARCHAR(100)," +
                            "dateArrivee DATE)",
                    T_OEUVRE);
            stmt.execute(sqlCreationTableOeuvres);
            // Table des salles
            String sqlCreationTableSalles = String.format(
                    "CREATE TABLE %s (" +
                            "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                            "nom VARCHAR(100))",
                    T_SALLE);
            stmt.execute(sqlCreationTableSalles);
            // Table des déplacements
            String sqlCreationTableDeplacements = String.format(
                    "CREATE TABLE %1$s (" +
                            "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                            "idOeuvre INT," +
                            "idSalle INT," +
                            "dateDeplacement DATE," +
                            "FOREIGN KEY(idOeuvre) REFERENCES %2$s(id)," +
                            "FOREIGN KEY(idSalle) REFERENCES %3$s(id))",
                    T_DEPLACEMENT, T_OEUVRE, T_SALLE);
            stmt.execute(sqlCreationTableDeplacements);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    // Crée une connexion à la base de données
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("%s/%s", URL, DB), USER_NAME, PASSWORD);
    }

    // Exécute un PreparedStatement d'update/insert, et récupère l'identifiant de l'enregistrement modifié/créé
    private Integer executeAndRetrieveId(PreparedStatement ps) throws SQLException {
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

}
