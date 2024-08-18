package com.mycompany.oodj.assignment.dotgroup;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends User {
    public Manager(String username, String password){
        super(username, password, RoleType.MANAGER);
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
