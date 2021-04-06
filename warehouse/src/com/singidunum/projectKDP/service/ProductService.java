
package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.DAO.ProductsDAO;
import com.singidunum.projectKDP.DAO.ResourcesManager;
import com.singidunum.projectKDP.DAO.SuppliersDAO;
import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Suppliers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.SQLException;


public class ProductService {
private static final ProductService instance =new ProductService();

private ProductService(){
}
public static ProductService getInstance(){

return instance;
}


public void addProduct(Products p) throws SQLException, SkladisteIzuzetak{
    Connection con=null;
    
    try{
    con= ResourcesManager.getConnection();
    con.setAutoCommit(false);
    ProductsDAO.getInstance().create(p, con);
    con.commit();
    
    }catch(SQLException e){
    ResourcesManager.rollbackTransactions(con);
    throw new SkladisteIzuzetak("Failed to add product");
    
    
    }finally{
    ResourcesManager.closeConnection(con);
    }

}

public void deleteProduct(int ProductID) throws SQLException, SkladisteIzuzetak{
Connection con=null;

try{
con= ResourcesManager.getConnection();
con.setAutoCommit(false);
Products p = ProductsDAO.getInstance().find(ProductID, con);
if(p!=null){
ProductsDAO.getInstance().delete(ProductID, con);
}
con.commit();
}catch(Exception e){
ResourcesManager.rollbackTransactions(con);
throw new SkladisteIzuzetak("Failed to delete product");
}finally{
    ResourcesManager.closeConnection(con);
    }

}
public void updateProduct(Products p) throws SQLException, SkladisteIzuzetak{
Connection con=null;

try{
con= ResourcesManager.getConnection();
con.setAutoCommit(false);
ProductsDAO.getInstance().update(p, con);

con.commit();
}catch(Exception e){
ResourcesManager.rollbackTransactions(con);
throw new SkladisteIzuzetak("Failed to update product");
}finally{
    ResourcesManager.closeConnection(con);
    }

}
public void addSupplier(Suppliers su) throws SQLException, SkladisteIzuzetak{
Connection con=null;

try{
con= ResourcesManager.getConnection();
 SuppliersDAO.getInstance().create(su, (com.mysql.jdbc.Connection) con);
}catch(Exception e){
throw new SkladisteIzuzetak("Failed to add Supplier");
}finally{
    ResourcesManager.closeConnection(con);
    }

}
public Suppliers findSupplier(int SupplierID) throws SQLException, SkladisteIzuzetak{
Connection con=null;

try{
con= ResourcesManager.getConnection();
return SuppliersDAO.getInstance().find((com.mysql.jdbc.Connection) con, SupplierID);
}catch(Exception e){
throw new SkladisteIzuzetak("Failed to find Supplier with given ID");
}finally{
    ResourcesManager.closeConnection(con);
    }

}

public void deleteSupplier(int SupplierID) throws SQLException, SkladisteIzuzetak{
Connection con=null;

try{
con= ResourcesManager.getConnection();
con.setAutoCommit(false);
Suppliers su = SuppliersDAO.getInstance().find((com.mysql.jdbc.Connection) con, SupplierID);
if(su!=null){
SuppliersDAO.getInstance().delete(SupplierID, (com.mysql.jdbc.Connection) con);
}
con.commit();
}catch(Exception e){
throw new SkladisteIzuzetak("Failed to delete Supplier with given ID");
}finally{
    ResourcesManager.closeConnection(con);
    }

}

}
