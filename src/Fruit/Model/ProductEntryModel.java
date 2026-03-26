/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.Model;

import java.util.Date;

/**
 *
 * @author raffiuddin
 */
public class ProductEntryModel {
    
    private String productName;
    private String productNameShort;
    private boolean active;
    private String creted;
    private int pId;

    @Override
    public String toString() {
        return  productName ;
    }
    

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public ProductEntryModel(String productName, String productNameShort, boolean active, String creted, int pId) {
        this.productName = productName;
        this.productNameShort = productNameShort;
        this.active = active;
        this.creted = creted;
        this.pId = pId;
    }

   

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameShort() {
        return productNameShort;
    }

    public void setProductNameShort(String productNameShort) {
        this.productNameShort = productNameShort;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreted() {
        return creted;
    }

    public void setCreted(String creted) {
        this.creted = creted;
    }
    
    
    
}
