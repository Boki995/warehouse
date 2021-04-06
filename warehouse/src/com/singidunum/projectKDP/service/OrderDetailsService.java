
package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.DAO.OrderDetailsDAO;
import com.singidunum.projectKDP.DAO.ResourcesManager;
import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.OrderDetails;
import com.singidunum.projectKDP.data.Orders;
import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Shippers;
import com.singidunum.projectKDP.data.Suppliers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderDetailsService {
  private static final OrderDetailsService instance = new OrderDetailsService();
    
    private OrderDetailsService() {}
    
    public static OrderDetailsService getInstance() {
        return instance;
        
    }  
      public void makeOrder(OrderDetails od) throws SQLException, SkladisteIzuzetak{
        Connection con=null;
        ResultSet rs = null;
        try{
            con=ResourcesManager.getConnection();
            con.setAutoCommit(false);
            Customer c = new Customer(rs.getInt("CustomerID"), rs.getString("CustomerName"),
                    rs.getString("ContactPerson"), rs.getString("Address"), 
                    rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"));
            if(od.getQuantity()==0){
                throw new SkladisteIzuzetak("We don't have this item right now.");
            }
            
            Employees e = new Employees(rs.getInt("EmployeeID"),
                    rs.getString("LastName"),rs.getString("FirstName"), rs.getDate("BirthDate"));
            Shippers sh = new Shippers(rs.getInt("ShipperID"), rs.getString("ShipperName"),
                    rs.getInt("Phone"));
            Suppliers su = new Suppliers(rs.getInt("SupplierId"), rs.getString("SupplierName"),
                    rs.getString("ContactPerson"), rs.getString("Address"), 
                    rs.getString("City"), rs.getInt("PostCode"), rs.getString("Country"),
                    rs.getInt("Phone"));
            Orders o = new Orders(rs.getInt("OrderID"), rs.getDate("OrderDate"),
                    c, e, sh);
            Products p = new Products(rs.getInt("ProductID"), rs.getString("ProductName"),
                    rs.getString("ProductCategory"), rs.getInt("PricePerUnit"), su);
            
            od = new OrderDetails(rs.getInt("OrderDetailsID"), o, p, rs.getInt("Quantity"));
            od.setQuantity(od.getQuantity()-1);
            OrderDetailsDAO.getInstance().create(od, con);
        }catch(Exception e){
            ResourcesManager.rollbackTransactions(con);
            throw new SkladisteIzuzetak("You can order this product.");
        }finally{
            ResourcesManager.closeConnection((com.mysql.jdbc.Connection) con);
            ResourcesManager.closeResources(rs, null);
        }
    }
}
