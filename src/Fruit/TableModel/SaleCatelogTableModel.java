/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.TableModel;

import Fruit.Model.SaleCatelogModel;
import java.util.List;

/**
 *
 * @author raffiuddin
 */
public class SaleCatelogTableModel extends javax.swing.table.AbstractTableModel{

    
    private List<SaleCatelogModel> lstOfProducts;
	private String[] columns;
    Fruit.Common.CommonMethods cm;
	public SaleCatelogTableModel(List<SaleCatelogModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "Items", "Category",
				"Quantity", "UnitCost", "Total" };
	}
//        public SaleCatelogTableModel() {
//            
//		super();
//		ProductDAL medDAL = new ProductDAL();
//		lstOfProducts = medDAL.getProductCatModelList();
//		columns = new String[] { "Product", "Pro. Shortcut","Category","Cat. Shortcut"
//			 };
//                
//	}
        
	
        
	// Number of column of your table
	public int getColumnCount() {
		return columns.length;
	}

    
        
	// Number of row of your table
	public int getRowCount() {
		return lstOfProducts.size();
	}

	// The object to render in a cell
	public Object getValueAt(int row, int col) {
		SaleCatelogModel mediumMdl = lstOfProducts.get(row);
		
                switch (col) {
		
                case 0:
			return mediumMdl.getCategoryModel().getProductEntryModel().getProductName();
		case 1:
			return mediumMdl.getCategoryModel().getCategoryName();
                case 2:
                        return mediumMdl.getTransactionModel().getQnty();
                case 3:
                        return mediumMdl.getTransactionModel().getUcost();
                case 4:
                        return mediumMdl.getTransactionModel().getUtot();
                default:
			return null;
		}
	}
	
	// get selected medium model
	public SaleCatelogModel getModel(int row) {
		SaleCatelogModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		SaleCatelogModel mediumMdl = lstOfProducts.get(row);
    		for(int i = 0; i < lstOfProducts.size(); i++)
                {
//                    mediumMdl.setRowSelectFlg(false);
                    fireTableCellUpdated(i, col);
                    this.fireTableDataChanged();
                }
//                mediumMdl.setRowSelectFlg((Boolean)value);
    		fireTableCellUpdated(row, col);
    	}
    }

	// Optional, the name of your column
	public String getColumnName(int col) {
		return columns[col];
	}
	
	public Class<?> getColumnClass(int columnIndex) {
	    if (columnIndex == 2 || columnIndex == 3 || columnIndex == 4)
	        return Number.class;
	    return super.getColumnClass(columnIndex);
	}
        
        
}
