/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Orchi
 */
public class OrderLine {

    Product product;
    int length;
    int quantity;
    // piece, roll, pack, set (stk, rulle, pakke, sæt)
    String amountType;
    String usabilityComment;



    public long getOrderLinePrice() {
        return (product.getPrice() /100) * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getUsabilityComment() {
        return usabilityComment;
    }

    public void setUsabilityComment(String usabilityComment) {
        this.usabilityComment = usabilityComment;
    }

    public OrderLine(Product product, int length, int quantity, String amountType, String usabilityComment) {
        this.product = product;
        this.length = length;
        this.quantity = quantity;
        this.amountType = amountType;
        this.usabilityComment = usabilityComment;
    }
    
    public String getProductName(){
        return product.getName();
    }
    
    public String getProductCategory(){
        return product.getCategory();
    }

}
