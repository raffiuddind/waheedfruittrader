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
public class InvoiceEntryModel {
    
    InvoiceModel invoiceModel;
    private int invTotal;

    public InvoiceEntryModel(InvoiceModel invoiceModel, int invTotal) {
        this.invoiceModel = invoiceModel;
        this.invTotal = invTotal;
    }

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    public int getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(int invTotal) {
        this.invTotal = invTotal;
    }
    
}
