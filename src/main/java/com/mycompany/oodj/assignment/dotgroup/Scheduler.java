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
    
    private boolean isAssignedHallMaintenance;
    
    public Scheduler(String username, String password){
        super(username, password);
        this.isAssignedHallMaintenance = false;
    }
    
    public Scheduler(String username, String password, boolean isAssignedHallMaintenance){
        super(username, password);
        this.isAssignedHallMaintenance = isAssignedHallMaintenance; 
    }

    
    public static Scheduler parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        
        
        String isAssignedHallMaintenanceString = null;
        Scheduler s;
        try {
            isAssignedHallMaintenanceString = col[2];
        } catch (ArrayIndexOutOfBoundsException e){
            isAssignedHallMaintenanceString = null;
        }

        if (isAssignedHallMaintenanceString != null) {
            boolean isAssignedHallMaintenance = "true".equals(isAssignedHallMaintenanceString);
            s = new Scheduler(name, password, isAssignedHallMaintenance);
        }  else {
            s = new Scheduler(name, password);
        }
        
        return s;
    }
    
    public String toString(){
        return this.getUsername() + "," + this.getPassword() + "," + RoleType.SCHEDULER + "," + this.getIsAssignedHallMaintenance();
    }
    
    public JFrame openPanel(JFrame login){
        SchedulerPanel panel = new SchedulerPanel(this);
        panel.setVisible(true);
        panel.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                login.setVisible(true);
            }
        });
        return panel;
    }
    
    public boolean getIsAssignedHallMaintenance() {
        return isAssignedHallMaintenance;
    }

    public void setIsAssignedHallMaintenance(boolean isAssignedHallMaintenance) {
        this.isAssignedHallMaintenance = isAssignedHallMaintenance;
    }
}
