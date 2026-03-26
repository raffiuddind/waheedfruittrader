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
public class SaleEntryTrxModel {
    private int cusId;
    private int total;
    private int paid;
    private int balance;
    private int invoiceId;
    private String transDate;

    public SaleEntryTrxModel(int cusId, int total, int paid, int balance, int invoiceId, String transDate) {
        this.cusId = cusId;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
        this.invoiceId = invoiceId;
        this.transDate = transDate;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    
    
}
