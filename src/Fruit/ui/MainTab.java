/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.ui;

import Fruit.DAL.PrivilegeDAL;
import Fruit.DAL.ReportDAL;
import Fruit.Model.PrivilegeModel;
import Fruit.component.util.TableCustomiseUtil;
import com.User.StatementModel;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author raffiuddin
 */
public class MainTab extends javax.swing.JFrame {

    /**
     * Creates new form MainTab
     */
    static Entry enty = new Entry();
    private static MainTab mainTab = null;
    String str = "cus";
    Connection conn;
    List<PrivilegeModel> privilegeModels;
    
    
    static org.apache.logging.log4j.Logger log = LogManager.getLogger(MainTab.class);
    public MainTab() {
       
        log.info("MAin class");
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Fruit/Resource/strawberry.png")));
//        try {
//            UIManager.setLookAndFeel(new MetalLookAndFeel());
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(MainTab.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try {
            //        MainTab.super.setIconImage(new ImageIcon("refresh.png").getImage());
            conn = Fruit.Common.DbConnection.getConnection();
        } catch (Exception ex) {
            log.error(ex);
        }
            PrivilegeDAL aL = new PrivilegeDAL();
            privilegeModels = aL.getPrivilegeStatus();
        if(LoginFrame.pvg.getSelectedItem().toString().equalsIgnoreCase("operator")) {
        try {
            
            for (int i = 0; i < privilegeModels.size(); i++) {
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Customer Registration")) {
                    customerRegistration.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Update Customer")) {
                    customerEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Sale Entry Form")) {
                    entryForm.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Sale Edit")) {
                    entryEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Previous Balance Edit")) {
                    editPreBalance.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Paid Edit")) {
                    paidEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Edit Discount/Damage")) {
                    discount_damageEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Back Up")) {
                    buackup.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Add Product")) {
                    addProduct.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier Registration")) {
                    supplierRegistration.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Update Supplier")) {
                    supplierEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Purchase Form")) {
                    invoiceForm.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Edit Purchase")) {
                    invoiceEdit.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Balance Sheet")) {
                    balanceSheetReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Invoice Report")) {
                    invoiceReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier Sale Report")) {
                    supplierSaleReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Statement Report")) {
                    customerStatementReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Customers List Report")) {
                    customerList.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Cash On Date")) {
                    cash_On_DataReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier List Report")) {
                    supplierListReport.setVisible(false);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Final Report")) {
                    finalReport.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
            
            for (int i = 0; i < privilegeModels.size(); i++) {
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Customer Registration")) {
                    //customerRegistration.setVisible(false);
                    previlegeTable.getModel().setValueAt(true, 0, 2);
                    
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Update Customer")) {
                    previlegeTable.getModel().setValueAt(true, 1, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Sale Entry Form")) {
                    previlegeTable.getModel().setValueAt(true, 2, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Sale Edit")) {
                    previlegeTable.getModel().setValueAt(true, 3, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Previous Balance Edit")) {
                    previlegeTable.getModel().setValueAt(true, 4, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Paid Edit")) {
                    previlegeTable.getModel().setValueAt(true, 5, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Edit Discount/Damage")) {
                    previlegeTable.getModel().setValueAt(true, 6, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Back Up")) {
                    previlegeTable.getModel().setValueAt(true, 7, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Add Product")) {
                    previlegeTable.getModel().setValueAt(true, 8, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier Registration")) {
                    previlegeTable.getModel().setValueAt(true, 9, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Update Supplier")) {
                    previlegeTable.getModel().setValueAt(true, 10, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Purchase Form")) {
                    previlegeTable.getModel().setValueAt(true, 11, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Edit Purchase")) {
                    previlegeTable.getModel().setValueAt(true, 12, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Balance Sheet")) {
                    previlegeTable.getModel().setValueAt(true, 13, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Invoice Report")) {
                    previlegeTable.getModel().setValueAt(true, 14, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier Sale Report")) {
                    previlegeTable.getModel().setValueAt(true, 15, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Statement Report")) {
                    previlegeTable.getModel().setValueAt(true, 16, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Customers List Report")) {
                    previlegeTable.getModel().setValueAt(true, 17, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Cash On Date")) {
                    previlegeTable.getModel().setValueAt(true, 18, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Supplier List Report")) {
                    previlegeTable.getModel().setValueAt(true, 19, 2);
                }
                if (privilegeModels.get(i).getPanelName().equalsIgnoreCase("Final Report")) {
                    previlegeTable.getModel().setValueAt(true, 20, 2);
                }
            }
    }
previlegeTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    public static MainTab getInstance() {

        if (mainTab == null) {
            mainTab = new MainTab();
        }
        TableCustomiseUtil.setFrameSize(mainTab);
        return mainTab;


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        customerRegistration = new javax.swing.JButton();
        entryForm = new javax.swing.JButton();
        addProduct = new javax.swing.JButton();
        entryEdit = new javax.swing.JButton();
        editPreBalance = new javax.swing.JButton();
        paidEdit = new javax.swing.JButton();
        discount_damageEdit = new javax.swing.JButton();
        customerEdit = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        supplierRegistration = new javax.swing.JButton();
        invoiceForm = new javax.swing.JButton();
        supplierEdit = new javax.swing.JButton();
        invoiceEdit = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        balanceSheetReport = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        invoiceReport = new javax.swing.JButton();
        supplierSaleReport = new javax.swing.JButton();
        customerStatementReport = new javax.swing.JButton();
        customerList = new javax.swing.JButton();
        cash_On_DataReport = new javax.swing.JButton();
        supplierListReport = new javax.swing.JButton();
        finalReport = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        previlegeTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        buackup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplication");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new java.awt.GridLayout(3, 0, 5, 5));

        customerRegistration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fruit/Resource/cusaddd.png"))); // NOI18N
        customerRegistration.setText("Customer");
        customerRegistration.setToolTipText("Add new Customer");
        customerRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRegistrationActionPerformed(evt);
            }
        });
        jPanel2.add(customerRegistration);

        entryForm.setText("Entry");
        entryForm.setToolTipText("Sale entry form");
        entryForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryFormActionPerformed(evt);
            }
        });
        jPanel2.add(entryForm);

        addProduct.setText("Add Product");
        addProduct.setToolTipText("Add new product");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });
        jPanel2.add(addProduct);

        entryEdit.setText("Transaction Edit");
        entryEdit.setToolTipText("To edit Sale entry");
        entryEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryEditActionPerformed(evt);
            }
        });
        jPanel2.add(entryEdit);

        editPreBalance.setText("Edit Prev. Balance");
        editPreBalance.setToolTipText("To edit privous balance of customer");
        editPreBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPreBalanceActionPerformed(evt);
            }
        });
        jPanel2.add(editPreBalance);

        paidEdit.setText("Edit Paid");
        paidEdit.setToolTipText("To edit amount paid by customer");
        paidEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidEditActionPerformed(evt);
            }
        });
        jPanel2.add(paidEdit);

        discount_damageEdit.setText("Edit Discount/Damage");
        discount_damageEdit.setToolTipText("To edit discount/damage of customers");
        discount_damageEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discount_damageEditActionPerformed(evt);
            }
        });
        jPanel2.add(discount_damageEdit);

        customerEdit.setText("Edit Customer");
        customerEdit.setToolTipText("To make changes in customer info.");
        customerEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerEditActionPerformed(evt);
            }
        });
        jPanel2.add(customerEdit);

        jButton4.setText("Change Password");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addGap(274, 274, 274))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sale", jPanel1);

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new java.awt.GridLayout(2, 3, 5, 5));

        supplierRegistration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fruit/Resource/picture-add-icon.png"))); // NOI18N
        supplierRegistration.setText("Supplier");
        supplierRegistration.setToolTipText("To add new supplier");
        supplierRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierRegistrationActionPerformed(evt);
            }
        });
        jPanel3.add(supplierRegistration);

        invoiceForm.setText("Invoice");
        invoiceForm.setToolTipText("Stock entry form");
        invoiceForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceFormActionPerformed(evt);
            }
        });
        jPanel3.add(invoiceForm);

        supplierEdit.setText("Edit Supplier");
        supplierEdit.setToolTipText("To make changes in supplier info.");
        supplierEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierEditActionPerformed(evt);
            }
        });
        jPanel3.add(supplierEdit);

        invoiceEdit.setText("Invoice Edit");
        invoiceEdit.setToolTipText("To edit Stock details");
        invoiceEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceEditActionPerformed(evt);
            }
        });
        jPanel3.add(invoiceEdit);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Invoice", jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        balanceSheetReport.setText("Balance Sheet");
        balanceSheetReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceSheetReportActionPerformed(evt);
            }
        });
        jPanel6.add(balanceSheetReport);

        jButton2.setText("Dead Balance Sheet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        invoiceReport.setText("Invoice Report");
        invoiceReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceReportActionPerformed(evt);
            }
        });
        jPanel6.add(invoiceReport);

        supplierSaleReport.setText("SupplierSaleReport");
        supplierSaleReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierSaleReportActionPerformed(evt);
            }
        });
        jPanel6.add(supplierSaleReport);

        customerStatementReport.setText("Statement");
        customerStatementReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerStatementReportActionPerformed(evt);
            }
        });
        jPanel6.add(customerStatementReport);

        customerList.setText("Customers List");
        customerList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerListActionPerformed(evt);
            }
        });
        jPanel6.add(customerList);

        cash_On_DataReport.setText("Cash On date");
        cash_On_DataReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cash_On_DataReportActionPerformed(evt);
            }
        });
        jPanel6.add(cash_On_DataReport);

        supplierListReport.setText("Supplier List");
        supplierListReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListReportActionPerformed(evt);
            }
        });
        jPanel6.add(supplierListReport);

        finalReport.setText("Supplier Wise Stock");
        finalReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalReportActionPerformed(evt);
            }
        });
        jPanel6.add(finalReport);

        jButton3.setText("EditReport");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);

        jTabbedPane1.addTab("Reports", jPanel6);

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));
        jPanel7.setToolTipText("To add restrictions to operator");

        previlegeTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        previlegeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Customer Registration", null},
                { new Integer(2), "Update Customer", null},
                { new Integer(3), "Sale Entry Form", null},
                { new Integer(4), "Sale Edit", null},
                { new Integer(5), "Previous Balance Edit", null},
                { new Integer(6), "Paid Edit", null},
                { new Integer(7), "Edit Discount/Damage", null},
                { new Integer(8), "Back Up", null},
                { new Integer(9), "Add Product", null},
                { new Integer(10), "Supplier Registration", null},
                { new Integer(11), "Update Supplier", null},
                { new Integer(12), "Purchase Form", null},
                { new Integer(13), "Edit Purchase", null},
                { new Integer(14), "Balance Sheet", null},
                { new Integer(15), "Invoice Report", null},
                { new Integer(16), "Supplier Sale Report", null},
                { new Integer(17), "Statement Report", null},
                { new Integer(18), "Customers List Report", null},
                { new Integer(19), "Cash On Date", null},
                { new Integer(20), "Supplier List Report", null},
                { new Integer(21), "Final Report", null}
            },
            new String [] {
                "S.No", "Title", "On/Off"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        previlegeTable.setToolTipText("");
        previlegeTable.setRowHeight(20);
        jScrollPane1.setViewportView(previlegeTable);

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));

        jButton1.setText("Save/Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jButton1)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin", jPanel7);

        buackup.setText("Back Up/ Restore");
        buackup.setToolTipText("To take backup of entire database");
        buackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buackup, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(688, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buackup, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("BackUp/ Restore", jPanel9);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void entryFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryFormActionPerformed
        EntryForm.getSaleEntryForm().setVisible(true);
    }//GEN-LAST:event_entryFormActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        ProductCreate.getInstance().setVisible(true);
    }//GEN-LAST:event_addProductActionPerformed

    private void customerRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRegistrationActionPerformed
        CustomerRegistration.getInstance().setVisible(true);
    }//GEN-LAST:event_customerRegistrationActionPerformed
    Fruit.DAL.ReportDAL reportDAL = new ReportDAL();

    private void entryEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryEditActionPerformed
        Fruit.ui.EntryEditForm.getInstance().setVisible(true);
    }//GEN-LAST:event_entryEditActionPerformed

    private void editPreBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPreBalanceActionPerformed
        new Fruit.ui.EditPre_Balance().setVisible(true);
    }//GEN-LAST:event_editPreBalanceActionPerformed

    private void paidEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidEditActionPerformed
        new Fruit.ui.EditPaid().setVisible(true);
    }//GEN-LAST:event_paidEditActionPerformed

    private void discount_damageEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discount_damageEditActionPerformed
        new Fruit.ui.EditDiscount_Damage().setVisible(true);
    }//GEN-LAST:event_discount_damageEditActionPerformed

    private void balanceSheetReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceSheetReportActionPerformed
