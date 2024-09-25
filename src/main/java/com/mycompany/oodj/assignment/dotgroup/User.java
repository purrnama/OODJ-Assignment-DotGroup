/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.time.Instant;
import javax.swing.JFrame;
/**
 *
 * @author purrnama
 */
public abstract class User implements HasPanel {
    private String username;
    private String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public abstract String toString();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract JFrame openPanel(JFrame login);
    
}
