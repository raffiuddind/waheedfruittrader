/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.TableModel;

import Fruit.Common.CommonMethods;
import Fruit.Model.DiscountModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raffiuddin
 */
public class Discount_Damage_PreBalanceTableModel extends AbstractTableModel{
    private List<DiscountModel> lstOfProducts;
	private String[] columns;
        private Fruit.Common.CommonMethods methods = new CommonMethods();

	public Discount_Damage_PreBalanceTableModel(List<DiscountModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#", "Date","Name",
				"Amount", "Description"};
	}
//        public ProductCategoryTableModel() {
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
		DiscountModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
                        return methods.dateviewformat(java.sql.Date.valueOf(mediumMdl.getDt()));
			
                case 2:
                        return mediumMdl.getName();
                    
                case 3:
                        return methods.rupee(String.valueOf(mediumMdl.getAmount()));
                        
                case 4:
                        return mediumMdl.getMsg();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public DiscountModel getModel(int row) {
		DiscountModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		DiscountModel mediumMdl = lstOfProducts.get(row);
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
