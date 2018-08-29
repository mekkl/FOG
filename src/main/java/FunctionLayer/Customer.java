/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class Customer {
    
    private String email;
    private String name;
    private String surname;
    private int phonenumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String address;
    private int zipcode;
    private String password; 
    private String city;

    public Customer(String email, String name, String surname, int phonenumber, String address, int zipcode, String password, String city) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.zipcode = zipcode;
        this.password = password;
        this.city = city;
    }

    public List<String> attributeNames(){
        List<String> list = new ArrayList<>();
        
        list.add("email");
        list.add("name");
        list.add("surname");
        list.add("phonenumber");
        list.add("address");
        list.add("zipcode");
        list.add("city");
        
        return list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" + "email=" + email + ", name=" + name + ", surname=" + surname + ", phonenumber=" + phonenumber + ", address=" + address + ", zipcode=" + zipcode + ", password=" + password + ", city=" + city + '}';
    }

    
    
}
