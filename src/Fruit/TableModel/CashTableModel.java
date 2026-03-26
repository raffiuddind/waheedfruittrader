/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.TableModel;

import Fruit.Model.CashOnDateModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raffiuddin
 */
public class CashTableModel extends AbstractTableModel{
    private List<CashOnDateModel> lstOfProducts;
	private String[] columns;

	public CashTableModel(List<CashOnDateModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#", "Name",
				"City", "Amount","Discount", "Date"};
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
		CashOnDateModel rec = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
			return rec.getCusName();
                case 2:
                        return rec.getCity();
                case 3:
                        return rec.getCash();
                case 4:
                        return rec.getDiscount();
                case 5: 
                        return rec.getDate();
                
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public CashOnDateModel getModel(int row) {
		CashOnDateModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
            
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		CashOnDateModel mediumMdl = lstOfProducts.get(row);
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
	    
	    return super.getColumnClass(columnIndex);
	}
        
    
}
