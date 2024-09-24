package com.mycompany.oodj.assignment.dotgroup;

import static com.mycompany.oodj.assignment.dotgroup.IssueMaintenanceStatus.CANCELLED;
import static com.mycompany.oodj.assignment.dotgroup.IssueMaintenanceStatus.CLOSED;
import static com.mycompany.oodj.assignment.dotgroup.IssueMaintenanceStatus.DONE;
import static com.mycompany.oodj.assignment.dotgroup.IssueMaintenanceStatus.IN_PROGRESS;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import javax.swing.ListSelectionModel;
//import javax.swing.ListModelListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

//import javax.swing.table.TableModelEvent;
//import javax.swing.table.TableModelListener;

public class ManagerMaintenance extends javax.swing.JFrame {

    FileOperation file = FileOperation.getInstance();
//    DefaultTableModel model;
    
    ArrayList<String> test = new ArrayList();
    
    ListSelectionModel listSelectionModel;
    
    public ManagerMaintenance() {
        initComponents();
        
        updateCustomerIssueTable(loadIssues());
        
//        insertDataFromTable();

    }
    
    // Retrieve the data by adding an event listener when the table is changed.
    private void insertDataFromTable() {
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        table_CustomerIssue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        

      
        
        listSelectionModel = table_CustomerIssue.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
//        
//        });

        TableModelListener tableModelListener;

        
//            table_CustomerIssue.
            
            System.out.println(table_CustomerIssue.getSelectedRow());
            
            int row = table_CustomerIssue.getSelectedRow();
            System.out.println("WWWWWWWWW");
//            System.out.println(model.getValueAt(table_CustomerIssue.getSelectedRow(), model.getColumnCount() - 1));
            
                
//                if ()
            // Respond? column
            if (model.getValueAt(row, model.getColumnCount() - 1).equals(true)) {
                
            
            String issueId      = (String)model.getValueAt(row, 0);
            String customerName = (String)model.getValueAt(row, 1);
            String subject      = (String)model.getValueAt(row, 2);
            String body         = (String)model.getValueAt(row, 3);
    //        String state        = (String)model.getValueAt(0, 4);
            String confirmation = (String)model.getValueAt(row, 5);
            String reporterName = (String)model.getValueAt(row, 6);
            String assigneeName = (String)model.getValueAt(row, 7);
    //        String status       = (String)model.getValueAt(0, 8);
                
                label_IssueId.setText("Issue ID: " + issueId + " ("  + ")");
                text_CustomerName.setText(customerName);
                text_Subject.setText(subject);
                textarea_Body.setText(body);
                combo_Confirmation.setSelectedItem(confirmation);                
                text_ReporterName.setText(reporterName);
                text_AssigneeName.setText(assigneeName);
//                combo_Status.setSelectedItem(status);                
//                text_Subject.setText(status);
                
            }
            System.out.println(((DefaultTableModel)table_CustomerIssue.getModel()).getValueAt(table_CustomerIssue.getSelectedRow(), 1));
        
    }
    
    private ArrayList<Issue> loadIssues() {
        return file.read(FileType.ISSUES);
    }
    
    private void updateCustomerIssueTable(ArrayList<Issue> issues) {
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        model.setRowCount(0);
        
        for (Issue issue : issues) {
            model.addRow(
                new Object[]{
                    issue.getIssueId(),
                    issue.getCustomerName(),
                    issue.getSubject(),
                    issue.getBody(),
                    issue.getState(),
                    issue.getConfirmation(), 
                    issue.getReporterName(),
                    issue.getAssigneeName(),
                    issue.getStatus(),
                }
            );
        }
    }
    
