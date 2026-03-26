/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.TableModel;

import Fruit.Model.RegistrationModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raffiuddin
 */
public class CustomerEditTableModel extends AbstractTableModel{
    private List<RegistrationModel> lstOfProducts;
	private String[] columns;

	public CustomerEditTableModel(List<RegistrationModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#", "Name",
				"City", "Mobile","Contact No.","H.No","State","Pin No.","Native"};
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
		RegistrationModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
			return mediumMdl.getName();
                case 2:
                        return mediumMdl.getCity();
                case 3:
                        return mediumMdl.getMobile();
                case 4:
                        return mediumMdl.getMobile2();
                case 5:
                        return mediumMdl.getPlotNo();
                case 6:
                        return mediumMdl.getState();
                case 7:
                        return mediumMdl.getPinNo();
                case 8:
                        return mediumMdl.getNativ();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public RegistrationModel getModel(int row) {
		RegistrationModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
            
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		RegistrationModel mediumMdl = lstOfProducts.get(row);
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
