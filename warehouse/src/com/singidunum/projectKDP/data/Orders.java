
package com.singidunum.projectKDP.data;

import java.util.Date;


public class Orders {
private int OrderID;
private Date OrderDate;
private Customer FK_CustomerID;
private Employees FK_EmployeeID;
private Shippers FK_ShipperID;
//konstruktor ordera
    public Orders(int OrderID, Date OrderDate, Customer FK_CustomerID, Employees FK_EmployeeID, Shippers FK_ShipperID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.FK_CustomerID = FK_CustomerID;
        this.FK_EmployeeID = FK_EmployeeID;
        this.FK_ShipperID = FK_ShipperID;
    }
//seteri i geteri
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Customer getFK_CustomerID() {
        return FK_CustomerID;
    }

    public void setFK_CustomerID(Customer FK_CustomerID) {
        this.FK_CustomerID = FK_CustomerID;
    }

    public Employees getFK_EmployeeID() {
        return FK_EmployeeID;
    }

    public void setFK_EmployeeID(Employees FK_EmployeeID) {
        this.FK_EmployeeID = FK_EmployeeID;
    }

    public Shippers getFK_ShipperID() {
        return FK_ShipperID;
    }

    public void setFK_ShipperID(Shippers FK_ShipperID) {
        this.FK_ShipperID = FK_ShipperID;
    }
//toString metoda
    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", FK_CustomerID=" + FK_CustomerID + ", FK_EmployeeID=" + FK_EmployeeID + ", FK_ShipperID=" + FK_ShipperID + '}';
    }

 
}
