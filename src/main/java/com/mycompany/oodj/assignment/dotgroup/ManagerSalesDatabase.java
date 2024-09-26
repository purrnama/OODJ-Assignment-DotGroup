package com.mycompany.oodj.assignment.dotgroup;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Date;

public class ManagerSalesDatabase extends javax.swing.JFrame {
    
    FileOperation file = FileOperation.getInstance();
    final int DATE_COLUMN = 3;

    public ManagerSalesDatabase() {
        initComponents();
        updateSalesTable(loadSales());
    }
    
    private ArrayList<ManagerSales> loadSales() {
        return file.read(FileType.SALES);
    }
    
 
    private void removeAllRowsFromTable() {
        DefaultTableModel model = (DefaultTableModel)table_SalesDatabase.getModel();
        model.setRowCount(0);
    }
    
    private void updateSalesTable(ArrayList<ManagerSales> sales) {
        DefaultTableModel model = (DefaultTableModel)table_SalesDatabase.getModel();
        removeAllRowsFromTable();
        
        for (ManagerSales sale : sales) {
            model.addRow(
                new Object[]{
                    sale.getSalesId(),
                    sale.getCustomerId(),
                    sale.getCustomerName(),
                    sale.getDate(),
                    sale.getTime(),
                    sale.getHallType(),
                    sale.getDescription(),
                    sale.getTotalSales()
                }
            );
        }
    }

