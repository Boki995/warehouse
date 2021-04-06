
package com.singidunum.projectKDP.data;

import java.util.Date;


public class Employees {
private int EmployeeID;
private String LastName;
private String FirstNama;
private Date BirthDate;
//konstruktor employee-a
    public Employees(int EmployeeID, String LastName, String FirstNama, Date BirthDate) {
        this.EmployeeID = EmployeeID;
        this.LastName = LastName;
        this.FirstNama = FirstNama;
        this.BirthDate = BirthDate;
    }
//seteri i geteri
    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstNama() {
        return FirstNama;
    }

    public void setFirstNama(String FirstNama) {
        this.FirstNama = FirstNama;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }
//toString metod
    @Override
    public String toString() {
        return "Employees{" + "EmployeeID=" + EmployeeID + ", LastName=" + LastName + ", FirstNama=" + FirstNama + ", BirthDate=" + BirthDate + '}';
    }


}