//        try {

//            ArrayList<BalanceModel> al = (ArrayList<BalanceModel>) reportDAL.getCustomerBalance();
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(al);
//            JasperFillManager.fillReportToFile("Reports", null, dataSource);
//            final ImageIcon icon = new ImageIcon("Reports.jpg");
//            Object[] possibilities = {"All", "Local", "Non Local", "Mini"};
//            String s1 = (String) JOptionPane.showInputDialog(
//                    this,
//                    "Select Local / Non-Local :\n"
//                    + "'BalanceSheet'",
//                    "Select Dialog",
//                    JOptionPane.PLAIN_MESSAGE,
//                    icon,
//                    possibilities,
//                    "Local");
//            if (s1 != null) {
//                List<StatementModel> list = new ArrayList<>();
//                list = new Fruit.DAL.CustomerDAL().getCustomerBalance(s1);
//                String tot1 = "";
//                long t = 0;
//              
//                
//                for (StatementModel model : list) {
//                    t += new Fruit.Common.CommonMethods().rupeeToInt(model.getTotalBalance());
//                }
//                tot1 = new Fruit.Common.CommonMethods().rupee(Long.toString(t));
//                if (list.size() > 0) {
////            String s = new Fruit.DAL.CustomerDAL().getTotalBalance(s1);
//                    Fruit.TableModel.BalanceSheetTableModel bstm = new BalanceSheetTableModel(list);
                    Fruit.ui.BalanceSheet bs = new BalanceSheet();
                    bs.setVisible(true);
                    
