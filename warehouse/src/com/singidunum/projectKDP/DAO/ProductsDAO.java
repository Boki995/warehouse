package com.singidunum.projectKDP.DAO;

import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Suppliers;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sun.security.util.ResourcesMgr;

public class ProductsDAO {
//Impletaija Singleton paterna
    private static final ProductsDAO instance = new ProductsDAO();

    private ProductsDAO() {

    }

    public static ProductsDAO getInstance() {

        return instance;
    }
//Ubacivanje vrednosti u tabelu proizvodi
    public int create(Products p, Connection con) throws SQLException, SkladisteIzuzetak {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id=-1;

        try {
            //upit za ubacivanje vrednosti u tabelu proizvodi
            ps = con.prepareStatement("INSERT INTO products(ProductID, ProductName, ProductCategory, PricePerUnit, Fk_SupplierID) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getProductCategory());
            ps.setInt(4, p.getPricePerUnit());
            Suppliers su = SuppliersDAO.getInstance().find((com.mysql.jdbc.Connection) con, p.getFk_SupplierID().getSupplierID());

            if (su == null) {

                throw new SkladisteIzuzetak("Supplier " + p.getFk_SupplierID() + "doesn't exist in database");
            }
            ps.setInt(5, su.getSupplierID());
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
//Azuriranje proizvoda
    public void update(Products p, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            //upit za azuriranje proizvoda sa datim ID-om
            ps = con.prepareStatement("UPDATE products SET ProductName=?, ProductCategory=?, PricePerUnit=?, Fk_SupplierID=? WHERE ProductID=?");
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getProductCategory());
            ps.setInt(3, p.getPricePerUnit());
            ps.setInt(4, p.getFk_SupplierID().getSupplierID());
            ps.setInt(5, p.getProductID());
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);

        }

    }
//Brisanje proizvoda
    public void delete(int ProductID, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            //Brisanje proizvoda sa dati ID-om
            ps = con.prepareStatement("DELETE FROM products WHERE ProductID=?");
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } finally {
            //Zatvaranje resursa
            ResourcesManager.closeResources(null, ps);
        }

    }
//Metoda za pretragu proizvoda
    public Products find(int ProductID, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Products p = null;
        
        try{
            //Upit za pretragu proizvoda sa zaditim ID-om
        ps=con.prepareStatement("SELECT * FROM products WHERE ProductID=?");
        ps.setInt(1, ProductID);
        rs= ps.executeQuery();
        if(rs.next()){
        Suppliers su= SuppliersDAO.getInstance().find((com.mysql.jdbc.Connection) con, rs.getInt("Fk_SupplierID"));
        p=new Products(ProductID, rs.getString("ProductName"), rs.getString("ProductCategory"), rs.getInt("PricePerUnit"), su);
        }
        }finally{
        //Zatvaranje Resursa
        ResourcesManager.closeResources(rs, ps);
        }
        return p;

    }
//Izlistavanje svih proizvoda    
    public List<Products> findAll(Suppliers su, Connection con) throws SQLException{
       PreparedStatement ps = null;
        ResultSet rs = null;
        List<Products> listaProizvoda = new ArrayList<>(); 
        try{
            //upit za izlistavanje svih proizvoda
        ps=con.prepareStatement("SELECT * FROM products WHERE fk_SupplierID=?");
        ps.setInt(1, su.getSupplierID());
        rs= ps.executeQuery();
        
        while(rs.next()){
        Products p= new Products(rs.getInt("ProductID"),rs.getString("ProductName"), rs.getString("ProductCategory"), rs.getInt("PricePerUnit"),su);
        listaProizvoda.add(p);
        }
        
    
    
    }finally{
            //Zatvaranje proizvoda
         ResourcesManager.closeResources(rs, ps);
        
        }
        return listaProizvoda;
    }
}
