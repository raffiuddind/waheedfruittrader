/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.Model;

/**
 *
 * @author raffiuddin
 */
public class SaleTransactionModel {
    
    private int qnty;
    private int ucost;
    private int utot;
    private int pid;
    private int tId;
    private int invoiceId;
    private int suplierId;
    private int loadId;

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }
    public int getSuplierId() {
        return suplierId;
    }

    public void setSuplierId(int suplierId) {
        this.suplierId = suplierId;
    }

    public SaleTransactionModel(int qnty, int ucost, int utot, int pid, int tId, int invoiceId) {
        this.qnty = qnty;
        this.ucost = ucost;
        this.utot = utot;
        this.pid = pid;
        this.tId = tId;
        this.invoiceId = invoiceId;
    }

    

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }
    

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public int getUcost() {
        return ucost;
    }

    public void setUcost(int ucost) {
        this.ucost = ucost;
    }

    public int getUtot() {
        return utot;
    }

    public void setUtot(int utot) {
        this.utot = utot;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

  
    
}
