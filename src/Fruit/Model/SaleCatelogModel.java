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
public class SaleCatelogModel {
    
    private ProductCategoryModel categoryModel;
    private SaleTransactionModel transactionModel;
    private int invoiceId;

    public SaleCatelogModel(ProductCategoryModel categoryModel, SaleTransactionModel transactionModel, int invoiceId) {
        this.categoryModel = categoryModel;
        this.transactionModel = transactionModel;
        this.invoiceId = invoiceId;
    }

    
    
    
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    

   

    public ProductCategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(ProductCategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public SaleTransactionModel getTransactionModel() {
        return transactionModel;
    }

    public void setTransactionModel(SaleTransactionModel transactionModel) {
        this.transactionModel = transactionModel;
    }
    
}
