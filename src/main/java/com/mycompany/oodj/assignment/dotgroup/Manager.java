package com.mycompany.oodj.assignment.dotgroup;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    
    //overriding openPanel method in abstract User class
    public JFrame openPanel(JFrame login){
        ManagerPanel panel = new ManagerPanel();
        panel.setVisible(true);
        panel.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                login.setVisible(true);
            }
        });
        return panel;
    }
}
