
package com.singidunum.projectKDP.data;


public class Shippers {
private int ShipperID;
private String ShipperName;
private int Phone;
//konstruktor shippers-a
    public Shippers(int ShipperID, String ShipperName, int Phone) {
        this.ShipperID = ShipperID;
        this.ShipperName = ShipperName;
        this.Phone = Phone;
    }
//seteri i geteri
    public int getShipperID() {
        return ShipperID;
    }

    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    public String getShipperName() {
        return ShipperName;
    }

    public void setShipperName(String ShipperName) {
        this.ShipperName = ShipperName;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }
//toString metoda
    @Override
    public String toString() {
        return "Shippers{" + "ShipperID=" + ShipperID + ", ShipperName=" + ShipperName + ", Phone=" + Phone + '}';
    }
    
    

}
