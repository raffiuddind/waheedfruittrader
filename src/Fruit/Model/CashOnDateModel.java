/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author raffiuddin
 */
public class CashOnDateModel {
    
    private int sNo;
    private String cusName;
    private String city;
    private String cash;
    private String date;
    private String details;
    private String discount;
    private String totalCollection; //adding this since we are getting total in one query

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
    
    public String getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(String totalCollection) {
        this.totalCollection = totalCollection;
    }
    
    public CashOnDateModel(int sNo, String cusName, String city, String cash, String date, String details, String discount) {
        this.sNo = sNo;
        this.cusName = cusName;
        this.city = city;
        this.cash = cash;
        this.date = date;
        this.details = details;
        this.discount = discount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CashOnDateModel{" + "sNo=" + sNo + ", cusName=" + cusName + ", city=" + city + ", cash=" + cash + ", date=" + date + ", details=" + details + '}';
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

                
}
