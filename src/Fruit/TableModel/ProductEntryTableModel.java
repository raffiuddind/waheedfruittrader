package Fruit.TableModel;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import Fruit.DAL.ProductDAL;
import Fruit.Model.ProductEntryModel;


/**
 *
 * @author Comp
 */
public class ProductEntryTableModel extends AbstractTableModel {
    

	private List<ProductEntryModel> lstOfProducts;
	private String[] columns;

	public ProductEntryTableModel(List<ProductEntryModel> mediumList) {
		super();
		lstOfProducts = mediumList;
		columns = new String[] { "", "Medium Name",
				"Medium_ID", "Status", "" };
	}
        public ProductEntryTableModel() {
            
		super();
		ProductDAL medDAL = new ProductDAL();
		lstOfProducts = medDAL.getProductModelList();
		columns = new String[] { "Product", "Short Cut"
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
		ProductEntryModel mediumMdl = lstOfProducts.get(row);
		switch (col) {
		case 0:
			return mediumMdl.getProductName();
		case 1:
			return mediumMdl.getProductNameShort();
//                case 2:
//                        return mediumMdl.getMediumID();
					
		default:
			return null;
		}
	}
	
	// get selected medium model
	public ProductEntryModel getModel(int row) {
		ProductEntryModel mediumMdl = lstOfProducts.get(row);
		return mediumMdl;
	}

	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
    public void setValueAt(Object value, int row, int col) {
    	
    	if(col == 0)
    	{
    		ProductEntryModel mediumMdl = lstOfProducts.get(row);
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
