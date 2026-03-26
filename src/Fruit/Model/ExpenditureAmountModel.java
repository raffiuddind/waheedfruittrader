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
import Fruit.Model.ExpenditureModel;
public class ExpenditureAmountModel {
    
    private int expAmtId;
    private ExpenditureModel expenditureModel;
    private String amt;
    private String expDate;
    private String upDatedON;

    @Override
    public String toString() {
        return "ExpenditureAmountModel{" + "expAmtId=" + expAmtId + ", expenditureModel=" + expenditureModel + ", amt=" + amt + ", expDate=" + expDate + ", upDatedON=" + upDatedON + '}';
    }

    public int getExpAmtId() {
        return expAmtId;
    }

    public void setExpAmtId(int expAmtId) {
        this.expAmtId = expAmtId;
    }

    public ExpenditureModel getExpenditureModel() {
        return expenditureModel;
    }

    public void setExpenditureModel(ExpenditureModel expenditureModel) {
        this.expenditureModel = expenditureModel;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getUpDatedON() {
        return upDatedON;
    }

    public void setUpDatedON(String upDatedON) {
        this.upDatedON = upDatedON;
    }

    public ExpenditureAmountModel(int expAmtId, ExpenditureModel expenditureModel, String amt, String expDate, String upDatedON) {
        this.expAmtId = expAmtId;
        this.expenditureModel = expenditureModel;
        this.amt = amt;
        this.expDate = expDate;
        this.upDatedON = upDatedON;
    }
   
    
}
