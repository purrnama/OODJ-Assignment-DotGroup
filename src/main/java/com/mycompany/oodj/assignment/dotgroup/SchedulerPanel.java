/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.ZoneId;
import java.time.ZoneOffset;
/**
 *
 * @author purrnama
 */
public class SchedulerPanel extends javax.swing.JFrame {

    FileOperation file = FileOperation.getInstance();
    ListSelectionModel cellHallSelection;
    ListSelectionModel cellPeriodSelection;
    Hall selectedHall;
    Period selectedPeriod;
    /**
     * Creates new form SchedulerPanel
     */
    public SchedulerPanel() {
        initComponents();
        updateHallsTable(loadHalls());
        
        //Add event listener when a row is selected in halls and periods table
        cellHallSelection = tblHalls.getSelectionModel();
        cellHallSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellHallSelection.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                selectedHall = getSelectedHall();
                if(selectedHall != null){
                    txtBoxName.setText(selectedHall.getName());
                    spinnerHourlyRate.setValue(selectedHall.getHourlyRate());
                    spinnerTotalSeats.setValue(selectedHall.getTotalSeats());
                }
            }
        });
        
        cellPeriodSelection = tblPeriods.getSelectionModel();
        cellPeriodSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellPeriodSelection.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                selectedPeriod = getSelectedPeriod();
                if(selectedPeriod != null){
                    txtBoxPeriodTitle.setText(selectedPeriod.getTitle());
                    cBoxPeriodHall.setSelectedItem(selectedPeriod.getHall().getName());
                    datePickerPeriodDate.setDate(Date.from(selectedPeriod.getStartTime().toInstant(ZoneOffset.UTC)));
                    cBoxPeriodType.setSelectedItem(selectedPeriod.getType().toString());
                    spinnerPeriodStartHour.setValue(selectedPeriod.getStartTime().getHour());
                    spinnerPeriodStartMinute.setValue(selectedPeriod.getStartTime().getMinute());
                    spinnerPeriodEndHour.setValue(selectedPeriod.getEndTime().getHour());
                    spinnerPeriodEndMinute.setValue(selectedPeriod.getEndTime().getMinute());
                }
            }
        });
        
        ArrayList<Hall> halls = file.read(FileType.HALLS);
        for( Hall h : halls){
            cBoxPeriodHall.addItem(h.getName());
        }
    }
    
    private ArrayList<Hall> loadHalls(){
        return file.read(FileType.HALLS);
    }
    
    private ArrayList<Period> loadPeriods(){
        return file.read(FileType.SCHEDULE);
    }
    
    private void updateHallsTable(ArrayList<Hall> halls){
        DefaultTableModel model = (DefaultTableModel)tblHalls.getModel();
        model.setRowCount(0);
        for (Hall h : halls){
            model.addRow(new Object[]{h.getName(), h.getHourlyRate(), h.getTotalSeats()});
        }
    }
    
    private void warning(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    private Hall getSelectedHall(){
        int selected = tblHalls.getSelectedRow();
        if(selected >= 0){
            String name = (String)tblHalls.getValueAt(selected, 0);
            double hourlyRate = (double)tblHalls.getValueAt(selected, 1);
            int totalSeats = (int)tblHalls.getValueAt(selected, 2);
            Hall h = new Hall(name, hourlyRate, totalSeats);
            return h;
        }
        return null;
    }
    
    private Period getSelectedPeriod(){
        int selected = tblPeriods.getSelectedRow();
        if(selected >= 0){
            String title = (String)tblPeriods.getValueAt(selected, 0);
            ArrayList<Period> periods = file.read(FileType.SCHEDULE);
            for(Period p : periods){
                if(p.getTitle().equals(title)){
                    return p;
                }
            }
        }
        return null;
    }
    
    private Hall getInputHall(){
        String name = txtBoxName.getText();
        double hourlyRate = (double)spinnerHourlyRate.getValue();
        int totalSeats = (int)spinnerTotalSeats.getValue();
        
        if(name.isEmpty()){
            warning("Hall name cannot be empty.", "Invalid Hall Name");
            return null;
        }
        
        Hall h = new Hall(name, hourlyRate, totalSeats);
        return h;
    }
    
    private Period getInputPeriod(){
        System.out.println((String)cBoxPeriodHall.getSelectedItem());
        if(txtBoxPeriodTitle.getText().isEmpty()){
            warning("Period title cannot be empty.", "Invalid Period Title");
            return null;
        }
        if(datePickerPeriodDate.getDate() == null){
            warning("Period date cannot be empty", "Invalid Period Date");
            return null;
        }
        
        String title = txtBoxPeriodTitle.getText();
        Hall hall = null;
        ArrayList<Hall> halls = file.read(FileType.HALLS);
        for( Hall h : halls ){
            if(h.getName().equals(cBoxPeriodHall.getSelectedItem())){
                hall = h;
                break;
            }
        }
        
        if(hall == null){
            warning("Invalid hall", "Invalid hall");
            return null;
        }
        LocalDate date = convertToLocalDate(datePickerPeriodDate.getDate());
        LocalTime startTime = LocalTime.of((Integer)spinnerPeriodStartHour.getValue(), (Integer)spinnerPeriodStartMinute.getValue());
        LocalTime endTime = LocalTime.of((Integer)spinnerPeriodEndHour.getValue(), (Integer)spinnerPeriodEndMinute.getValue());
        PeriodType type = PeriodType.valueOf(cBoxPeriodType.getSelectedItem().toString().toUpperCase());
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        Period p = new Period(startDateTime, endDateTime, hall, type, title, PeriodStatus.ACTIVE, null, null);
        return p;
    }
    
    private LocalDate convertToLocalDate(Date d){
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    private void updatePeriodsTable(ArrayList<Period> periods){
        DefaultTableModel model = (DefaultTableModel)tblPeriods.getModel();
        model.setRowCount(0);
        for (Period p : periods){
            model.addRow(new Object[]{p.getTitle(), p.getHall().getName(), p.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME), p.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), p.getType()});
        }
    }
    
    private ArrayList<Period> getPeriodsByLocalDate(LocalDate date){
    ArrayList<Period> periods = loadPeriods();
        ArrayList<Period> filtered = new ArrayList();
        for(Period p : periods){
            if(!date.isBefore(p.getStartTime().toLocalDate()) && !date.isAfter(p.getEndTime().toLocalDate())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelScheduler = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelHall = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBoxName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCreateHall = new javax.swing.JButton();
        btnEditHall = new javax.swing.JButton();
        btnDeleteHall = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHalls = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        spinnerHourlyRate = new javax.swing.JSpinner();
        spinnerTotalSeats = new javax.swing.JSpinner();
        panelSchedule = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        calSchedule = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeriods = new javax.swing.JTable();
        lblDate = new javax.swing.JLabel();
        txtBoxPeriodTitle = new javax.swing.JTextField();
        spinnerPeriodStartHour = new javax.swing.JSpinner();
        spinnerPeriodStartMinute = new javax.swing.JSpinner();
        datePickerPeriodDate = new com.toedter.calendar.JDateChooser();
        lblDate1 = new javax.swing.JLabel();
        lblDate2 = new javax.swing.JLabel();
        spinnerPeriodEndMinute = new javax.swing.JSpinner();
        spinnerPeriodEndHour = new javax.swing.JSpinner();
        lblDate3 = new javax.swing.JLabel();
        lblDate4 = new javax.swing.JLabel();
        lblDate5 = new javax.swing.JLabel();
        btnCreatePeriod = new javax.swing.JButton();
        btnEditPeriod = new javax.swing.JButton();
        btnDeletePeriod = new javax.swing.JButton();
        lblDate6 = new javax.swing.JLabel();
        cBoxPeriodHall = new javax.swing.JComboBox<>();
        cBoxPeriodType = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("SCHEDULER");

        jTabbedPane1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Manage Halls");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setText("Hourly Rate (RM):");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setText("Total Seats:");

        btnCreateHall.setText("Create");
        btnCreateHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateHallActionPerformed(evt);
            }
        });

        btnEditHall.setText("Edit");
        btnEditHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditHallActionPerformed(evt);
            }
        });

        btnDeleteHall.setText("Delete");
        btnDeleteHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHallActionPerformed(evt);
            }
        });

        tblHalls.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Hourly Rate (RM)", "Total Seats"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHalls);

        spinnerHourlyRate.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        spinnerTotalSeats.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout panelHallLayout = new javax.swing.GroupLayout(panelHall);
        panelHall.setLayout(panelHallLayout);
        panelHallLayout.setHorizontalGroup(
            panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHallLayout.createSequentialGroup()
                .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelHallLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spinnerHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelHallLayout.createSequentialGroup()
                            .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtBoxName, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHallLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteHall)
                            .addGroup(panelHallLayout.createSequentialGroup()
                                .addComponent(btnCreateHall)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditHall))
                            .addComponent(spinnerTotalSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelHallLayout.setVerticalGroup(
            panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHallLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelHallLayout.createSequentialGroup()
                        .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBoxName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(spinnerHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerTotalSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreateHall)
                            .addComponent(btnEditHall))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteHall)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Halls", panelHall);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Manage Schedule");

        calSchedule.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calSchedulePropertyChange(evt);
            }
        });

        tblPeriods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Hall", "Start Time", "End Time", "Type"
            }
        ));
        jScrollPane1.setViewportView(tblPeriods);

        lblDate.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate.setText("Date");

        spinnerPeriodStartHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerPeriodStartMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        lblDate1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate1.setText("Period Title:");

        lblDate2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate2.setText("Start Time:");

        spinnerPeriodEndMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        spinnerPeriodEndHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        lblDate3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate3.setText("Date:");

        lblDate4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate4.setText("End Time:");

        lblDate5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate5.setText("Type:");

        btnCreatePeriod.setText("Create");
        btnCreatePeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePeriodActionPerformed(evt);
            }
        });

        btnEditPeriod.setText("Edit");
        btnEditPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPeriodActionPerformed(evt);
            }
        });

        btnDeletePeriod.setText("Delete");
        btnDeletePeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePeriodActionPerformed(evt);
            }
        });

        lblDate6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDate6.setText("Hall:");

        cBoxPeriodHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxPeriodHallActionPerformed(evt);
            }
        });

        cBoxPeriodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOOKING", "MAINTENANCE" }));

        javax.swing.GroupLayout panelScheduleLayout = new javax.swing.GroupLayout(panelSchedule);
        panelSchedule.setLayout(panelScheduleLayout);
        panelScheduleLayout.setHorizontalGroup(
            panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScheduleLayout.createSequentialGroup()
                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScheduleLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScheduleLayout.createSequentialGroup()
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDate4)
                                    .addComponent(lblDate2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelScheduleLayout.createSequentialGroup()
                                        .addComponent(spinnerPeriodStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerPeriodStartMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelScheduleLayout.createSequentialGroup()
                                        .addComponent(spinnerPeriodEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerPeriodEndMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelScheduleLayout.createSequentialGroup()
                                        .addComponent(btnCreatePeriod)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEditPeriod))
                                    .addComponent(btnDeletePeriod)))
                            .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblDate5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cBoxPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(lblDate6))
                            .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cBoxPeriodHall, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelScheduleLayout.createSequentialGroup()
                                    .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblDate1)
                                        .addComponent(lblDate3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtBoxPeriodTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(datePickerPeriodDate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDate)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
                                .addGap(6, 6, 6)))))
                .addContainerGap())
        );
        panelScheduleLayout.setVerticalGroup(
            panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScheduleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScheduleLayout.createSequentialGroup()
                        .addComponent(calSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelScheduleLayout.createSequentialGroup()
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBoxPeriodTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate6)
                            .addComponent(cBoxPeriodHall, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDate3)
                            .addComponent(datePickerPeriodDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cBoxPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerPeriodStartMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerPeriodStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerPeriodEndMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate4)
                            .addComponent(spinnerPeriodEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreatePeriod)
                            .addComponent(btnEditPeriod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletePeriod)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Schedule", panelSchedule);

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel8.setText("Hall Booking Management System");

        javax.swing.GroupLayout panelSchedulerLayout = new javax.swing.GroupLayout(panelScheduler);
        panelScheduler.setLayout(panelSchedulerLayout);
        panelSchedulerLayout.setHorizontalGroup(
            panelSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSchedulerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addGroup(panelSchedulerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18))
        );
        panelSchedulerLayout.setVerticalGroup(
            panelSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSchedulerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScheduler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateHallActionPerformed
        Hall h = getInputHall();
        if(h != null){
            file.create(h);
            updateHallsTable(loadHalls());
        }
    }//GEN-LAST:event_btnCreateHallActionPerformed

    private void btnEditHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditHallActionPerformed
        Hall h = getInputHall();
        if(selectedHall != null && h != null){
            file.update(selectedHall, h);
            updateHallsTable(loadHalls());
        }
    }//GEN-LAST:event_btnEditHallActionPerformed

    private void btnDeleteHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHallActionPerformed
        Hall h = getSelectedHall();
        if(h != null){
            file.delete(h);
            updateHallsTable(loadHalls());
        }
    }//GEN-LAST:event_btnDeleteHallActionPerformed

    private void calSchedulePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calSchedulePropertyChange
        LocalDate date = convertToLocalDate(calSchedule.getDate());
        lblDate.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        updatePeriodsTable(getPeriodsByLocalDate(date));
        selectedPeriod = null;
    }//GEN-LAST:event_calSchedulePropertyChange

    private void btnCreatePeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePeriodActionPerformed
        Period p = getInputPeriod();
        if(p == null){
            return;
        }
        file.create(p);
        LocalDate date = convertToLocalDate(calSchedule.getDate());
        lblDate.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        updatePeriodsTable(getPeriodsByLocalDate(date));
    }//GEN-LAST:event_btnCreatePeriodActionPerformed

    private void btnEditPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPeriodActionPerformed
        Period p = getInputPeriod();
        if(p != null && selectedPeriod != null){
            file.update(selectedPeriod, p);
            LocalDate date = convertToLocalDate(calSchedule.getDate());
            lblDate.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            updatePeriodsTable(getPeriodsByLocalDate(date));
        }
        
    }//GEN-LAST:event_btnEditPeriodActionPerformed

    private void btnDeletePeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePeriodActionPerformed
        Period p = getSelectedPeriod();
        if(p != null){
            file.delete(p);
            LocalDate date = convertToLocalDate(calSchedule.getDate());
            lblDate.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            updatePeriodsTable(getPeriodsByLocalDate(date));
        }
    }//GEN-LAST:event_btnDeletePeriodActionPerformed

    private void cBoxPeriodHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxPeriodHallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBoxPeriodHallActionPerformed

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
            java.util.logging.Logger.getLogger(SchedulerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchedulerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchedulerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchedulerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchedulerPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateHall;
    private javax.swing.JButton btnCreatePeriod;
    private javax.swing.JButton btnDeleteHall;
    private javax.swing.JButton btnDeletePeriod;
    private javax.swing.JButton btnEditHall;
    private javax.swing.JButton btnEditPeriod;
    private javax.swing.JComboBox<String> cBoxPeriodHall;
    private javax.swing.JComboBox<String> cBoxPeriodType;
    private com.toedter.calendar.JCalendar calSchedule;
    private com.toedter.calendar.JDateChooser datePickerPeriodDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblDate3;
    private javax.swing.JLabel lblDate4;
    private javax.swing.JLabel lblDate5;
    private javax.swing.JLabel lblDate6;
    private javax.swing.JPanel panelHall;
    private javax.swing.JPanel panelSchedule;
    private javax.swing.JPanel panelScheduler;
    private javax.swing.JSpinner spinnerHourlyRate;
    private javax.swing.JSpinner spinnerPeriodEndHour;
    private javax.swing.JSpinner spinnerPeriodEndMinute;
    private javax.swing.JSpinner spinnerPeriodStartHour;
    private javax.swing.JSpinner spinnerPeriodStartMinute;
    private javax.swing.JSpinner spinnerTotalSeats;
    private javax.swing.JTable tblHalls;
    private javax.swing.JTable tblPeriods;
    private javax.swing.JTextField txtBoxName;
    private javax.swing.JTextField txtBoxPeriodTitle;
    // End of variables declaration//GEN-END:variables
}
