/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.Common;

import Fruit.DAL.CustomerDAL;
import Fruit.DAL.ExpenditureDAL;
import Fruit.DAL.ProductDAL;
import Fruit.DAL.SupplierDAL;
import Fruit.Model.ExpenditureModel;
import Fruit.Model.ProductCategoryModel;
import Fruit.Model.ProductEntryModel;
import Fruit.Model.RegistrationModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;

/**
 *
 * @author raffiuddin
 */

public class CommonMethods {

    public CommonMethods() {
    }
    ProductDAL productDAL = new ProductDAL();
    CustomerDAL customerDAL = new CustomerDAL();
    ExpenditureDAL edal = new ExpenditureDAL();
    Fruit.DAL.SupplierDAL supplierDAL = new SupplierDAL();
    SimpleDateFormat yyyy_MM_dd_format = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat dd_MM_yyyy_format = new SimpleDateFormat("dd/MM/yyyy");
//    private static FormatStringValue stringValue = new FormatStringValue(dd_MM_yyyy_format);
//    
    
    public String datesaveformat(Date d){
        String newdate = yyyy_MM_dd_format.format(d);
       
        return newdate;
    }
    public String dateviewformat(Date d){
        String newdate = dd_MM_yyyy_format.format(d);
        return newdate;
    }
    public long sum(long a,long b){
        long sum = a + b ;
        return sum;
    }
    public long sub(long a,long b){
        long sum = a - b ;
        return sum;
    }
    public long mul(long a,long b){
        long sum = a * b ;
        return sum;
    }
    public long div(long a,long b){
        long sum = a / b ;
        return sum;
    }
    public int sum(int a,int b){
        int sum = a + b ;
        return sum;
    }
    public int sub(int a,int b){
        int sum = a - b ;
        return sum;
    }
    public int mul(int a,int b){
        int sum = a * b ;
        return sum;
    }
    public int div(int a,int b){
        int sum = a / b ;
        return sum;
    }
    public float sum(float a,float b){
        float sum = a + b ;
        return sum;
    }
    public float sub(float a,float b){
        float sum = a - b ;
        return sum;
    }
    public float mul(float a,float b){
        float sum = a * b ;
        return sum;
    }
    public float div(float a,float b){
        float sum = a / b ;
        return sum;
    }
    public String firstLeterToCap(String name){
        try {
            if(name.isEmpty() || name.equals(null)){
        
        } else if(name.length() >= 1) {
            char ch = name.charAt(0);
            
            char chn = Character.toUpperCase(ch);
            name = name.replace(ch, chn);
            
            
        }
            
        } catch (Exception e) {
        }
        
        return name;
        
    }
    public int rupeeToInt(String rupee){
        int rt=0;
        try {
            
            if(rupee.trim().isEmpty())
                return rt;
            else
                rt = Integer.parseInt((rupee.replaceAll(",", "")).trim());
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         return rt;
    }
    public String rupee(String s){
        try {
            if(s==null){
             s = ""; return s;
        } else {
            
        if(s.length()>3){
         s =   s.replaceAll(",", "").trim();
        StringBuilder sb1 = new StringBuilder(s);
        s = sb1.reverse().toString();
        String str = s.substring(3);
        s = s.substring(0, 3);
//        System.out.println("s" +str);
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<str.length();i++){
            if(i%2==0)
                sb.append(",");
            sb.append(str.charAt(i));
//            System.out.println(sb);
        }
        
        StringBuilder n = new StringBuilder(s + sb.toString());
        s = n.reverse().toString();
        }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return s;
    }
    public List<ProductEntryModel> loadProductComboo(){
        List<ProductEntryModel> entryModels = productDAL.getProductModelList();
        return entryModels;
        
    }
    public List<ProductCategoryModel> loadProductCategoryComboo(){
        List<ProductCategoryModel> entryModels = productDAL.getProductCatModelList();
        return entryModels;
        
    }
    public List<ProductCategoryModel> loadProductCategoryCombooByProduct(int pId){
        List<ProductCategoryModel> entryModels = productDAL.getProductCatModelList(pId);
        return entryModels;
        
    }
    public List<ExpenditureModel> loadExpenditure(){
        List<ExpenditureModel> entryModels = edal.getExpenditureName();
        return entryModels;
        
    }
    
    public DefaultComboBoxModel update_combo_expend() {
        
//        Object[] eduTypes = edal.getExpenditureName().toArray();
        DefaultComboBoxModel newModel = new DefaultComboBoxModel(edal.getExpenditureName().toArray());
//        System.out.println("length"+eduTypes.length);
        newModel.insertElementAt("Select",0);
        newModel.setSelectedItem("Select");
        return newModel;       
        
    }
    public List<RegistrationModel> getCustomers(){
        List<RegistrationModel> entryModels = customerDAL.getCustomerList();
//        List list = new ArrayList();
//        for(int i=0; i < entryModels.size(); i++){
////            System.out.println(""+entryModels.get(i).toString());
//        list.add(entryModels.get(i).toString());
//        }
        return entryModels;
        
    }
    public List<RegistrationModel> getSuppliers(){
        List<RegistrationModel> entryModels = supplierDAL.getSupplierModelList();
//        List list = new ArrayList();
//        for(int i=0; i < entryModels.size(); i++){
////            System.out.println(""+entryModels.get(i).toString());
//        list.add(entryModels.get(i).toString());
//        }
        return entryModels;
        
    }
    
    public int getJListIndex(ListModel l,String obj){
        int index=-1;
        for(int i=0; i<l.getSize(); i++)
        {
            if(l.getElementAt(i).toString().equalsIgnoreCase(obj.trim()))
                index=i;
        }
        return index;
        
    }
    
}