    public void confirmMaintenance() {
        
        FileOperation file = FileOperation.getInstance();
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
//        model.removeTableModelListener(table_CustomerIssue);
//        model.setRowCount(0);
        
        String issueId = label_IssueId.getText();
//        label_IssueId.setText("Issue ID: " + issueId + " ("  + ")");
//        text_CustomerName.getText();
//        text_Subject.getText();
//        textarea_Body.getText();
//        combo_Confirmation.getSelectedItem();                
//        text_ReporterName.getText();
//        text_AssigneeName.getText();
//        combo_Status.getSelectedItem();                
//        text_Subject.getText();
        
        int row = table_CustomerIssue.getSelectedRow();
        
        notifyCustomer();
//        if (row < 0) {
//           return;
//        }
//        model.removeRow(row);
//        table_CustomerIssue.is
//        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
//        int rowCount = model.getRowCount();
//        for (int i = rowCount - 1; i >= 0; i--) {
//            model.removeRow(i);
//        }
        
//        Issue newIssueFile = new Issue();
        
//        file.update(issue, issue);
        
        
//        for (int row = 0; row != ; row++) {
//            for (int column = 0; column != ; column++) {
//                
//            }
//        }
//        issue.getIssueId(),
//                issue.getCustomerName(),
//                issue.getSubject(),
//                issue.getBody(),
//                "Unconfirmed",  
//                issue.getStatus(),
//        model.addRow(
//            new Object[]{
//                label_IssueId.getText(),
//                
//            }
//        );
//        
//    	String issueId      = ;
//        String customerName = (String)model.getValueAt(0, 1);
//        String subject      = (String)model.getValueAt(0, 2);
//        String body         = (String)model.getValueAt(0, 3);
//        String confirmation = (String)model.getValueAt(0, 4);
    }
    
    public void receiveCustomerIssue() {
//    	if ()
    }
    
