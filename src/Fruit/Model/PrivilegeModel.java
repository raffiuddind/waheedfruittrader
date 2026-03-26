/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author Raffiuddin
 */
public class PrivilegeModel {
    
    private int id;
    private String panelName;

    @Override
    public String toString() {
        return  panelName ;
    }
    private boolean active;

    public PrivilegeModel(int id, String panelName, boolean active) {
        this.id = id;
        this.panelName = panelName;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
