
package com.singidunum.projectKDP.data;


public class Customer {
private int CustomerID;
private String CustomerName;
private String ContactPerson;
private String Address;
private String City;
private int PostCode;
private String Country;
//Konstruktor Customera
    public Customer(int CustomerID, String CustomerName, String ContactPerson, String Address, String City, int PostCode, String Country) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.ContactPerson = ContactPerson;
        this.Address = Address;
        this.City = City;
        this.PostCode = PostCode;
        this.Country = Country;
    }
//Seteri i geteri
    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
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
//toString metod
    @Override
    public String toString() {
        return "Customer{" + "CustomerID=" + CustomerID + ", CustomerName=" + CustomerName + ", ContactPerson=" + ContactPerson + ", Address=" + Address + ", City=" + City + ", PostCode=" + PostCode + ", Country=" + Country + '}';
    }



}
