
package com.singidunum.projectKDP.data;


public class Products {
 private int ProductID;
 private String ProductName;
 private String ProductCategory;
 private int PricePerUnit;
 private Suppliers Fk_SupplierID;
//konstruktor producta
    public Products(int ProductID, String ProductName, String ProductCategory, int PricePerUnit, Suppliers Fk_SupplierID) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
        this.PricePerUnit = PricePerUnit;
        this.Fk_SupplierID = Fk_SupplierID;
    }
//seteri i geteri
    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String ProductCategory) {
        this.ProductCategory = ProductCategory;
    }

    public int getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(int PricePerUnit) {
        this.PricePerUnit = PricePerUnit;
    }

    public Suppliers getFk_SupplierID() {
        return Fk_SupplierID;
    }

    public void setFk_SupplierID(Suppliers Fk_SupplierID) {
        this.Fk_SupplierID = Fk_SupplierID;
    }
//toString metoda
    @Override
    public String toString() {
        return "Products{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", ProductCategory=" + ProductCategory + ", PricePerUnit=" + PricePerUnit + ", Fk_SupplierID=" + Fk_SupplierID + '}';
    }
 
 
}