//                } else {
//                    JOptionPane.showMessageDialog(null, "No data..!");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_balanceSheetReportActionPerformed

    private void supplierSaleReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierSaleReportActionPerformed
        try {
            Fruit.ui.SaleReport.getInstance().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_supplierSaleReportActionPerformed

    private void customerStatementReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerStatementReportActionPerformed
        new Fruit.ui.CustomerStatementForm().setVisible(true);
    }//GEN-LAST:event_customerStatementReportActionPerformed

    private void invoiceReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceReportActionPerformed
        try {

            Fruit.ui.InvoiceReport.getInstance().setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_invoiceReportActionPerformed

    private void supplierRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierRegistrationActionPerformed
        SuplierRegistration.getInstance().setVisible(true);
    }//GEN-LAST:event_supplierRegistrationActionPerformed

    private void invoiceFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceFormActionPerformed
        Fruit.ui.InvoiceFrom.getInstance().setVisible(true);
    }//GEN-LAST:event_invoiceFormActionPerformed

    private void invoiceEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceEditActionPerformed
        new Fruit.ui.InvoiceEditForm().setVisible(true);
    }//GEN-LAST:event_invoiceEditActionPerformed

    private void customerListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerListActionPerformed
        try {
//            final ImageIcon icon = new ImageIcon("Reports.jpg");
//        Object[] possibilities = {"All", "Local", "Non Local","Mini"};
//String s1 = (String)JOptionPane.showInputDialog(
//                    this,
//                    "Select Local / Non-Local :\n"
//                    + "'BalanceSheet'",
//                    
//                    "Select Dialog",
//                    JOptionPane.PLAIN_MESSAGE,
//                    icon,
//                    possibilities,
//                    "Local");
            Connection con = Fruit.Common.DbConnection.getConnection();
            Map parameters = new HashMap();

            JasperDesign jasperDesign = JRXmlLoader.load(new File("Business_Report\\customerlist.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_customerListActionPerformed

    private void supplierListReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListReportActionPerformed
        try {
            Connection con = Fruit.Common.DbConnection.getConnection();
            Map parameters = new HashMap();

            JasperDesign jasperDesign = JRXmlLoader.load(new File("Business_Report\\supplierReport.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_supplierListReportActionPerformed
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    String dt = df.format(new Date()).toString();
    private void buackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buackupActionPerformed

        try {
            /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B().setVisible(true);
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
   

        
    }//GEN-LAST:event_buackupActionPerformed

    private void supplierEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierEditActionPerformed
        try {
            new Fruit.ui.SupplierEdit().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_supplierEditActionPerformed

    private void customerEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerEditActionPerformed
        try {
            new Fruit.ui.CustomerEdit().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_customerEditActionPerformed

    private void cash_On_DataReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cash_On_DataReportActionPerformed
        Fruit.ui.CashOnDate casOnDate = new Fruit.ui.CashOnDate();
        TableCustomiseUtil.setFrameSize(casOnDate);
        casOnDate.setVisible(true);
    }//GEN-LAST:event_cash_On_DataReportActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            PrivilegeDAL dAL = new PrivilegeDAL();
            DefaultTableModel dtm = (DefaultTableModel) previlegeTable.getModel();
            dAL.deletePrivilege();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                if (dtm.getValueAt(i, 2) != null) {

                    if ((Boolean) dtm.getValueAt(i, 2)) {
                        System.out.println(i + 1 + " " + dtm.getValueAt(i, 1) + " : " + dtm.getValueAt(i, 2));

                        PrivilegeModel model = new PrivilegeModel(i,
                                dtm.getValueAt(i, 1).toString(), (Boolean) dtm.getValueAt(i, 2));
                        dAL.addPrivilege(model);

                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Successful...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try {

//            ArrayList<BalanceModel> al = (ArrayList<BalanceModel>) reportDAL.getCustomerBalance();
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(al);
//            JasperFillManager.fillReportToFile("Reports", null, dataSource);
            final ImageIcon icon = new ImageIcon("Reports.jpg");
            Object[] possibilities = {"All", "Local", "Non Local", "Mini"};
            String s1 = (String) JOptionPane.showInputDialog(
                    this,
                    "Select Local / Non-Local :\n"
                    + "'BalanceSheet'",
                    "Select Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "Local");
            if (s1 != null) {
                List<StatementModel> list = new ArrayList<>();
                list = new Fruit.DAL.CustomerDAL().getDeadCustomerBalance(s1);
                String tot1 = "";
                long t = 0;
                for (StatementModel model : list) {
                    t += new Fruit.Common.CommonMethods().rupeeToInt(model.getTotalBalance());
                }
                tot1 = new Fruit.Common.CommonMethods().rupee(Long.toString(t));
                if (list.size() > 0) {
//            String s = new Fruit.DAL.CustomerDAL().getTotalBalance(s1);
                    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
                    Map m = new HashMap();
                    m.put("tot", tot1);
                    m.put("l", s1);

                    JasperDesign design = JRXmlLoader.load("Business_Report\\blancesheet.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(design);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, m, beanCollectionDataSource);
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No data..!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void finalReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalReportActionPerformed
        try {
            new Fruit.ui.StockDetails().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_finalReportActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            new DoupReport().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            PWDChange.getInstance().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainTab.getInstance().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton balanceSheetReport;
    private javax.swing.JButton buackup;
    private javax.swing.JButton cash_On_DataReport;
    private javax.swing.JButton customerEdit;
    private javax.swing.JButton customerList;
    private javax.swing.JButton customerRegistration;
    private javax.swing.JButton customerStatementReport;
    private javax.swing.JButton discount_damageEdit;
    private javax.swing.JButton editPreBalance;
    private javax.swing.JButton entryEdit;
    private javax.swing.JButton entryForm;
    private javax.swing.JButton finalReport;
    private javax.swing.JButton invoiceEdit;
    private javax.swing.JButton invoiceForm;
    private javax.swing.JButton invoiceReport;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton paidEdit;
    private javax.swing.JTable previlegeTable;
    private javax.swing.JButton supplierEdit;
    private javax.swing.JButton supplierListReport;
    private javax.swing.JButton supplierRegistration;
    private javax.swing.JButton supplierSaleReport;
    // End of variables declaration//GEN-END:variables
}
