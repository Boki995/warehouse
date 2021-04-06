
package com.singidunum.projectKDP.DAO;

import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class ResourcesManager {
    static {
        //Ukljucivanje drivera 
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//Dohvatanje konekcije
    public static Connection getConnection() throws SQLException {
        //Konekcija na bazu sa userom:root i password-om:
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/warehouse?user=root&password=");
        return con;
    }
//Zatvaranje resursa
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        //Provjeravanje da li je result razlicit od null ako nije zatvara result
        if (resultSet != null) {
            resultSet.close();
        }
        //Provjeravanje da li je prepareStatment razlicit od null ako nije zatvara ga
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
//Zatvaranje konekcije
    public static void closeConnection(Connection con) throws SkladisteIzuzetak {
        //Provjeravanje da li je konekcija razlicita od null
        if (con != null) {
            //Pokusavanje da se zatvori konekcija
            try {
                con.close();
            } catch (SQLException ex) {
                //Ako ne uspe da zatvori konekciju baca se izuzetak da nije uspelo zatvaranje
                throw new SkladisteIzuzetak("Failed to close database connection.", ex);
            }
        }
    }
//Metoda za rollback
    public static void rollbackTransactions(Connection con) throws SkladisteIzuzetak {
        //Proverava da li ima neka konekcija
        if (con != null) {
            //Kada pronadje konekciju pokusava da uradi roolback
            try {
                con.rollback();
            } catch (SQLException ex) {
                //Ako ne uspe da izvrsi rollback baca se izuzetak da nije uspelo bacanje izuzetka
                throw new SkladisteIzuzetak("Failed to rollback database transactions.", ex);
            }
        }
    }    
}
