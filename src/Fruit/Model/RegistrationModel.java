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
public class RegistrationModel {
    
    private int csID;
    private String name;

    private String mobile;
    private String mobile2;
    private byte[] photo;
    private String city;
    private int pinNo;
    private String state;
    private String plotNo;
    private String nativ;

    private boolean active;
    
    private boolean isWatsupNo;
    
    public void setIsWatsupNo(boolean isWatsupNo){
        this.isWatsupNo = isWatsupNo;
    }
    public boolean isItWatsUpNo(){
        return isWatsupNo;
    }

    
    public RegistrationModel(int csID, String name, String mobile, String mobile2, byte[] photo, String city, int pinNo, String state, String plotNo,boolean active,String nat) {
        this.csID = csID;
        this.name = name;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.photo = photo;
        this.city = city;
        this.pinNo = pinNo;
        this.state = state;
        this.plotNo = plotNo;
        this.active = active;
        this.nativ = nat;
    }

    
    public String getNativ() {
        return nativ;
    }

    public void setNativ(String nativ) {
        this.nativ = nativ;
    }
    @Override
    public String toString() {
        return  name ;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCsID() {
        return csID;
    }

    public void setCsID(int csID) {
        this.csID = csID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinNo() {
        return pinNo;
    }

    public void setPinNo(int pinNo) {
        this.pinNo = pinNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }
    
}
