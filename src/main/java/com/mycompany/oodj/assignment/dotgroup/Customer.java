/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import javax.swing.JFrame;
/**
 *
 * @author purrnama
 */
public class Customer extends User {
    public Customer(String username, String password){
        super(username, password);
    }
    
    public static Customer parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        Customer c = new Customer(name, password);
        return c;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.CUSTOMER;
    }
    
    public JFrame openPanel(JFrame login){
        //replace this with your implementation
        return new JFrame();
    }
    
    public Issue getIssue() {
    	return issue;
    }
    
    public void setIssue(Issue issue) {
    	this.issue = issue;
    }
     
    private Issue issue;
}
