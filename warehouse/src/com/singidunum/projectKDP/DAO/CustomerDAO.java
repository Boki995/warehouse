package com.singidunum.projectKDP.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.singidunum.projectKDP.data.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
//Singelton patern    

    private static final CustomerDAO instance = new CustomerDAO();

    private CustomerDAO() {

    }

    public static CustomerDAO getInstance() {

        return instance;
    }
//Kreiranje metode create

    public void create(Customer c, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //upit za ubacivanje podataka u customer-a
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO customer(CustomerID, CustomerName, ContactPerson, Address, City, PostCode, Country) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1, c.getCustomerID());
            ps.setString(2, c.getCustomerName());
            ps.setString(3, c.getContactPerson());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCity());
            ps.setInt(6, c.getPostCode());
            ps.setString(7, c.getCountry());

            ps.executeUpdate();

        } finally {
            //Zatvaranje resursa
       ResourcesManager.closeResources(rs, ps);

        }

    }
//azuriranje tabele customer

    public void update(Customer c, Connection con) throws SQLException {

        PreparedStatement ps = null;

        try {
            //upit za izvrsavanje komandi za azuriranje
            ps = (PreparedStatement) con.prepareStatement("UPDATE customer SET CustomerName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=? WHERE CustomerID=?");
            ps.setString(1, c.getCustomerName());
            ps.setString(2, c.getContactPerson());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getCity());
            ps.setInt(5, c.getPostCode());
            ps.setString(6, c.getCountry());
            ps.setInt(7, c.getCustomerID());
            ps.executeUpdate();

        } finally {
    ResourcesManager.closeResources(null, ps);

        }

    }
//brisanje customer-a

    public void delete(int CustomerID, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za brisanje customer-a za odredjenim ID
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM customer WHERE CustomerID=?");
            ps.setInt(1, CustomerID);
            ps.executeUpdate();

        } finally {
    ResourcesManager.closeResources(null, ps);
        }

//metoda za pretragu Customer-a
    }

    public Customer find(Connection con, int CustomerID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer c = null;

        try {
//upit za pretragu customer-a sa odredjenim ID-om
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer WHERE CustomerID=?");
            ps.setInt(1, CustomerID);
            ps.executeQuery();
            if (rs.next()) {
                c = new Customer(CustomerID, rs.getString("CustomerName"), rs.getString("ContactPerson"), rs.getString("Address"), rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"));
            }
        } finally {
   ResourcesManager.closeResources(rs, ps);

        }

        return c;

    }
//metoda za izlistavanje svih Customer-a

    public List<Customer> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> listaCustomera = new ArrayList<>();
        try {
            //upit za izlistavanje Customer-a
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer c = new Customer(rs.getInt("CustomerID"), rs.getString("CustomerName"), rs.getString("ContactPerson"), rs.getString("Address"), rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"));
                listaCustomera.add(c);
            }

        } finally {
 ResourcesManager.closeResources(rs, ps);
        }
        return listaCustomera;
    }

}
