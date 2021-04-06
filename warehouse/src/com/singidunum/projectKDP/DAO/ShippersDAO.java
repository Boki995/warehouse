
package com.singidunum.projectKDP.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.Shippers;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ShippersDAO {
    //Impletacija Singelton panterna
      private static final ShippersDAO instance = new ShippersDAO();

    private ShippersDAO() {

    }

    public static ShippersDAO getInstance() {

        return instance;
    }

//Ubacivanje vrednosti u shippers tabelu
     public void create(Shippers sh, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //upit za ubacivanje podataka u tabelu shippers
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO shippers (ShipperID, ShipperName, Phone) VALUES(?,?,?)");
            ps.setInt(1, sh.getShipperID());
            ps.setString(2, sh.getShipperName());
            ps.setInt(3, sh.getPhone());
            
           

            ps.executeUpdate();

        } finally {
            //Zatvaranje resursa
       ResourcesManager.closeResources(rs, ps);

        }

    }
     
    //azuriranje prevoznika 
    public void update(Shippers sh, Connection con) throws SQLException {

        PreparedStatement ps = null;

        try {
            //upit za izvrsavanje komandi za azuriranje
            ps = (PreparedStatement) con.prepareStatement("UPDATE shippers SET ShipperName=?, Phone=? WHERE ShippersID=?");
            ps.setString(1, sh.getShipperName());
            ps.setInt(2, sh.getPhone());
            ps.setInt(3, sh.getShipperID());
            
            
            ps.executeUpdate();

        } finally {
    ResourcesManager.closeResources(null, ps);

        }

    }
//brisanje prevoznika

    public void delete(int ShipperID, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za brisanje prevoznika za odredjenim ID
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM shippers WHERE ShipperID=?");
            ps.setInt(1, ShipperID);
            ps.executeUpdate();

        } finally {
     ResourcesManager.closeResources(null, ps);
        }

//metoda za pretragu prevoznika
    }

    public Shippers find(Connection con, int ShipperID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
       Shippers sh = null;

        try {
//upit za pretragu prevoznika sa odredjenim ID-om
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM shippers WHERE ShipperID=?");
            ps.setInt(1, ShipperID);
            ps.executeQuery();
            if (rs.next()) {
                sh = new Shippers(ShipperID, rs.getString("ShipperName"), rs.getInt("Phone"));
            }
        } finally {
   ResourcesManager.closeResources(rs, ps);

        }

        return sh;

    }
//metoda za izlistavanje svih prevoznika

    public List<Shippers> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Shippers> listaPrevoznika = new ArrayList<>();
        try {
            //upit za izlistavanje prevoznika
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM shippers");
            rs = ps.executeQuery();

            while (rs.next()) {
                Shippers sh = new Shippers(rs.getInt("ShipperID"), rs.getString("ShipperName"), rs.getInt("Phone"));
                listaPrevoznika.add(sh);
            }

        } finally {
 ResourcesManager.closeResources(rs, ps);
        }
        return listaPrevoznika;
    }    
}
