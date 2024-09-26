/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
/**
 *
 * @author purrnama
 */
public class Customer extends User {
    
    private boolean hasReportedHallIssue;
    
    public Customer(String username, String password){
        super(username, password);
        this.hasReportedHallIssue = false;
    }
    
    public Customer(String username, String password, boolean hasReportedHallIssue){
        super(username, password);
        this.hasReportedHallIssue = hasReportedHallIssue;
    }
    
    public static Customer parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        String strHasReportedHallIssue = null;
        Customer c = new Customer(name, password);
        try {
            strHasReportedHallIssue = col[2];
        } catch (ArrayIndexOutOfBoundsException e){
        }

        if (strHasReportedHallIssue != null) {
            boolean hasReported = "true".equals(strHasReportedHallIssue);
            c = new Customer(name, password, hasReported);
        }  else {
            c = new Customer(name, password);
        }
        
        return c;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.CUSTOMER + "," + this.hasReportedHallIssue;
    }
    
    public JFrame openPanel(JFrame login){
        CustomerDashboard panel = new CustomerDashboard(this);
        panel.setVisible(true);
        panel.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                login.setVisible(true);
            }
        });
        return panel;
    }
    public boolean getHasReportedHallIssue() {
        return hasReportedHallIssue;
    }

    public void setHasReportedHallIssue(boolean hasReportedHallIssue) {
        this.hasReportedHallIssue = hasReportedHallIssue;
    }
    
    public Issue getIssue() {
    	return issue;
    }
    
    public void setIssue(Issue issue) {
    	this.issue = issue;
    }
     
    private Issue issue;
}
