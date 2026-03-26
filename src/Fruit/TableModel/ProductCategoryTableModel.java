/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.TableModel;

import Fruit.DAL.ProductDAL;
import Fruit.Model.ProductCategoryModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author raffiuddin
 */
public class ProductCategoryTableModel extends AbstractTableModel {
    

	private List<ProductCategoryModel> lstOfProducts;
	private String[] columns;

	public ProductCategoryTableModel(List<ProductCategoryModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "", "Medium Name",
				"Medium_ID", "Status", "" };
	}
        public ProductCategoryTableModel() {
            
		super();
		ProductDAL medDAL = new ProductDAL();
		lstOfProducts = medDAL.getProductCatModelList();
		columns = new String[] { "Product", "Pro. Shortcut","Category","Cat. Shortcut"
			 };
                
	}
        
	
        
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
		ProductCategoryModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return mediumMdl.getProductEntryModel().getProductName();
		case 1:
			return mediumMdl.getProductEntryModel().getProductNameShort();
                case 2:
                        return mediumMdl.getCategoryName();
                case 3:
                        return mediumMdl.getCategoryNameShort();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public ProductCategoryModel getModel(int row) {
		ProductCategoryModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		ProductCategoryModel mediumMdl = lstOfProducts.get(row);
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
//	    if (columnIndex == 0)
//	        return Boolean.class;
	    return super.getColumnClass(columnIndex);
	}
        
        
}
