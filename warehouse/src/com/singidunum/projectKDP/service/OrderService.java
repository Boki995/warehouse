
package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.DAO.CustomerDAO;
import com.singidunum.projectKDP.DAO.EmployeesDAO;
import com.singidunum.projectKDP.DAO.OrdersDAO;
import com.singidunum.projectKDP.DAO.ResourcesManager;
import com.singidunum.projectKDP.DAO.ShippersDAO;
import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.Orders;
import com.singidunum.projectKDP.data.Shippers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderService {
 private static final OrderService instance = new OrderService();
    
    private OrderService() {}
    
    public static OrderService getInstance() {
        return instance;
    }
    
public void createOrder(Customer c, Employees e, Shippers sh) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    ResultSet rs =null;
    
    try{
    con= ResourcesManager.getConnection();
    con.setAutoCommit(false);
    
    c = new Customer(rs.getInt("CustomerID"), rs.getString("CustomerName"),
                    rs.getString("ContactPerson"), rs.getString("Address"), rs.getString("City"),
                    rs.getInt("PostCode"), rs.getString("Country"));
      e= new Employees(rs.getInt("EmployeeID"), rs.getString("LastName"),
                    rs.getString("FirstName"), rs.getDate("BirthDate"));
    sh = new Shippers(rs.getInt("ShipperID"), rs.getString("ShipperName"),rs.getInt("Phone"));
    
    Orders o = new Orders(rs.getInt("OrderID"), rs.getDate("OrderDate"),c,e,sh); 
    OrdersDAO.getInstance().create(o, con);
    con.commit();
    
    }catch (Exception ex){
        ResourcesManager.rollbackTransactions(con);
        throw new SkladisteIzuzetak("Failed to make order.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}   
public void addCustomerOrder(Customer c) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    CustomerDAO.getInstance().create(c, (com.mysql.jdbc.Connection) con);
    }catch (SQLException ex){
        throw new SkladisteIzuzetak("Failed to add Customer Order.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public void addOrder(Orders o) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    OrdersDAO.getInstance().create(o, (com.mysql.jdbc.Connection) con);
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to add order.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}

public void addEmployeeOrder(Employees e) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    EmployeesDAO.getInstance().create(e, (com.mysql.jdbc.Connection) con);
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to add Employee order.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public void addShipperOrder(Shippers sh) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    ShippersDAO.getInstance().create(sh, (com.mysql.jdbc.Connection) con);
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to add Shipper order.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public Orders findOrder(int OrderID) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
  return OrdersDAO.getInstance().find(OrderID, (com.mysql.jdbc.Connection) con);
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public Customer findCustomerOrder(int CustomerID) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
  return CustomerDAO.getInstance().find((com.mysql.jdbc.Connection) con,CustomerID );
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public Employees findEmployeesOrder(int EmployeeID) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
  return EmployeesDAO.getInstance().find((com.mysql.jdbc.Connection) con,EmployeeID );
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}

public Shippers findShippersOrder(int ShipperID) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
  return ShippersDAO.getInstance().find((com.mysql.jdbc.Connection) con,ShipperID );
    }catch (SQLException ex){        
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public void updateOrder(Orders o) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    con.setAutoCommit(false);
    OrdersDAO.getInstance().update(o, con);
    con.commit();
    }catch (SQLException ex){
     ResourcesManager.rollbackTransactions(con);
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}
public void deleteOrder(int OrderID) throws SQLException, SkladisteIzuzetak {
    Connection con=null;
    try{
    con= ResourcesManager.getConnection();
    con.setAutoCommit(false);
  Orders o =  OrdersDAO.getInstance().find(OrderID, con);
  if(o!=null){
  OrdersDAO.getInstance().delete(OrderID, con);
  }
    con.commit();
    }catch (SQLException ex){
     ResourcesManager.rollbackTransactions(con);
        throw new SkladisteIzuzetak("Failed to find Order with given ID.");
        
    
    }finally{
    ResourcesManager.closeConnection(con);
    }
    
}



}
