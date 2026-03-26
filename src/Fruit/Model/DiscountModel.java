/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author Raffiuddin
 */
public class DiscountModel {
    
    private int id;
    private int amount;
    private int bal;
    private int cId;
    private String msg;
    private String dt;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public DiscountModel(int id, int amount, int bal, int cid, String msg, String dt, String name) {
        this.id = id;
        this.amount = amount;
        this.bal = bal;
        this.cId = cid;
        this.msg = msg;
        this.dt = dt;
        this.name = name;
    }

    

    

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }
    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
    
    
}
