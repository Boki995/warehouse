
package com.singidunum.projectKDP.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.singidunum.projectKDP.data.Shippers;
import com.singidunum.projectKDP.data.Suppliers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SuppliersDAO {
     //Impletacija Singelton panterna
      private static final SuppliersDAO instance = new SuppliersDAO();

    private SuppliersDAO() {

    }

    public static SuppliersDAO getInstance() {

        return instance;
    }

//Ubacivanje vrednosti u suppliers tabelu
     public void create(Suppliers su, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //upit za ubacivanje podataka u tabelu suppliers
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO suppliers (SupplierID, SupplierName, ContactPerson, Address, City, PostCode, Country, Phone) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, su.getSupplierID());
            ps.setString(2, su.getSupplierName());
            ps.setString(3, su.getContactPerson());
            ps.setString(4, su.getAddress());
            ps.setString(5, su.getCity());
            ps.setInt(6, su.getPostCode());
            ps.setString(7, su.getCountry());
            ps.setInt(8, su.getPhone());
            
           

            ps.executeUpdate();

        } finally {
            //Zatvaranje resursa
     ResourcesManager.closeResources(rs, ps);

        }

    }
     
    //azuriranje dobavljaca 
    public void update(Suppliers su, Connection con) throws SQLException {

        PreparedStatement ps = null;

        try {
            //upit za izvrsavanje komandi za azuriranje
            ps = (PreparedStatement) con.prepareStatement("UPDATE suppliers SET SupplierName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=?, Phone=?  WHERE SupplierID=?");
              ps.setString(1, su.getSupplierName());
            ps.setString(2, su.getContactPerson());
            ps.setString(3, su.getAddress());
            ps.setString(4, su.getCity());
            ps.setInt(5, su.getPostCode());
            ps.setString(6, su.getCountry());
            ps.setInt(7, su.getPhone());
             ps.setInt(8, su.getSupplierID());
            
            
            ps.executeUpdate();

        } finally {
    ResourcesManager.closeResources(null, ps);

        }

    }
//brisanje dobavljaca

    public void delete(int SupplierID, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za brisanje dobavljaca za odredjenim ID
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM suppliers WHERE SuppplierID=?");
            ps.setInt(1, SupplierID);
            ps.executeUpdate();

        } finally {
     ResourcesManager.closeResources(null, ps);
        }

//metoda za pretragu dobavljaca
    }

    public Suppliers find(Connection con, int SupplierID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
       Suppliers su = null;

        try {
//upit za pretragu dobavljaca sa odredjenim ID-om
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM suppliers WHERE SupplierID=?");
            ps.setInt(1, SupplierID);
            ps.executeQuery();
            if (rs.next()) {
                su = new Suppliers(SupplierID, rs.getString("SupplierName"), rs.getString("ContactPerson"), rs.getString("Address"), rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"), rs.getInt("Phone"));
            }
        } finally {
   ResourcesManager.closeResources(rs, ps);

        }

        return su;

    }
//metoda za izlistavanje svih dobavljaca

    public List<Suppliers> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Suppliers> listaDobavljaca = new ArrayList<>();
        try {
            //upit za izlistavanje dobavljaca
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM suppliers");
            rs = ps.executeQuery();

            while (rs.next()) {
                Suppliers su = new Suppliers(rs.getInt("SupplierID"), rs.getString("SupplierName"), rs.getString("ContactPerson"), rs.getString("Address"), rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"), rs.getInt("Phone"));
                listaDobavljaca.add(su);
            }

        } finally {
 ResourcesManager.closeResources(rs, ps);
        }
        return listaDobavljaca;
    }    
}
