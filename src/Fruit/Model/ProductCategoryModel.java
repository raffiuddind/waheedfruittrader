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
public class ProductCategoryModel {
    
    private String categoryName;
    private String categoryNameShort;
    private boolean active;
    private String creted;
    private int catId;
    private int proCatId;
    
    private ProductEntryModel productEntryModel;

    
    @Override
    public String toString() {
        return  categoryName ;
    }
    public ProductCategoryModel(String categoryName, String categoryNameShort, boolean active, String creted, int catId, int proCatId, ProductEntryModel productEntryModel) {
        this.categoryName = categoryName;
        this.categoryNameShort = categoryNameShort;
        this.active = active;
        this.creted = creted;
        this.catId = catId;
        this.proCatId = proCatId;
        this.productEntryModel = productEntryModel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameShort() {
        return categoryNameShort;
    }

    public void setCategoryNameShort(String categoryNameShort) {
        this.categoryNameShort = categoryNameShort;
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

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getProCatId() {
        return proCatId;
    }

    public void setProCatId(int proCatId) {
        this.proCatId = proCatId;
    }

    public ProductEntryModel getProductEntryModel() {
        return productEntryModel;
    }

    public void setProductEntryModel(ProductEntryModel productEntryModel) {
        this.productEntryModel = productEntryModel;
    }
    
}
