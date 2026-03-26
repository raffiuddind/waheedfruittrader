/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author raffiuddin
 */
public abstract class TransactionModel {
    
    private int qnty;
    private int unitCost;
    private int unitTotal;
    private int productId;
    private int prodCatId;
    private int transactionId;

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public int getUnitTotal() {
        return unitTotal;
    }

    public void setUnitTotal(int unitTotal) {
        this.unitTotal = unitTotal;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProdCatId() {
        return prodCatId;
    }

    public void setProdCatId(int prodCatId) {
        this.prodCatId = prodCatId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
   
}
