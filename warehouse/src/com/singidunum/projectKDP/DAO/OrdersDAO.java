
package com.singidunum.projectKDP.DAO;

import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.Orders;
import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Shippers;
import com.singidunum.projectKDP.data.Suppliers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrdersDAO {
//Impletaija Singleton paterna
    private static final OrdersDAO instance = new OrdersDAO();

    private OrdersDAO() {

    }

    public static OrdersDAO getInstance() {

        return instance;
    }
//Ubacivanje vrednosti u tabelu narudzbine
    public int create(Orders o, Connection con) throws SQLException, SkladisteIzuzetak {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id= -1;

        try {
            //upit za ubacivanje vrednosti u tabelu proizvodi
            ps = con.prepareStatement("INSERT INTO orders(OrderID, OrderDate, Fk_CustomerID, Fk_EmployeeID, Fk_ShipperID) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getOrderID());
            ps.setDate(2, (Date) o.getOrderDate());
            Customer c= CustomerDAO.getInstance().find((com.mysql.jdbc.Connection) con, o.getFK_CustomerID().getCustomerID());
            Employees e= EmployeesDAO.getInstance().find((com.mysql.jdbc.Connection) con, o.getFK_EmployeeID().getEmployeeID());
            Shippers sh = ShippersDAO.getInstance().find((com.mysql.jdbc.Connection) con, o.getFK_ShipperID().getShipperID());
             if (c == null) {

                throw new SkladisteIzuzetak("Customer " + o.getFK_CustomerID() + "doesn't exist in database");
            }
              if (e == null) {

                throw new SkladisteIzuzetak("Employee " + o.getFK_EmployeeID() + "doesn't exist in database");
            }
            if (sh == null) {

                throw new SkladisteIzuzetak("Shipper " + o.getFK_ShipperID() + "doesn't exist in database");
            }
            ps.setInt(3, c.getCustomerID());
            ps.setInt(4, e.getEmployeeID());
            ps.setInt(5, sh.getShipperID());
            ps.executeUpdate();
            

            rs = ps.getGeneratedKeys();
            rs.next();
            id=rs.getInt(1);

        } finally {
            //Zatvaranje Resursa
            ResourcesManager.closeResources(rs, ps);
        }
return id;
    }
//Azuriranje narudzbina
    public void update(Orders o, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za azuriranje narudzbine sa datim ID-om
            ps = con.prepareStatement("UPDATE orders SET OrderDate=?, Fk_CustomerID=?, Fk_EmployeeID=?, Fk_ShipperID=? WHERE OrderID=?");
            ps.setDate(1, (Date) o.getOrderDate());
            ps.setInt(2, o.getFK_CustomerID().getCustomerID());
            ps.setInt(3, o.getFK_EmployeeID().getEmployeeID());
            ps.setInt(4, o.getFK_ShipperID().getShipperID());
            ps.setInt(5, o.getOrderID());
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);

        }

    }
//Brisanje narudzbina
    public void delete(int OrderID, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            //Brisanje narudzbina sa dati ID-om
            ps = con.prepareStatement("DELETE FROM orders WHERE OrderID=?");
            ps.setInt(1, OrderID);
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);
        }

    }
//Metoda za pretragu narudzbina
    public Orders find(int OrderID, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Orders o = null;
        
        try{
            //Upit za pretragu narudzbina sa zaditim ID-om
        ps=con.prepareStatement("SELECT * FROM orders WHERE OrderID=?");
        ps.setInt(1, OrderID);
        rs= ps.executeQuery();
        
        if(rs.next()){
        Customer c= CustomerDAO.getInstance().find((com.mysql.jdbc.Connection) con, rs.getInt("Fk_CustomerID"));
        Employees e= EmployeesDAO.getInstance().find((com.mysql.jdbc.Connection) con, rs.getInt("Fk_EmployeeID"));
        Shippers sh= ShippersDAO.getInstance().find((com.mysql.jdbc.Connection) con, rs.getInt("Fk_ShipperID"));
        o=new Orders(OrderID, rs.getDate("OrderDate"), c, e, sh);
        }
        }finally{
        //Zatvaranje Resursa
        ResourcesManager.closeResources(rs, ps);
        }
        return o;

    }
//Izlistavanje svih narudzbina    
    public List<Orders> findAll(Customer c, Employees e,Shippers sh, Connection con) throws SQLException{
       PreparedStatement ps = null;
        ResultSet rs = null;
        List<Orders> listaNarudzbina = new ArrayList<>(); 
        try{
            //upit za izlistavanje svih narudzbina
        ps=con.prepareStatement("SELECT * FROM orders ");
       
        rs= ps.executeQuery();
        
        while(rs.next()){
            
        Orders o= new Orders(rs.getInt("OrderID"),rs.getDate("OrderDate"), c , e , sh);
        listaNarudzbina.add(o);
        }
        
    
    
    }finally{
            //Zatvaranje Resursa
         ResourcesManager.closeResources(rs, ps);
        
        }
        return listaNarudzbina;
    }
}    

