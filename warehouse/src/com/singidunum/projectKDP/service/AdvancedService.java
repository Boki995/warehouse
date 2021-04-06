package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.DAO.ResourcesManager;
import izuzetak.SkladisteIzuzetak;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvancedService {

    private static final AdvancedService instance = new AdvancedService();

    private AdvancedService() {
    }

    public static AdvancedService getInstance() {
        return instance;
    }

    public void selectAllCustomersWithTheirOrders() throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT customer.CustomerName, orders.OrderID FROM customer INNER JOIN orders ON "
                    + "                          customer.CustomerID= orders.Fk_CustomerID");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(rs, ps);

        }
    }

    public void selectAllProductsWithTheirSuppliers(int SupplierID) throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT products.ProductsName FROM products INNER JOIN suppliers ON "
                    + "                  products.FK_SupplierID= suppliers.SupplierID WHERE SupplierID=?");
            ps.setInt(1, SupplierID);
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(rs, ps);

        }
    }

    public void selectAllProductsWithTheirShippers(int ShipperID) throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT products.ProductsName FROM products OUTER JOIN suppliers ON "
                    + "              products.FK_SupplierID= suppliers.SupplierID "
                    + "              OUTER JOIN orderdetails ON products.ProductID=orderdetails.FK_ProductID"
                    + "              OUTER JOIN orders ON orderdetails.FK_OrderID=orders.OrderID"
                    + "              OUTER JOIN shippers ON orders.FK_ShipperID= shippers.ShipperID WHERE ShipperID=?");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(rs, ps);

        }
    }

    public void sumOfAllOrders() throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT SUM(Quantity)*SUM(PricePerUnit) AS Sum of all orders  from orderdetails "
                    + "                 OUTER JOIN products ON orderdetails.FK_ProductID=products.ProductID");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
    }

    public void sumOfCustomerOrders(int CustomerID) throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT SUM(Quantity)*SUM(PricePerUnit) AS Sum of all orders  from orderdetails "
                    + "                 OUTER JOIN products ON orderdetails.FK_ProductID=products.ProductID"
                    + "                 OUTER JOIN orders ON orderdetails.FK_OrderID= orders.OrderID"
                    + "                 OUTER JOIN customer ON orders.FK_CustomerID=customer.CustomerID"
                    + "                 WHERE CustomerID=?");
            ps.setInt(1, CustomerID);
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
    }
        public void sumOfShipperOrders(int ShipperID) throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT SUM(Quantity)*SUM(PricePerUnit) AS Sum of all orders  from orderdetails "
                    + "                 OUTER JOIN products ON orderdetails.FK_ProductID=products.ProductID"
                    + "                 OUTER JOIN orders ON orderdetails.FK_OrderID= orders.OrderID"
                    + "                 OUTER JOIN shippers ON orders.FK_ShipperID=shipper.ShipperID"
                    + "                 WHERE ShipperID=?");
            ps.setInt(1, ShipperID);
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
    }
            public void sumOfSupplierOrders(int SupplierID) throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT SUM(Quantity)*SUM(PricePerUnit) AS Sum of all orders  from orderdetails "
                    + "                 OUTER JOIN products ON orderdetails.FK_ProductID=products.ProductID"
                    + "                 OUTER JOIN suppliers ON products.Fk_SupplierID=suppliers.SupplierID"
                    + "                 WHERE SupplierID=?");
            ps.setInt(1, SupplierID);
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
    }
            
     public void bestSaleByEmployee() throws SQLException, SkladisteIzuzetak {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ResourcesManager.getConnection();
            ps = con.prepareStatement("SELECT employees.FirstName, employees.LastName, (SELECT SUM(Quantity)*SUM(PricePerUnit)) AS Best Sale "
                    + "                 OUTER JOIN products ON orderdetails.FK_ProductID=products.ProductID"
                    + "                 OUTER JOIN orders ON orderdetails.FK_OrderID= orders.OrderID"
                    + "                 OUTER JOIN employees ON orders.FK_EmployeeID=employees.EmployeeID"
                    + "                 WHERE max(Quantity*PricePerUnit)");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
    }
     
     public void twoMostPopularProducts() throws SQLException, SkladisteIzuzetak{
      Connection con = null;
        PreparedStatement ps = null;

        try {
             con = ResourcesManager.getConnection();
             ps=con.prepareStatement("SELECT products.ProductName FROM products "
                     + " INNER JOIN orderdetails ON orderdetails.FK_ProductID=products.ProductID"
                     + " WHERE MAX(Quantity) AND MAX(Quantity)-1");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
     
     }
     
     public void supplierWithMostOrders() throws SkladisteIzuzetak, SQLException{
     Connection con = null;
        PreparedStatement ps = null;

        try {
             con = ResourcesManager.getConnection();
             ps=con.prepareStatement("SELECT suppliers.SupplierName FROM suppliers "
                     + " INNER JOIN orderdetails ON orderdetails.FK_ProductID=products.ProductID"
                     + " WHERE MAX(Quantity) AND MAX(Quantity)-1");
            ps.execute();
        } catch (SQLException e) {
            throw new SkladisteIzuzetak("Nemoguce izvrsiti upit");

        } finally {
            ResourcesManager.closeConnection(con);
            ResourcesManager.closeResources(null, ps);

        }
     
     }

}
