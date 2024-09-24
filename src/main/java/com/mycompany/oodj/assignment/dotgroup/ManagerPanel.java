package com.mycompany.oodj.assignment.dotgroup;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.io.*;

public class ManagerPanel extends javax.swing.JFrame {

    FileOperation file = FileOperation.getInstance();
    JFrame currentPanel = new JFrame();
    
//    String imagePath = "./OODJ-Assignment-DotGroup/assets/sales_dashboard_icon.jpg";
//    ImageIcon icon = new ImageIcon(imagePath);
//    JLabel label_SalesDashboardIcon = new JLabel(icon);
//    
    
    String image_path_sales = "./assets/sales_dashboard_128.png";
    String image_path_maintenance = "./assets/maintenance_64.png";

    public ManagerPanel() {
        initComponents();
       
//        label_ManagerPanelTitle.setHorizontalAlignment(JLabel.CENTER);
//        label_ManagerPanelTitle.setVerticalAlignment(JLabel.CENTER);
        
        button_SalesDashboard.setIcon(new ImageIcon(image_path_sales));
        button_Maintenance.setIcon(new ImageIcon(image_path_maintenance));
        
        
        java.net.URL imageURL = ManagerPanel.class.getResource(image_path_sales);
        System.out.println(new ImageIcon(image_path_sales));
        System.out.println(imageURL);
    }
    
    public void closePanel() { 
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

        panel_Manager = new javax.swing.JPanel();
        label_ManagerPanelTitle = new javax.swing.JLabel();
        button_Maintenance = new javax.swing.JButton();
        button_SalesDashboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);

        panel_Manager.setBackground(new java.awt.Color(153, 255, 51));

        label_ManagerPanelTitle.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        label_ManagerPanelTitle.setText("Manager Panel");

        button_Maintenance.setBackground(new java.awt.Color(255, 153, 51));
        button_Maintenance.setToolTipText("Go to Maintenance");
        button_Maintenance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_MaintenanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_MaintenanceMouseExited(evt);
            }
        });
        button_Maintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MaintenanceActionPerformed(evt);
            }
        });

        button_SalesDashboard.setBackground(new java.awt.Color(255, 153, 51));
        button_SalesDashboard.setToolTipText("Go to Sales Dashboard");
        button_SalesDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_SalesDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_SalesDashboardMouseExited(evt);
            }
        });
        button_SalesDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SalesDashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ManagerLayout = new javax.swing.GroupLayout(panel_Manager);
        panel_Manager.setLayout(panel_ManagerLayout);
        panel_ManagerLayout.setHorizontalGroup(
            panel_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ManagerLayout.createSequentialGroup()
                .addGroup(panel_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ManagerLayout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(button_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ManagerLayout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(label_ManagerPanelTitle)))
                .addContainerGap(292, Short.MAX_VALUE))
            .addGroup(panel_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_ManagerLayout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(button_SalesDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(611, Short.MAX_VALUE)))
        );
        panel_ManagerLayout.setVerticalGroup(
            panel_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ManagerLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(label_ManagerPanelTitle)
                .addGap(80, 80, 80)
                .addComponent(button_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(panel_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ManagerLayout.createSequentialGroup()
                    .addContainerGap(222, Short.MAX_VALUE)
                    .addComponent(button_SalesDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(133, 133, 133)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Manager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panel_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(121, 121, 121))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_MaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MaintenanceActionPerformed
        // TODO add your handling code here:
        closePanel();
        ManagerMaintenance panel = new ManagerMaintenance();
        panel.setVisible(true);
    }//GEN-LAST:event_button_MaintenanceActionPerformed

    private void button_MaintenanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_MaintenanceMouseEntered
        // TODO add your handling code here:
        button_Maintenance.setBackground(Color.red);
    }//GEN-LAST:event_button_MaintenanceMouseEntered

    private void button_MaintenanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_MaintenanceMouseExited
        // TODO add your handling code here:
        button_Maintenance.setBackground(Color.ORANGE);
    }//GEN-LAST:event_button_MaintenanceMouseExited

    private void button_SalesDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SalesDashboardMouseEntered
        // TODO add your handling code here:
        button_SalesDashboard.setBackground(Color.red);
    }//GEN-LAST:event_button_SalesDashboardMouseEntered

    private void button_SalesDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SalesDashboardMouseExited
        // TODO add your handling code here:
        button_SalesDashboard.setBackground(Color.ORANGE);
    }//GEN-LAST:event_button_SalesDashboardMouseExited

    private void button_SalesDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SalesDashboardActionPerformed
        // TODO add your handling code here:
        closePanel();
//        SalesDashboard panel = new SalesDashboard();
        ManagerSalesDatabase panel = new ManagerSalesDatabase();
        panel.setVisible(true);
    }//GEN-LAST:event_button_SalesDashboardActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Maintenance;
    private javax.swing.JButton button_SalesDashboard;
    private javax.swing.JLabel label_ManagerPanelTitle;
    private javax.swing.JPanel panel_Manager;
    // End of variables declaration//GEN-END:variables
}
