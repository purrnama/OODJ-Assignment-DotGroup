package com.mycompany.oodj.assignment.dotgroup;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

public class Manager extends User {
    public Manager(String username, String password){
        super(username, password);
    }
    
    public static Manager parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        Manager m = new Manager(name, password);
        return m;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.MANAGER;
    }
    
    public JFrame openPanel(JFrame login){
        //replace this with your implementation
        return new JFrame();
    }
    
    public void viewSales() {
//    	String[] salesTotal = 
    	
    	
    }
    
    public void maintenance() {
    	
    }
    
    public void receiveCustomerIssue() {
    	
    }
    
    public void notifyScheduler(String message, NotificationLevel notificationLevel, NotificationType notificationType) {
    	
    	
    	scheduler.getUsername();
    }
    
    public void viewCustomerIssues() {
    	String issue = "";
    	customer.getIssue();
    	
    }
    
    public void setCustomerIssue(IssueStatusCustomer issueStatusCustomer) {
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
    
    private HashMap<Customer, Sales> sales;
    private ArrayList<Customer> customerIssue;
    private Customer customer;
    private Scheduler scheduler;
}
