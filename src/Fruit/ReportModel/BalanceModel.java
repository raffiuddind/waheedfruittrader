/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.ReportModel;

/**
 *
 * @author Raffiuddin
 */
public class BalanceModel {
    
    private int sno;
    private String name;
    private String city;
    private String mobile;
    private String balance;

    public BalanceModel(int sno, String name, String city, String mobile, String balance) {
        this.sno = sno;
        this.name = name;
        this.city = city;
        this.mobile = mobile;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name;
    }
    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
