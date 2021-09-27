package dao;

import modele.Salle;

import java.sql.*;

public class DaoMuseeMySQLImpl implements DaoMusee{


    private final static String URL = "jdbc:mysql://localhost:3306";

    private final static String DB = "MUSEE";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "root";


    private Connection connection;

    public DaoMuseeMySQLImpl() {
        try {


            this.initialisation();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void initialisation() throws SQLException {

        this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);


        // SQL command to create a database in MySQL.
        String sql = "CREATE DATABASE IF NOT EXISTS "+DB;
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.execute();




        String sqlCreationTableOeuvres = "CREATE TABLE oeuvres(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,nomOeuvre VARCHAR(100),dateArrivee DATE)";


        String sqlCreationSalles = "CREATE TABLE salles(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, nomSalle VARCHAR(100))";


        String sqlCreationDeplacements = "CREATE TABLE deplacements(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, idOeuvre INT, idSalle INT, dateDeplacement DATE, FOREIGN KEY(idOeuvre) REFERENCES oeuvres(id), FOREIGN KEY(idSalle) REFERENCES salles(id))";



        this.connection = DriverManager.getConnection(URL+"/"+DB, USER_NAME, PASSWORD);
        Statement statement = this.connection.createStatement();
        statement.execute(sqlCreationTableOeuvres);

        statement = this.connection.createStatement();
        statement.execute(sqlCreationSalles);


        statement = this.connection.createStatement();
        statement.execute(sqlCreationDeplacements);
        this.connection.close();

    }





    @Override
    public int creerSalle(String nomSalle) throws ConnexionBDException {
        try {
            this.connection = DriverManager.getConnection(URL+"/"+DB, USER_NAME, PASSWORD);
            String sqlAjoutSalle = "INSERT INTO salles(nomSalle) VALUES(?) ";

            Integer id = null;
            Statement statement = null;
            try {
                PreparedStatement ps=connection.prepareStatement(sqlAjoutSalle,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,nomSalle);
                ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    id=rs.getInt(1);
                }
            } catch (Exception e) {
                throw new ConnexionBDException();
            }
            return id;

        } catch (SQLException throwables) {
            throw new ConnexionBDException();
        }


    }

    @Override
    public Salle getSalleById(int idSalle) {
        return null;
    }




/*        String sqlAjoutGrandHall = "INSERT INTO salles(nomSalle) VALUES('Grand Hall') ";
        String sqlAjoutGrandeSalle1 = "INSERT INTO salles(nomSalle) VALUES('Grande Salle 1') ";
        String sqlAjoutReserve = "INSERT INTO salles(nomSalle) VALUES('Reserve') ";

        String sqlAjoutTRex = "INSERT INTO oeuvres(nomOeuvre,dateArrivee) VALUES ('T-Rex','2015-09-15')";
        String sqlDeplacementTRex1 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,3,'2015-09-15')";
        String sqlDeplacementTRex2 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,2,'2016-01-01')";
        String sqlDeplacementTRex3 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,1,'2016-09-01')";

        String deplacements = "SELECT deplacements.id,oeuvres.nomOeuvre,salles.nomSalle,deplacements.dateDeplacement FROM (oeuvres INNER JOIN deplacements ON oeuvres.id=deplacements.idOeuvre) INNER JOIN salles ON deplacements.idSalle = salles.id";
*/

/*
    statement = conn.createStatement();
            statement.executeUpdate(sqlAjoutGrandHall);

    statement = conn.createStatement();
            statement.executeUpdate(sqlAjoutReserve);

    statement = conn.createStatement();
            statement.executeUpdate(sqlAjoutTRex);

    statement = conn.createStatement();
            statement.executeUpdate(sqlDeplacementTRex1);

    statement = conn.createStatement();
            statement.executeUpdate(sqlDeplacementTRex2);

    statement = conn.createStatement();
            statement.executeUpdate(sqlDeplacementTRex3);


    statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(deplacements);

            while (resultSet.next())
                    System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getDate(4));


} catch (SQLException throwables) {
        throwables.printStackTrace();
        }
*/



/**
 *
 */

}
