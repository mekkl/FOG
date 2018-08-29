/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class Inquiry {

    private int id;
    private int carportHeight;
    private int carportLength;
    private int carportWidth;
    private int shackWidth;
    private int shackLength;
    private boolean withShack;
    private String roofType;  //enum
    private String roofMaterial;
    private String angle;          //enum
    private String commentCustomer;
    private String commentEmployee;
    private Date period;
    private String status;      //enum
    private String email;

    public void setId(int id) {
        this.id = id;
    }
    private int id_employee;
    private BillOfMaterials bom;
    private Timestamp date;

    public Inquiry(int id, int carportHeight, int carportLength, int carportWidth, int shackWidth, int shackLength, String roofType, String roofMaterial, String angle, String commentCustomer, String commentEmployee, Date period, String status, String email, int id_employee, Timestamp date) {
        this.id = id;
        this.carportHeight = carportHeight;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shackWidth = shackWidth;
        this.shackLength = shackLength;
        this.roofType = roofType;
        this.roofMaterial = roofMaterial;
        this.angle = angle;
        this.commentCustomer = commentCustomer;
        this.commentEmployee = commentEmployee;
        this.period = period;
        this.status = status;
        this.email = email;
        this.id_employee = id_employee;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCarportHeight() {
        return carportHeight;
    }

    public BillOfMaterials getBom() {
        return bom;
    }

    public void setBom(BillOfMaterials bom) {
        this.bom = bom;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public boolean isWithShack() {
        return withShack;
    }

    public void setWithShack(boolean withShack) {
        this.withShack = withShack;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public int getShackWidth() {
        return shackWidth;
    }

    public int getShackLength() {
        return shackLength;
    }

    public String getRoofType() {
        return roofType;
    }

    public String getRoofMaterial() {
        return roofMaterial;
    }
    
    public String getAngle() {
        return angle;
    }

    public String getCommentCustomer() {
        return commentCustomer;
    }

    public String getCommentEmployee() {
        return commentEmployee;
    }

    public Date getPeriod() {
        return period;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public int getId_employee() {
        return id_employee;
    }

    public Timestamp getDate() {
        return date;
    }
}
