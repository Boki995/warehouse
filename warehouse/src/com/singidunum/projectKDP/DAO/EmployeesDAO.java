
package com.singidunum.projectKDP.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.singidunum.projectKDP.data.Employees;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeesDAO {
    //Impletacija Singelton panterna
      private static final EmployeesDAO instance = new EmployeesDAO();

    private EmployeesDAO() {

    }

    public static EmployeesDAO getInstance() {

        return instance;
    }
    //Ubacivanje vrednosti u employee tabelu
     public void create(Employees e, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //upit za ubacivanje podataka u employee-a
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO employees (EmployeeID, LastName, FirstName, BirthDate) VALUES(?,?,?,?)");
            ps.setInt(1, e.getEmployeeID());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getFirstNama());
            ps.setDate(4, (Date) e.getBirthDate());
           

            ps.executeUpdate();

        } finally {
            //Zatvaranje resursa
       ResourcesManager.closeResources(rs, ps);

        }

    }
     
    //azuriranje zaposlenog 
    public void update(Employees e, Connection con) throws SQLException {

        PreparedStatement ps = null;

        try {
            //upit za izvrsavanje komandi za azuriranje
            ps = (PreparedStatement) con.prepareStatement("UPDATE employees SET LastName=?, FirstNamen=?, BirthDate=? WHERE EmployeeID=?");
            ps.setString(1, e.getLastName());
            ps.setString(2, e.getLastName());
            ps.setDate(3, (Date) e.getBirthDate());
            ps.setInt(4, e.getEmployeeID());
            
            ps.executeUpdate();

        } finally {
    ResourcesManager.closeResources(null, ps);

        }

    }
//brisanje zaposlenog

    public void delete(int EmployeeID, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za brisanje zaposlenog za odredjenim ID
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM employees WHERE EmployeeID=?");
            ps.setInt(1, EmployeeID);
            ps.executeUpdate();

        } finally {
     ResourcesManager.closeResources(null, ps);
        }

//metoda za pretragu zaposlenog
    }

    public Employees find(Connection con, int EmployeeID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employees e = null;

        try {
//upit za pretragu zaposlenog sa odredjenim ID-om
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM employees WHERE EmployeeID=?");
            ps.setInt(1, EmployeeID);
            ps.executeQuery();
            if (rs.next()) {
                e = new Employees(EmployeeID, rs.getString("LastName"), rs.getString("FirstName"), rs.getDate("BirthDate"));
            }
        } finally {
   ResourcesManager.closeResources(rs, ps);

        }

        return e;

    }
//metoda za izlistavanje svih zaposlenih

    public List<Employees> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employees> listaZaposlenih = new ArrayList<>();
        try {
            //upit za izlistavanje zaposlenih
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM employees");
            rs = ps.executeQuery();

            while (rs.next()) {
                Employees e = new Employees(rs.getInt("EmployeeID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getDate("BirthDate"));
                listaZaposlenih.add(e);
            }

        } finally {
 ResourcesManager.closeResources(rs, ps);
        }
        return listaZaposlenih;
    }
    
}
