/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.TableModel;

import Fruit.ReportModel.BalanceModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raffiuddin
 */
public class InvoicesTableModel extends AbstractTableModel{
    private List<BalanceModel> lstOfProducts;
	private String[] columns;

	public InvoicesTableModel(List<BalanceModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "Item", "Quantity",
				"Unit Cost", "Total"};
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
		BalanceModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
//		case 0:
//			return row+1;
		case 0:
			return mediumMdl.getName();
                case 1:
                        return mediumMdl.getCity();
                case 2:
                        return mediumMdl.getMobile();
                case 3:
                        return mediumMdl.getBalance();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public BalanceModel getModel(int row) {
		BalanceModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		BalanceModel mediumMdl = lstOfProducts.get(row);
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

