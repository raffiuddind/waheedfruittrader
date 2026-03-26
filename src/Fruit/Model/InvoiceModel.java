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
public class InvoiceModel {
    
    private int invoiceId;
    private int qnty;
    private int stock;
    private int sold;
    private String dt;
    private int loadId;
    private int supId;
    private String trkNo;
    private String trnsprt;
    
    
    private boolean active;

    public InvoiceModel(int invoiceId, int qnty,int stock,int sold, String dt, int loadId, int supId, String trkNo, String trnsprt, boolean active) {
        this.invoiceId = invoiceId;
        this.qnty = qnty;
        this.stock = stock;
        this.sold = sold;
        this.dt = dt;
        this.loadId = loadId;
        this.supId = supId;
        this.trkNo = trkNo;
        this.trnsprt = trnsprt;
        this.active = active;
    }

    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
  
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public String getTrkNo() {
        return trkNo;
    }

    public void setTrkNo(String trkNo) {
        this.trkNo = trkNo;
    }

    public String getTrnsprt() {
        return trnsprt;
    }

    public void setTrnsprt(String trnsprt) {
        this.trnsprt = trnsprt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
