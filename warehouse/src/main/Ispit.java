package main;

import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employees;
import com.singidunum.projectKDP.data.OrderDetails;
import com.singidunum.projectKDP.data.Orders;
import com.singidunum.projectKDP.data.Products;
import com.singidunum.projectKDP.data.Shippers;
import com.singidunum.projectKDP.data.Suppliers;
import com.singidunum.projectKDP.service.AdvancedService;
import com.singidunum.projectKDP.service.OrderDetailsService;
import com.singidunum.projectKDP.service.OrderService;
import com.singidunum.projectKDP.service.ProductService;
import izuzetak.SkladisteIzuzetak;
import java.sql.Date;
import java.sql.SQLException;

public class Ispit {

    private static final OrderDetailsService ods = OrderDetailsService.getInstance();
    private static final OrderService os = OrderService.getInstance();
    private static final ProductService ps = ProductService.getInstance();
    private static final AdvancedService as = AdvancedService.getInstance();

    public static void addOrder() throws SQLException, SkladisteIzuzetak {
        os.createOrder(new Customer(1, "Bojan", "bojan@gmail.com", "Cara Dusana 21", "Zemun", 11000, "Serbia"),
                new Employees(1, "Milosevic", "Milos", new Date(11  - 06 - 1992)),
                new Shippers(1, "Ivan", 06434512));
    }

    public static void addOrderDetails() throws SQLException, SkladisteIzuzetak {
        ods.makeOrder(new OrderDetails(1,
                new Orders(1,
                        new Date(18 - 12 - 1976),
                        new Customer(1, "Zika", "zika@yahoo.com", "Balakanska 32", "Beograd", 11000, "Serbia"),
                        new Employees(1, "Peric", "Pera", new Date(13 - 03 - 2001)),
                        new Shippers(1, "Pera", 0643124)), new Products(1, "Lego", "igracka", 43,
                        new Suppliers(1, "Jovan", "jovan@gmail.com", "Kumodraska 221", "Beograd", 11000, "Srbija", 0621234)), 5));
    }
    
    public static void addProduct() throws SQLException, SkladisteIzuzetak{
    ps.addProduct(new Products(1,"bojler","bela tehnika", 100, new Suppliers(1, "Marko", "marko@yahoo.com","Cara Dusana 12", "Zemun",11000,"Srbija",0611423455)));
    
    }

    public static void main(String[] args) throws SQLException, SkladisteIzuzetak {
     addOrder();
     addOrderDetails();
     addProduct();
        

    }

}
