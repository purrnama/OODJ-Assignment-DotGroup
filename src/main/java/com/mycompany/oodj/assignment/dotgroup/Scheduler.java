/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author purrnama
 */
public class Scheduler extends User {
    
    public Scheduler(String username, String password){
        super(username, password);
    }

    
    public static Scheduler parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        Scheduler s = new Scheduler(name, password);
        return s;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.SCHEDULER;
    }
    
    public JFrame openPanel(JFrame login){
        SchedulerPanel panel = new SchedulerPanel();
        panel.setVisible(true);
        panel.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                login.setVisible(true);
            }
        });
        return panel;
    }
}
