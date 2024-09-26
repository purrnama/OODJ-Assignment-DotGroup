package com.mycompany.oodj.assignment.dotgroup;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.swing.ListSelectionModel;

public class ManagerMaintenance extends javax.swing.JFrame {

    FileOperation file = FileOperation.getInstance();
    
    Issue selectedIssue;
    ListSelectionModel cellIssueSelection;
    
    final int STATUS_COLUMN = 8;
    
    public ManagerMaintenance() {
        initComponents();
        updateCustomerIssueTable(loadIssues());
        cellIssueSelection = table_CustomerIssue.getSelectionModel();
        cellIssueSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellIssueSelection.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                selectedIssue = getSelectedIssue();
                if (selectedIssue != null) {
                label_IssueId.setText("Issue ID:" + " " + selectedIssue.getIssueId());
                text_CustomerName.setText(selectedIssue.getCustomerName());
                text_Subject.setText(selectedIssue.getSubject());
                textarea_Body.setText(selectedIssue.getBody());
                combo_Confirmation.setSelectedItem(selectedIssue.getConfirmation());
                text_ReporterName.setText(selectedIssue.getReporterName());
                text_AssigneeName.setText(selectedIssue.getAssigneeName());
                
                switch (selectedIssue.getStatus().toString()) {    
                    case "IN_PROGRESS":
                        combo_Status.setSelectedIndex(0);
                        break;
                    case "DONE":
                        combo_Status.setSelectedIndex(1);
                        break;
                    case "CANCELLED":
                        combo_Status.setSelectedIndex(2);
                        break;
                    case "COMPLETED":
                        combo_Status.setSelectedIndex(3);
                        break;
                    case "NONE":
                        combo_Status.setSelectedIndex(4);
                }
            }
            }
        });
    }

    private Issue getSelectedIssue() {
        int selected = table_CustomerIssue.getSelectedRow();
        
        if (selected >= 0) {
            String issueId      = (String)table_CustomerIssue.getValueAt(selected, 0);
            String customerName = (String)table_CustomerIssue.getValueAt(selected, 1);
            String subject      = (String)table_CustomerIssue.getValueAt(selected, 2);
            String body         = (String)table_CustomerIssue.getValueAt(selected, 3);

            String state        = (String)table_CustomerIssue.getValueAt(selected, 4).toString();
            
            IssueState issueState = IssueState.valueOf(state);

            String confirmation = (String)table_CustomerIssue.getValueAt(selected, 5);
            String reporterName = (String)table_CustomerIssue.getValueAt(selected, 6);
            String assigneeName = (String)table_CustomerIssue.getValueAt(selected, 7);

            String status       = (String)table_CustomerIssue.getValueAt(selected, STATUS_COLUMN).toString();
            
            IssueMaintenanceStatus issueMaintenanceStatus = IssueMaintenanceStatus.valueOf(status);
            
            Issue i = new Issue(
                issueId, customerName, subject, body, issueState, confirmation,
                reporterName, assigneeName, issueMaintenanceStatus
                );
            return i;
        } else {
            return null;
        }
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
    
    private IssueState getIssueStateFromString(String state) {
        IssueState issueState;
        
        switch (state) {
            case "OPEN", "Open"         -> issueState = IssueState.OPEN;
            case "CLOSED", "Closed"     -> issueState = IssueState.CLOSED;
            case "REOPENED", "Reopened" -> issueState = IssueState.REOPENED;
            case "RESOLVED", "Resolved" -> issueState = IssueState.RESOLVED;
            default                     -> issueState = IssueState.NONE;
        }
        
        return issueState;
    }
    
    private IssueMaintenanceStatus getIssueMaintenanceStatusFromString(String status) {
        IssueMaintenanceStatus issueMaintenanceStatus;
       
        issueMaintenanceStatus = switch (status) {
            case "In Progress", "IN_PROGRESS" -> 
                IssueMaintenanceStatus.IN_PROGRESS;
            case "Done", "DONE" -> 
                IssueMaintenanceStatus.DONE;
            case "Cancelled", "CANCELLED" -> 
                IssueMaintenanceStatus.CANCELLED;
            case "Completed", "COMPLETED" -> 
                IssueMaintenanceStatus.COMPLETED;
            default -> IssueMaintenanceStatus.NONE;
        };
       
        return issueMaintenanceStatus;
    }
    
    private Issue getInputIssue(){
        String issueId = selectedIssue.getIssueId();
        
        String customerName = text_CustomerName.getText();
        String subject = text_Subject.getText();
        String body = textarea_Body.getText();
        IssueState state = selectedIssue.getState();
        
        String confirmation = (String)combo_Confirmation.getSelectedItem();
        
        String reporterName = text_ReporterName.getText();
        String assigneeName = text_AssigneeName.getText();
        
        String status = combo_Status.getSelectedItem().toString();
        IssueMaintenanceStatus issueMaintenanceStatus = IssueMaintenanceStatus.valueOf(status);

        if(issueId.isEmpty() || customerName.isEmpty()) {  
            warning("Issue ID or Customer name cannot be empty.", "Input Error");
            return null;
        }

        return new Issue(issueId, customerName, subject, body, state,
                confirmation, reporterName, assigneeName, issueMaintenanceStatus
        );
    }
    
    public void confirmMaintenance() {
        notifyCustomer();
    }
        
    private void warning(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    private void notifyCustomer() {
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        int row = table_CustomerIssue.getSelectedRow();
         
        if (row >= 0) {
            String message = """
                Dear Customer, 
                We have confirmed the issue you are facing.

                We are currently working on a fix.
                Please wait patiently. 

                Thank you.

                Regards, 
                Hall Symphony Inc.
            """;

            String title = "Customer Issue: " + model.getValueAt(row, 2);
            
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            warning("Please close the program and try again.", "Notify Customer Error");
        }
        
    }
    
    private void updateOldSchedulerData(String schedulerAssignee, boolean value) {
        
        Scheduler oldScheduler = null;
        ArrayList<User> users = file.read(FileType.USERS);
        for(User u : users){
            if(u instanceof Scheduler s){
                if(s.getUsername().equals(schedulerAssignee)){
                    oldScheduler = s;
                    break;
                }
            }
        }
        if(oldScheduler != null){
            Scheduler newScheduler = oldScheduler;
            newScheduler.setIsAssignedHallMaintenance(value);
            file.update(oldScheduler, newScheduler);
        } else {
            warning("Selected scheduler cannot be assigned.", "Null Scheduler");
        }
    }
    
    private void assignScheduler() {
    	DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        int row = table_CustomerIssue.getSelectedRow();
               
        if (row < 0) {
            warning("Row is not selected.", "Row Selection Issue");
            return;
        }
        
        String issueId = (String)model.getValueAt(row, 0);
        String schedulerAssignee = (String)model.getValueAt(row, 7);
        
        if (row >= 0) {
            String message = "You have assigned Scheduler " + schedulerAssignee + 
                " to fix Issue " + issueId +  ".\n";
            
            updateOldSchedulerData(schedulerAssignee, true); // Refers to setting isAssignedHallMaintenance to true.

            JOptionPane.showMessageDialog(null, message, "Notify Scheduler", JOptionPane.INFORMATION_MESSAGE);        
        } else {
            warning("Please close the program and try again.", "Notify Scheduler Error");
        }
    }
    
    private void removeAssignScheduler() {
    	DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        int row = table_CustomerIssue.getSelectedRow();
               
        if (row < 0) {
            warning("Row is not selected.", "Row Selection Issue");
            return;
        }
        
        String issueId = (String)model.getValueAt(row, 0);
        String schedulerAssignee = (String)model.getValueAt(row, 7);
        
        if (row >= 0) {
            String message = "You have removed assignedScheduler " + schedulerAssignee + 
                " to fix Issue " + issueId +  ".\n";
            
            updateOldSchedulerData(schedulerAssignee, false); // Refers to setting isAssignedHallMaintenance to true.

            JOptionPane.showMessageDialog(null, message, "Notify Scheduler", JOptionPane.INFORMATION_MESSAGE);        
        } else {
            warning("Please close the program and try again.", "Notify Scheduler Error");
        }
    }
    
    private void updateIssueMaintenanceStatusFromTable() {
        DefaultTableModel model = (DefaultTableModel)table_CustomerIssue.getModel();
        int row = table_CustomerIssue.getSelectedRow();
        
        if (row < 0) {
            warning("Row is not selected.", "Row Selection Issue");
            return;
        }
        
        String status = (String)combo_Status.getSelectedItem();
        IssueMaintenanceStatus issueMaintenanceStatus = getIssueMaintenanceStatusFromString(status);
        
        model.setValueAt(issueMaintenanceStatus, row, STATUS_COLUMN);
    }
    
    private void goToManagerPanel() {
        ManagerPanel panel = new ManagerPanel();
        panel.setVisible(true);
        this.dispose();
    }
    
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
        button_ManagerPanel = new javax.swing.JButton();
        button_RemoveAssignScheduler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_ManagerMaintenance.setBackground(new java.awt.Color(255, 153, 51));

        label_MaintenanceTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        label_MaintenanceTitle.setText("Maintenance");

        table_CustomerIssue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Issue ID", "Customer Name", "Subject", "Body", "Issue State", "Issue Confirmed?", "Reporter Name", "Assignee Name", "Status"
            }
        ));
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

        combo_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IN_PROGRESS", "DONE", "CANCELLED", "COMPLETED", "NONE" }));
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

        combo_Confirmation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Confirmed", "Unconfirmed" }));

        button_UpdateStatus.setText("Update Status");
        button_UpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UpdateStatusActionPerformed(evt);
            }
        });

        button_ConfirmMaintenance.setText("Confirm Maintenance");
        button_ConfirmMaintenance.setToolTipText("Notify customers about Hall issues");
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

        button_ManagerPanel.setText("To Manager Panel");
        button_ManagerPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ManagerPanelActionPerformed(evt);
            }
        });

        button_RemoveAssignScheduler.setText("Remove Assign Scheduler");
        button_RemoveAssignScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_RemoveAssignSchedulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ManagerMaintenanceLayout = new javax.swing.GroupLayout(panel_ManagerMaintenance);
        panel_ManagerMaintenance.setLayout(panel_ManagerMaintenanceLayout);
        panel_ManagerMaintenanceLayout.setHorizontalGroup(
            panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                .addContainerGap()
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
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label_SchedulerAssigneeName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label_ReporterName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                    .addComponent(label_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                        .addComponent(combo_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_RemoveAssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                        .addComponent(text_AssigneeName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_AssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                                        .addComponent(text_ReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(button_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(button_ConfirmMaintenance)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addComponent(label_MaintenanceTitle)
                        .addGap(32, 32, 32)
                        .addComponent(button_ManagerPanel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_ManagerMaintenanceLayout.setVerticalGroup(
            panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ManagerMaintenanceLayout.createSequentialGroup()
                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_MaintenanceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(button_ManagerPanel)))
                .addGap(30, 30, 30)
                .addComponent(label_IssueId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(button_AssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_RemoveAssignScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_ConfirmMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ManagerMaintenanceLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ManagerMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Body, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ManagerMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ManagerMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        assignScheduler();
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
       if(selectedIssue == null){
           warning("Please select an issue to update", "No Issue Selected");
           return;
       }
       
       Issue newIssue = getInputIssue();
       file.update(selectedIssue, newIssue);
       updateCustomerIssueTable(loadIssues());
    }//GEN-LAST:event_button_UpdateStatusActionPerformed

    private void button_ManagerPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ManagerPanelActionPerformed
        goToManagerPanel();
    }//GEN-LAST:event_button_ManagerPanelActionPerformed

    private void button_RemoveAssignSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_RemoveAssignSchedulerActionPerformed
        removeAssignScheduler();
    }//GEN-LAST:event_button_RemoveAssignSchedulerActionPerformed

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
    private javax.swing.JButton button_ManagerPanel;
    private javax.swing.JButton button_RemoveAssignScheduler;
    private javax.swing.JButton button_UpdateStatus;
    private javax.swing.JComboBox<String> combo_Confirmation;
    private javax.swing.JComboBox<String> combo_Status;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JTextField text_AssigneeName;
    private javax.swing.JTextField text_CustomerName;
    private javax.swing.JTextField text_ReporterName;
    private javax.swing.JTextField text_Subject;
    private javax.swing.JTextArea textarea_Body;
    // End of variables declaration//GEN-END:variables

}
