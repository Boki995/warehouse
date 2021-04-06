
package com.singidunum.projectKDP.DAO;

import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.OrderDetails;
import com.singidunum.projectKDP.data.Orders;
import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Shippers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDetailsDAO {
//Impletaija Singleton paterna
    private static final OrderDetailsDAO instance = new OrderDetailsDAO();

    private OrderDetailsDAO() {

    }

    public static OrderDetailsDAO getInstance() {

        return instance;
    }
//Ubacivanje vrednosti u tabelu detalji o narudzbinama
    public int create(OrderDetails od, Connection con) throws SQLException, SkladisteIzuzetak {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id= -1;

        try {
            //upit za ubacivanje vrednosti u tabelu proizvodi
            ps = con.prepareStatement("INSERT INTO orderdetails(OrderDetailsID,Quantity, Fk_OrderID, Fk_ProductID ) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, od.getOrderDetailsID());
            ps.setInt(2, od.getQuantity());
            Orders o= OrdersDAO.getInstance().find(od.getFK_OrderID().getOrderID(), con);
            Products p= ProductsDAO.getInstance().find( od.getFK_ProductID().getProductID(), con);
            
             if (o == null) {

                throw new SkladisteIzuzetak("Order " + od.getFK_OrderID() + "doesn't exist in database");
            }
              if (p == null) {

                throw new SkladisteIzuzetak("Product " + od.getFK_ProductID() + "doesn't exist in database");
            }
            
            ps.setInt(3, o.getOrderID());
            ps.setInt(4, p.getProductID());           
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
//Azuriranje detalja o narudzbinama
    public void update(OrderDetails od, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za azuriranje detalja o narudzbinama sa datim ID-om
            ps = con.prepareStatement("UPDATE orderdetails SET Quantity=?, Fk_OrderID=?, Fk_ProductID=? WHERE OrderDetailsID=?");
            ps.setInt(1, od.getQuantity());
            ps.setInt(2, od.getFK_OrderID().getOrderID());
            ps.setInt(3, od.getFK_ProductID().getProductID());
            ps.setInt(5, od.getOrderDetailsID());
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);

        }

    }
//Brisanje detalja o narudzbinama
    public void delete(OrderDetails od, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            //Brisanje detalja o narudzbinama sa datim ID-om
            ps = con.prepareStatement("DELETE FROM orderdetails WHERE OrderDetailsID=?");
            ps.setInt(1, od.getOrderDetailsID());
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);
        }

    }
//Metoda za pretragu detalja o narudzbinama
    public OrderDetails find(int OrderDetailsID, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetails od = null;
        
        try{
            //Upit za pretragu detalja o narudzbinama sa zaditim ID-om
        ps=con.prepareStatement("SELECT * FROM orderdetails WHERE OrderDetailsID=?");
        ps.setInt(1, OrderDetailsID);
        rs= ps.executeQuery();
        
        if(rs.next()){
        Orders o= OrdersDAO.getInstance().find(rs.getInt("Fk_OrderID"), con);
        Products p= ProductsDAO.getInstance().find( rs.getInt("Fk_ProductID"),con);
        od=new OrderDetails(OrderDetailsID, o , p , rs.getInt("Quantity"));
        }
        }finally{
        //Zatvaranje Resursa
        ResourcesManager.closeResources(rs, ps);
        }
        return od;

    }
//Izlistavanje svih detalja o narudzbinama    
    public List<OrderDetails> findAll(Orders o, Products p, Connection con) throws SQLException{
       PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetails> listadetaljaONarudzbinama = new ArrayList<>(); 
        try{
            //upit za izlistavanje svih detalja o narudzbinama
        ps=con.prepareStatement("SELECT * FROM orderdetails ");
       
        rs= ps.executeQuery();
        
        while(rs.next()){
            
        OrderDetails od= new OrderDetails(rs.getInt("OrderDetailsID"),o,p,rs.getInt("Quantity") );
        listadetaljaONarudzbinama.add(od);
        }
        
    
    
    }finally{
            //Zatvaranje Resursa
         ResourcesManager.closeResources(rs, ps);
        
        }
        return listadetaljaONarudzbinama;
    }    
}
