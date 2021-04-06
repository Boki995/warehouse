
package com.singidunum.projectKDP.data;


public class OrderDetails {
private int OrderDetailsID;
private int Quantity;
private Orders FK_OrderID;
private Products FK_ProductID;

//konstruktor orderDetails-a
    public OrderDetails(int OrderDetailsID, Orders FK_OrderID, Products FK_ProductID, int Quantity) {
        this.OrderDetailsID = OrderDetailsID;
        this.FK_OrderID = FK_OrderID;
        this.FK_ProductID = FK_ProductID;
        this.Quantity = Quantity;
    }
//seteri i geteri
    public int getOrderDetailsID() {
        return OrderDetailsID;
    }

    public void setOrderDetailsID(int OrderDetailsID) {
        this.OrderDetailsID = OrderDetailsID;
    }

    public Orders getFK_OrderID() {
        return FK_OrderID;
    }

    public void setFK_OrderID(Orders FK_OrderID) {
        this.FK_OrderID = FK_OrderID;
    }

    public Products getFK_ProductID() {
        return FK_ProductID;
    }

    public void setFK_ProductID(Products FK_ProductID) {
        this.FK_ProductID = FK_ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
//toString metoda
    @Override
    public String toString() {
        return "OrderDetails{" + "OrderDetailsID=" + OrderDetailsID + ", FK_OrderID=" + FK_OrderID + ", FK_ProductID=" + FK_ProductID + ", Quantity=" + Quantity + '}';
    }


}
