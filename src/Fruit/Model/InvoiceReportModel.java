/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author Raffiuddin
 */
public class InvoiceReportModel {
    
    private int sno;
    private int loadID;
    private String invoiceID;
    private String invoiceDate;
    private String name;
    private String v_No;
    private String stock;
    private String sold;
    private String balace;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getLoadID() {
        return loadID;
    }

    public void setLoadID(int loadID) {
        this.loadID = loadID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }
    
    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getV_No() {
        return v_No;
    }

    public void setV_No(String v_No) {
        this.v_No = v_No;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getBalace() {
        return balace;
    }

    public void setBalace(String balace) {
        this.balace = balace;
    }

    public InvoiceReportModel(int sno, int loadID, String invoiceDate, String name, String v_No, String stock, String sold, String balace) {
        this.sno = sno;
        this.loadID = loadID;
        this.invoiceDate = invoiceDate;
        this.name = name;
        this.v_No = v_No;
        this.stock = stock;
        this.sold = sold;
        this.balace = balace;
    }
    
}
