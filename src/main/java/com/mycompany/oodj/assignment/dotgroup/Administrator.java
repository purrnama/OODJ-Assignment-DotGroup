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
public class Administrator extends User {
    public Administrator(String username, String password){
        super(username, password);
    }
    
    public static Administrator parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        Administrator a = new Administrator(name, password);
        return a;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.ADMINISTRATOR;
    }
    
    public JFrame openPanel(JFrame login){
        AdminPanel panel = new AdminPanel();
        panel.setVisible(true);
        panel.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                login.setVisible(true);
            }
        });
        return panel;
    }
}
