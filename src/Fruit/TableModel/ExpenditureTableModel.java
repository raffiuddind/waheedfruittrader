/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.TableModel;

import Fruit.Common.CommonMethods;
import com.User.StatementModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Fruit.Model.ExpenditureAmountModel;

/**
 *
 * @author raffiuddin
 */
public class ExpenditureTableModel extends AbstractTableModel{
    private List<ExpenditureAmountModel> lstOfProducts;
	private String[] columns;
        Fruit.Common.CommonMethods cm = new CommonMethods();

	public ExpenditureTableModel(List<ExpenditureAmountModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#","Date", "Expenditure",
				 "Cash"};
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
		ExpenditureAmountModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
			return (mediumMdl.getExpDate());
		case 2:
			return mediumMdl.getExpenditureModel().getExpend_Name();
                case 3:
                        return mediumMdl.getAmt();
//                case 3:
//                        return mediumMdl.getCashPaid();
//                case 4:
//                        return mediumMdl.getTotalBalance();
                
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public ExpenditureAmountModel getModel(int row) {
		ExpenditureAmountModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
            
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		ExpenditureAmountModel mediumMdl = lstOfProducts.get(row);
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

    

