/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.TableModel;

import Fruit.Model.InvoiceReportModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raffiuddin
 */
public class DetailedInvoiceReportTableModel extends AbstractTableModel{
    private List<InvoiceReportModel> lstOfProducts;
	private String[] columns;

	public DetailedInvoiceReportTableModel(List<InvoiceReportModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "#", "Date",
				"Name", "Invoice Id", "Truck No","Stock","Sold","Balance"};
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
		InvoiceReportModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return row+1;
		case 1:
			return mediumMdl.getInvoiceDate();
                case 2:
                        return mediumMdl.getName();
                case 4:
                        return mediumMdl.getV_No();
                case 5:
                        return mediumMdl.getStock();
                case 6:
                        return mediumMdl.getSold();
                case 7:
                        return mediumMdl.getBalace();
                case 3:
                        return mediumMdl.getInvoiceID();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public InvoiceReportModel getModel(int row) {
		InvoiceReportModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		InvoiceReportModel mediumMdl = lstOfProducts.get(row);
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
//	    if (columnIndex == columns.length-1)
//	        return javax.swing.JButton.class;
	    return super.getColumnClass(columnIndex);
	}
        
    
}

