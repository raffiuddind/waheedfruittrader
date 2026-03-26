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
public class ExpenditureModel {
    
    private int expId;
    private String expend_Name;
    private String created;
    private String updated;
    private boolean active;

    public ExpenditureModel() {
    }
    

    public ExpenditureModel(int expId, String expend_Name, String created, String updated) {
        this.expId = expId;
        this.expend_Name = expend_Name;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return  expend_Name ;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getExpend_Name() {
        return expend_Name;
    }

    public void setExpend_Name(String expend_Name) {
        this.expend_Name = expend_Name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