    private LocalDate convertToLocalDate(Date d){
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    private boolean isDateBetween(LocalDate startDate, LocalDate endDate, LocalDate targetDate) {
        return startDate.isBefore(targetDate) && targetDate.isBefore(endDate);
    }
    
    private LocalDate convertStringToLocalDate(String value, DateTimeFormatter formatter) {                
        try {
            return LocalDate.parse(value, formatter);        
        } catch (DateTimeException e) {
            warning("Sorry, value is not parsable to LocalDateTime.", "DateTime Parsing Error");
        }

        return null;        
    }
    
    public ManagerSales getAllDataInSpecifiedRow(int row) {
        DefaultTableModel model = (DefaultTableModel)table_SalesDatabase.getModel();

        String salesId      = (String)model.getValueAt(row, 0);
        String customerId   = (String)model.getValueAt(row, 1);
        String customerName = (String)model.getValueAt(row, 2);        
        LocalDate date      = (LocalDate)model.getValueAt(row, 3);       
        LocalTime time      = (LocalTime)model.getValueAt(row, 4);
        
        String hallType     = (String)model.getValueAt(row, 5);
        String description  = (String)model.getValueAt(row, 6);
                
        Double totalSales = (Double)model.getValueAt(row, 7);

        return new ManagerSales(
            salesId, customerId, customerName,
            date, time, hallType, description, totalSales
        );
    }
    
    private ArrayList<ManagerSales> getSalesFiltered(LocalDate startDate, LocalDate endDate, LocalDate selectedDate) {
        DefaultTableModel model = (DefaultTableModel)table_SalesDatabase.getModel();

        ArrayList<ManagerSales> salesFiltered = new ArrayList();
       
        for (int row = 0; row != model.getRowCount(); row++) {
            String targetDate = model.getValueAt(row, DATE_COLUMN).toString();
            LocalDate targetDateFormatted = 
                convertStringToLocalDate(targetDate, DateTimeFormatter.ISO_LOCAL_DATE);

            if (isDateBetween(startDate, endDate, targetDateFormatted)) {
                salesFiltered.add(getAllDataInSpecifiedRow(row));
            }
        }
        
        return salesFiltered;
    }
    
    private void filterTableBasedOnDate() {
        if (text_DateSelected.getText().isEmpty()) {
            warning("Target date cannot be read for filter.", "Sales Filter Error");
            return;
        }
        
        String selectedDateString = text_DateSelected.getText();
        LocalDate selectedDate = 
            convertStringToLocalDate(selectedDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        
        if (text_StartDateSelected.getText().isEmpty() || text_EndDateSelected.getText().isEmpty()) {
            warning("Date cannot be read for filter.", "Sales Filter Error");
            return;
        }
        
        LocalDate startDate = convertToLocalDate(calendar_SalesMadeFilterStart.getDate());
        LocalDate endDate = convertToLocalDate(calendar_SalesMadeFilterEnd.getDate());

        ArrayList<ManagerSales> salesFiltered = getSalesFiltered(startDate, endDate, selectedDate);
        updateSalesTable(salesFiltered);
    }
        
    private void warning(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    private void reloadSalesTable() {
        updateSalesTable(loadSales());
    }
    
        private void getSelectedDateFromTable() {
        DefaultTableModel model = (DefaultTableModel)table_SalesDatabase.getModel();
        
        int row = table_SalesDatabase.getSelectedRow();        
        // int column = table_SalesDatabase.getSelectedColumn();
        
        if (row < 0) {
            warning("No rows from table to be selected.", "Row Selection Error");
            return;
        }

        if (row >= 0) {            
            String selectedDate = (String)model.getValueAt(row, 3).toString(); // Get Date column
            text_DateSelected.setText(selectedDate);
        }
    }
    
    private void addDateBy(int state) {
        // TODO add your handling code here:
        if (text_StartDateSelected.getText().isEmpty()) {
            warning("Start date cannot be read for filter.", "Sales Filter Error");
            return;
        }
        
        String startDate = text_StartDateSelected.getText();
        LocalDate date = 
            convertStringToLocalDate(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String newEndDate = "";
        
        // State 0 refers to add by nth week(s), 1 refers to nth month(s), 2 refers to nth year(s)
        switch (state) {
            case 0 -> {
                String[] numberOfDaysInAWeek = { "1", "2", "3", "4", "5", "6", "7" };
       
                int input = JOptionPane.showOptionDialog(null, "How Many Weeks?", 
                    "Add Week(s) to Start Date", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, 
                    numberOfDaysInAWeek, DISPOSE_ON_CLOSE
                );

                // Input is incremented by 1 due to it being 0-index based
                newEndDate = date.plusWeeks(input + 1).toString(); 
            }
            case 1 -> {
                String[] numberOfMonthsInAYear = { "1", "2", "3", "4", "5", "6", "7", 
                    "8", "9", "10", "11", "12" };
       
                int input = JOptionPane.showOptionDialog(null, "How Many Months?", 
                    "Add Month(s) to Start Date", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, 
                    numberOfMonthsInAYear, DISPOSE_ON_CLOSE
                );

                // Input is incremented by 1 due to it being 0-index based
                newEndDate = date.plusMonths(input + 1).toString(); 
            }
            case 2 -> {
                // Input is incremented by 1 due to it being 0-index based
                newEndDate = date.plusYears(1).toString(); 
            }
        } 
        
        text_EndDateSelected.setText(newEndDate);
    }
    
    private void getStartDateFromCalendar() {
        LocalDate startDate = convertToLocalDate(calendar_SalesMadeFilterStart.getDate());
        String date = startDate.toString();

        text_StartDateSelected.setText(date);
    }
    
    private void getEndDateFromCalendar() {
        LocalDate endDate = convertToLocalDate(calendar_SalesMadeFilterEnd.getDate());
        String date = endDate.toString();
        
        text_EndDateSelected.setText(date);
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

        body_PanelMain = new javax.swing.JPanel();
        label_SalesTableTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_SalesDatabase = new javax.swing.JTable();
        aside_PanelFilter = new javax.swing.JPanel();
        button_Filter = new javax.swing.JButton();
        label_FilterTargetDate = new javax.swing.JLabel();
        calendar_SalesMadeFilterStart = new com.toedter.calendar.JCalendar();
        label_StartDate = new javax.swing.JLabel();
        calendar_SalesMadeFilterEnd = new com.toedter.calendar.JCalendar();
        label_EndDate = new javax.swing.JLabel();
        text_DateSelected = new javax.swing.JTextField();
        button_ReloadTable = new javax.swing.JButton();
        text_StartDateSelected = new javax.swing.JTextField();
        text_EndDateSelected = new javax.swing.JTextField();
        button_AddBy1Week = new javax.swing.JButton();
        button_AddBy1Month = new javax.swing.JButton();
        button_AddBy1Year = new javax.swing.JButton();
        button_ManagerPanel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body_PanelMain.setBackground(new java.awt.Color(0, 0, 51));

        label_SalesTableTitle.setBackground(new java.awt.Color(204, 255, 255));
        label_SalesTableTitle.setFont(new java.awt.Font("MesloLGM Nerd Font", 1, 24)); // NOI18N
        label_SalesTableTitle.setForeground(new java.awt.Color(255, 255, 255));
        label_SalesTableTitle.setText("Sales Table");

        table_SalesDatabase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Customer ID", "Customer Name", "Date", "Time", "Hall Type", "Description", "Sales (RM)"
            }
        ));
        table_SalesDatabase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_SalesDatabaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_SalesDatabase);
        if (table_SalesDatabase.getColumnModel().getColumnCount() > 0) {
            table_SalesDatabase.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        aside_PanelFilter.setBackground(new java.awt.Color(0, 153, 204));

        button_Filter.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        button_Filter.setText("Filter");
        button_Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_FilterActionPerformed(evt);
            }
        });

        label_FilterTargetDate.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        label_FilterTargetDate.setForeground(new java.awt.Color(255, 255, 255));
        label_FilterTargetDate.setText("Target Date");

        calendar_SalesMadeFilterStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar_SalesMadeFilterStartPropertyChange(evt);
            }
        });

        label_StartDate.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        label_StartDate.setForeground(new java.awt.Color(255, 255, 255));
        label_StartDate.setText("Start Date");

        calendar_SalesMadeFilterEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar_SalesMadeFilterEndPropertyChange(evt);
            }
        });

        label_EndDate.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        label_EndDate.setForeground(new java.awt.Color(255, 255, 255));
        label_EndDate.setText("End Date");

        button_ReloadTable.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        button_ReloadTable.setText("Reload");
        button_ReloadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReloadTableActionPerformed(evt);
            }
        });

        text_EndDateSelected.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                text_EndDateSelectedPropertyChange(evt);
            }
        });

        button_AddBy1Week.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        button_AddBy1Week.setText("Add 1 Week");
        button_AddBy1Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AddBy1WeekActionPerformed(evt);
            }
        });

        button_AddBy1Month.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        button_AddBy1Month.setText("Add 1 Month");
        button_AddBy1Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AddBy1MonthActionPerformed(evt);
            }
        });

        button_AddBy1Year.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        button_AddBy1Year.setText("Add 1 Year");
        button_AddBy1Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AddBy1YearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aside_PanelFilterLayout = new javax.swing.GroupLayout(aside_PanelFilter);
        aside_PanelFilter.setLayout(aside_PanelFilterLayout);
        aside_PanelFilterLayout.setHorizontalGroup(
            aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aside_PanelFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_Filter)
                    .addComponent(button_AddBy1Month)
                    .addComponent(calendar_SalesMadeFilterStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aside_PanelFilterLayout.createSequentialGroup()
                        .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_StartDate)
                            .addComponent(label_FilterTargetDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_StartDateSelected)
                            .addComponent(text_DateSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aside_PanelFilterLayout.createSequentialGroup()
                        .addComponent(button_AddBy1Week)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_AddBy1Year, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aside_PanelFilterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_ReloadTable)
                            .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(calendar_SalesMadeFilterEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aside_PanelFilterLayout.createSequentialGroup()
                                    .addComponent(label_EndDate)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(text_EndDateSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        aside_PanelFilterLayout.setVerticalGroup(
            aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aside_PanelFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_AddBy1Week)
                    .addComponent(button_AddBy1Month)
                    .addComponent(button_AddBy1Year))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_FilterTargetDate)
                    .addComponent(text_DateSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_StartDateSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_EndDate)
                        .addComponent(text_EndDateSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_StartDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar_SalesMadeFilterStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar_SalesMadeFilterEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aside_PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Filter)
                    .addComponent(button_ReloadTable))
                .addGap(721, 721, 721))
        );

        button_ManagerPanel.setText("To Manager Panel");
        button_ManagerPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ManagerPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout body_PanelMainLayout = new javax.swing.GroupLayout(body_PanelMain);
        body_PanelMain.setLayout(body_PanelMainLayout);
        body_PanelMainLayout.setHorizontalGroup(
            body_PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(body_PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(body_PanelMainLayout.createSequentialGroup()
                        .addComponent(label_SalesTableTitle)
                        .addGap(25, 25, 25)
                        .addComponent(button_ManagerPanel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aside_PanelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        body_PanelMainLayout.setVerticalGroup(
            body_PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_PanelMainLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(body_PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_PanelMainLayout.createSequentialGroup()
                        .addGroup(body_PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_SalesTableTitle)
                            .addComponent(button_ManagerPanel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(aside_PanelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body_PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body_PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void table_SalesDatabaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_SalesDatabaseMouseClicked
        getSelectedDateFromTable();
    }//GEN-LAST:event_table_SalesDatabaseMouseClicked

    private void button_FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_FilterActionPerformed
        filterTableBasedOnDate();
    }//GEN-LAST:event_button_FilterActionPerformed

    private void button_ReloadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReloadTableActionPerformed
        reloadSalesTable();
    }//GEN-LAST:event_button_ReloadTableActionPerformed

    private void calendar_SalesMadeFilterStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar_SalesMadeFilterStartPropertyChange
        getStartDateFromCalendar();
    }//GEN-LAST:event_calendar_SalesMadeFilterStartPropertyChange

    private void text_EndDateSelectedPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_text_EndDateSelectedPropertyChange

                                              
    }//GEN-LAST:event_text_EndDateSelectedPropertyChange

    private void calendar_SalesMadeFilterEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar_SalesMadeFilterEndPropertyChange
        getEndDateFromCalendar();
    }//GEN-LAST:event_calendar_SalesMadeFilterEndPropertyChange

    private void button_AddBy1WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AddBy1WeekActionPerformed
        addDateBy(0); 
    }//GEN-LAST:event_button_AddBy1WeekActionPerformed

    private void button_AddBy1MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AddBy1MonthActionPerformed
        addDateBy(1);
    }//GEN-LAST:event_button_AddBy1MonthActionPerformed

    private void button_AddBy1YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AddBy1YearActionPerformed
        // TODO add your handling code here:
        addDateBy(2);
    }//GEN-LAST:event_button_AddBy1YearActionPerformed

    private void button_ManagerPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ManagerPanelActionPerformed
        goToManagerPanel();
    }//GEN-LAST:event_button_ManagerPanelActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerSalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerSalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerSalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerSalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ManagerSalesDatabase().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aside_PanelFilter;
    private javax.swing.JPanel body_PanelMain;
    private javax.swing.JButton button_AddBy1Month;
    private javax.swing.JButton button_AddBy1Week;
    private javax.swing.JButton button_AddBy1Year;
    private javax.swing.JButton button_Filter;
    private javax.swing.JButton button_ManagerPanel;
    private javax.swing.JButton button_ReloadTable;
    private com.toedter.calendar.JCalendar calendar_SalesMadeFilterEnd;
    private com.toedter.calendar.JCalendar calendar_SalesMadeFilterStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_EndDate;
    private javax.swing.JLabel label_FilterTargetDate;
    private javax.swing.JLabel label_SalesTableTitle;
    private javax.swing.JLabel label_StartDate;
    private javax.swing.JTable table_SalesDatabase;
    private javax.swing.JTextField text_DateSelected;
    private javax.swing.JTextField text_EndDateSelected;
    private javax.swing.JTextField text_StartDateSelected;
    // End of variables declaration//GEN-END:variables
}
