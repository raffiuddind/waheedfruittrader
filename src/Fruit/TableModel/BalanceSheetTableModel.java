/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.TableModel;

import com.User.StatementModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author raffiuddin
 */
public class BalanceSheetTableModel extends AbstractTableModel{
    private List<StatementModel> lstOfProducts;
	private String[] columns;

	public BalanceSheetTableModel(List<StatementModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#", "Name",
				 "Mobile", "Date", 
                                "Pre-balance", "Balance"};
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
		StatementModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
			return mediumMdl.getItems();
                case 2:
                        return mediumMdl.getCashPaid();
                case 3:
                        return mediumMdl.getPtotal();
                case 4:
                        return mediumMdl.getDate();
                case 5:
                        return mediumMdl.getTotalBalance();
                
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public StatementModel getModel(int row) {
		StatementModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
            
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		StatementModel mediumMdl = lstOfProducts.get(row);
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

