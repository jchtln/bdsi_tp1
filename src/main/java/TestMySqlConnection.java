import dao.ConnexionBDException;
import dao.DaoMuseeMySQLImpl;
import facade.FacadeMusee;
import facade.FacadeMuseeImpl;

import java.sql.*;

public class TestMySqlConnection {


    public static void main(String[] args) throws ConnexionBDException {

        FacadeMusee facadeMusee = new FacadeMuseeImpl(new DaoMuseeMySQLImpl());

        int id = facadeMusee.creerSalle("E18");

        System.out.println(id);

      /*  String url = "jdbc:mysql://localhost:3306";

        String DB = "MUSEE";

        // Defines username and password to connect to database server.
        String username = "root";
        String password = "root";

        // SQL command to create a database in MySQL.
        String sql = "CREATE DATABASE IF NOT EXISTS MUSEE";


        String sqlCreationTableOeuvres = "CREATE TABLE oeuvres(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,nomOeuvre VARCHAR(100),dateArrivee DATE)";


        String sqlCreationSalles = "CREATE TABLE salles(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, nomSalle VARCHAR(100))";


        String sqlCreationDeplacements = "CREATE TABLE deplacements(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, idOeuvre INT, idSalle INT, dateDeplacement DATE, FOREIGN KEY(idOeuvre) REFERENCES oeuvres(id), FOREIGN KEY(idSalle) REFERENCES salles(id))";


        String sqlAjoutGrandHall = "INSERT INTO salles(nomSalle) VALUES('Grand Hall') ";
        String sqlAjoutGrandeSalle1 = "INSERT INTO salles(nomSalle) VALUES('Grande Salle 1') ";
        String sqlAjoutReserve = "INSERT INTO salles(nomSalle) VALUES('Reserve') ";

        String sqlAjoutTRex = "INSERT INTO oeuvres(nomOeuvre,dateArrivee) VALUES ('T-Rex','2015-09-15')";
        String sqlDeplacementTRex1 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,3,'2015-09-15')";
        String sqlDeplacementTRex2 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,2,'2016-01-01')";
        String sqlDeplacementTRex3 = "INSERT INTO deplacements(idOeuvre,idSalle,dateDeplacement) VALUES (1,1,'2016-09-01')";

        String deplacements = "SELECT deplacements.id,oeuvres.nomOeuvre,salles.nomSalle,deplacements.dateDeplacement FROM (oeuvres INNER JOIN deplacements ON oeuvres.id=deplacements.idOeuvre) INNER JOIN salles ON deplacements.idSalle = salles.id";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();

            conn = DriverManager.getConnection(url+"/"+DB, username, password);
            Statement statement = conn.createStatement();
            statement.execute(sqlCreationTableOeuvres);

            statement = conn.createStatement();
            statement.execute(sqlCreationSalles);


            statement = conn.createStatement();
            statement.execute(sqlCreationDeplacements);


            statement = conn.createStatement();
            statement.executeUpdate(sqlAjoutGrandeSalle1);

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
        }*/








}
}
