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
}