    private void warning(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public void notifyCustomer() {
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
//        model.setRowCount(0);
        int row = table_CustomerIssue.getSelectedRow();
         
        if (row > 0) {
            String message = "Dear Customer, \nWe have confirmed the issue you are facing.\n\n"
                + "We are currently working on a fix.\n"
                + "Please wait patiently. \n\nThank you.\n\n"
                + "Regards, \nHall Symphony Inc.";

            String title = "Customer Issue: " + model.getValueAt(row, 2);

            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            warning("Please close the program and try again.", "Error");
        }
        
    }
    
    public void notifyScheduler() {
//        String message, NotificationLevel notificationLevel, NotificationType notificationType
    	DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        
        int row = table_CustomerIssue.getSelectedRow();
        
        String message = "You have notified Scheduler " + model.getValueAt(row, 7) + 
            " to fix Issue " + model.getValueAt(row, 0) +  ".\n";
        
        JOptionPane.showMessageDialog(null, message, "Notify Scheduler", JOptionPane.INFORMATION_MESSAGE);
    	
//    	scheduler.getUsername();
    }
    
    public void viewCustomerIssues() {
    	String issue = "";
    	customer.getIssue();
    	
    }
    
    public void setCustomerIssue(IssueMaintenanceStatus issueStatusCustomer) {
    	switch (issueStatusCustomer) {
            case IN_PROGRESS:
                break;
            case CLOSED:
                break;
            case CANCELLED:
                break;
            case DONE:
                break;
            default:
                break;
        }
    }
    
    private HashMap<Customer, ManagerSales> sales;
    private ArrayList<Customer> customerIssue;
    private Customer customer;
    private Issue issue;
    private Scheduler scheduler;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_ManagerMaintenance = new javax.swing.JPanel();
        label_MaintenanceTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_CustomerIssue = new javax.swing.JTable();
        label_CustomerName = new javax.swing.JLabel();
        text_CustomerName = new javax.swing.JTextField();
        label_IssueId = new javax.swing.JLabel();
        label_Status = new javax.swing.JLabel();
        combo_Status = new javax.swing.JComboBox<>();
        button_AssignScheduler = new javax.swing.JButton();
        label_Body = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textarea_Body = new javax.swing.JTextArea();
        label_Subject = new javax.swing.JLabel();
        text_Subject = new javax.swing.JTextField();
        label_ConfirmationStatus = new javax.swing.JLabel();
        combo_Confirmation = new javax.swing.JComboBox<>();
        button_UpdateStatus = new javax.swing.JButton();
        button_ConfirmMaintenance = new javax.swing.JButton();
        label_ReporterName = new javax.swing.JLabel();
        text_ReporterName = new javax.swing.JTextField();
        label_SchedulerAssigneeName = new javax.swing.JLabel();
        text_AssigneeName = new javax.swing.JTextField();
        text_UpdateStatus = new javax.swing.JTextField();
        button_InsertData = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_CustomerIssueMaintenanceConfirmed = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label_MaintenanceTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        label_MaintenanceTitle.setText("Maintenance");

        table_CustomerIssue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Issue ID", "Customer Name", "Subject", "Body", "Issue State", "Issue Confirmed?", "Reporter Name", "Assignee Name", "Status", "Respond?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_CustomerIssue.setShowGrid(false);
        table_CustomerIssue.setShowVerticalLines(true);
        jScrollPane1.setViewportView(table_CustomerIssue);
        if (table_CustomerIssue.getColumnModel().getColumnCount() > 0) {
            table_CustomerIssue.getColumnModel().getColumn(3).setResizable(false);
        }

        label_CustomerName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_CustomerName.setText("Customer Name: ");

        text_CustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_CustomerNameActionPerformed(evt);
            }
        });

        label_IssueId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_IssueId.setText("Issue ID: ");

        label_Status.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Status.setText("Status:");

        combo_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Progress", "Done", "Completed", "Cancelled" }));
        combo_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_StatusActionPerformed(evt);
            }
        });

        button_AssignScheduler.setText("Assign Scheduler");
        button_AssignScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AssignSchedulerActionPerformed(evt);
            }
        });

        label_Body.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Body.setText("Body:");

        textarea_Body.setColumns(20);
        textarea_Body.setRows(5);
        jScrollPane3.setViewportView(textarea_Body);

        label_Subject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Subject.setText("Subject:");

        text_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_SubjectActionPerformed(evt);
            }
        });

        label_ConfirmationStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_ConfirmationStatus.setText("Confirmation Status:");

        combo_Confirmation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Confirmed", "Unconfirmed", " " }));

        button_UpdateStatus.setText("Update Status");
        button_UpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UpdateStatusActionPerformed(evt);
            }
        });

        button_ConfirmMaintenance.setText("Confirm Maintenance");
        button_ConfirmMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ConfirmMaintenanceActionPerformed(evt);
            }
        });

        label_ReporterName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_ReporterName.setText("Reporter Name: ");

        text_ReporterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_ReporterNameActionPerformed(evt);
            }
        });

        label_SchedulerAssigneeName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_SchedulerAssigneeName.setText("Assignee Name: ");

        text_AssigneeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_AssigneeNameActionPerformed(evt);
            }
        });

        text_UpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_UpdateStatusActionPerformed(evt);
            }
        });

        button_InsertData.setText("Insert Data");
        button_InsertData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_InsertDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ManagerMaintenanceLayout = new javax.swing.GroupLayout(panel_ManagerMaintenance);
        panel_ManagerMaintenance.setLayout(panel_ManagerMaintenanceLayout);
        panel_ManagerMaintenanceLayout.setHorizontalGroup(
            panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_IssueId, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_CustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                                .addComponent(label_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(label_ConfirmationStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(text_Subject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(combo_Confirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(text_CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                        .addComponent(label_Body, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label_SchedulerAssigneeName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label_ReporterName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                    .addComponent(label_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button_ConfirmMaintenance)
                                    .addComponent(text_ReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_AssigneeName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(button_AssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_InsertData, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_MaintenanceTitle))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        panel_ManagerMaintenanceLayout.setVerticalGroup(
            panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ManagerMaintenanceLayout.createSequentialGroup()
                .addComponent(label_MaintenanceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(label_IssueId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_ReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_ReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_SchedulerAssigneeName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_Confirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_ConfirmationStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_AssigneeName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_UpdateStatus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_AssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Body, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_ConfirmMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_InsertData, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        table_CustomerIssueMaintenanceConfirmed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Issue ID", "Customer Name", "Hall Type", "Payment", "Subject", "Body", "Status"
            }
        ));
        jScrollPane2.setViewportView(table_CustomerIssueMaintenanceConfirmed);
        if (table_CustomerIssueMaintenanceConfirmed.getColumnModel().getColumnCount() > 0) {
            table_CustomerIssueMaintenanceConfirmed.getColumnModel().getColumn(2).setHeaderValue("Hall Type");
            table_CustomerIssueMaintenanceConfirmed.getColumnModel().getColumn(3).setHeaderValue("Payment");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ManagerMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ManagerMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_CustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_CustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_CustomerNameActionPerformed

    private void combo_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_StatusActionPerformed

    private void button_AssignSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AssignSchedulerActionPerformed
        // TODO add your handling code here:
        notifyScheduler();
    }//GEN-LAST:event_button_AssignSchedulerActionPerformed

    private void text_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_SubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_SubjectActionPerformed

    private void button_ConfirmMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ConfirmMaintenanceActionPerformed
        confirmMaintenance();
    }//GEN-LAST:event_button_ConfirmMaintenanceActionPerformed

    private void text_ReporterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_ReporterNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_ReporterNameActionPerformed

    private void text_AssigneeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_AssigneeNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_AssigneeNameActionPerformed

    private void button_UpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UpdateStatusActionPerformed
        // TODO add your handling code here:
//        JOptionPane.YES_NO_CANCEL_OPTION
        String[] options = { "In Progress", "Done", "Closed", "Cancelled", "Completed", "None" };
        
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
//        model.setRowCount(0);
        String updateStatus = text_UpdateStatus.getText();
        
        String[] split = updateStatus.split(";"); // Obeys row-status format, with a ; as separator
        
        int row = Integer.parseInt(split[0]);
        String newStatus = split[1];
        System.out.println(model.getValueAt(row, 8));
//        button_UpdateStatusActionPerformed();

//        model.se
//        model.setValueAt((Object)newStatus, 1, 8);
//        Object[] rows = {};

//        combo_Status.setSelectedItem
//        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
//        table_CustomerIssue.getCol
//        int columnIndex = model.getColumnIndex("Status");
//        int row = table_CustomerIssue.getSelectedRow();
//        
//        Object currentStatus = model.getValueAt(row, columnIndex);
//        Object newStatus = JOptionPane.showOptionDialog(null, "Set to what new Status?", "Update Issue Maintenance Status", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
////        String new_status = 
        
//        model.setValueAt(newStatus, row, columnIndex);
//        for (int i = 0; i != model.getRowCount(); i++) {
////            rows.add(String.valueOf(i));
//            rows[i] = String.valueOf(i);
//        }
        
//        JOptionPane.showIn
//        System.out.println(JOptionPane.showInputDialog(null, "Whihch row?", "Update", JOptionPane.QUESTION_MESSAGE, null, options, DISPOSE_ON_CLOSE));
//        System.out.println(JOptionPane.showInputDialog(null, "Which row?", "Update", JOptionPane.QUESTION_MESSAGE, null, rows, DISPOSE_ON_CLOSE));
//        Object row = JOptionPane.showInputDialog(null, "Which row?", "Update Issue Maintenance Status", JOptionPane.QUESTION_MESSAGE, null, rows, DISPOSE_ON_CLOSE);
        
//          String row = JOptionPane.showInputDialog("Which row? Max row count is " + model.getRowCount());
//        System.out.println(JOptionPane.showInputDialog(null, "Which row?", "Update Issue Maintenance Status"));
//        JOptionPane.showMessageDialog(null, "HHHHH");

//        model.setValueAt(new_status, Integer.parseInt(row), 1);
    }//GEN-LAST:event_button_UpdateStatusActionPerformed

    private void text_UpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_UpdateStatusActionPerformed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_text_UpdateStatusActionPerformed

    private void button_InsertDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_InsertDataActionPerformed
        // TODO add your handling code here:
        insertDataFromTable();
    }//GEN-LAST:event_button_InsertDataActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerMaintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerMaintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerMaintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerMaintenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerMaintenance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_AssignScheduler;
    private javax.swing.JButton button_ConfirmMaintenance;
    private javax.swing.JButton button_InsertData;
    private javax.swing.JButton button_UpdateStatus;
    private javax.swing.JComboBox<String> combo_Confirmation;
    private javax.swing.JComboBox<String> combo_Status;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_Body;
    private javax.swing.JLabel label_ConfirmationStatus;
    private javax.swing.JLabel label_CustomerName;
    private javax.swing.JLabel label_IssueId;
    private javax.swing.JLabel label_MaintenanceTitle;
    private javax.swing.JLabel label_ReporterName;
    private javax.swing.JLabel label_SchedulerAssigneeName;
    private javax.swing.JLabel label_Status;
    private javax.swing.JLabel label_Subject;
    private javax.swing.JPanel panel_ManagerMaintenance;
    private javax.swing.JTable table_CustomerIssue;
    private javax.swing.JTable table_CustomerIssueMaintenanceConfirmed;
    private javax.swing.JTextField text_AssigneeName;
    private javax.swing.JTextField text_CustomerName;
    private javax.swing.JTextField text_ReporterName;
    private javax.swing.JTextField text_Subject;
    private javax.swing.JTextField text_UpdateStatus;
    private javax.swing.JTextArea textarea_Body;
    // End of variables declaration//GEN-END:variables

}
