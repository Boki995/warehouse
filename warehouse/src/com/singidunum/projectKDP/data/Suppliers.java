
package com.singidunum.projectKDP.data;


public class Suppliers {
private int SupplierID;
private String SupplierName;
private String ContactPerson;
private String Address;
private String City;
private int PostCode;
private String Country;
private int Phone;
//konstruktor suppliers-a
    public Suppliers(int SupplierID, String SupplierName, String ContactPerson, String Address, String City, int PostCode, String Country, int Phone) {
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.ContactPerson = ContactPerson;
        this.Address = Address;
        this.City = City;
        this.PostCode = PostCode;
        this.Country = Country;
        this.Phone = Phone;
    }
//seteri i geteri
    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public int getPostCode() {
        return PostCode;
    }

    public void setPostCode(int PostCode) {
        this.PostCode = PostCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
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
        return "Suppliers{" + "SupplierID=" + SupplierID + ", SupplierName=" + SupplierName + ", ContactPerson=" + ContactPerson + ", Address=" + Address + ", City=" + City + ", PostCode=" + PostCode + ", Country=" + Country + ", Phone=" + Phone + '}';
    }


}
