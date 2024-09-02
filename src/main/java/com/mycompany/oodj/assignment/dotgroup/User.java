/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.time.Instant;
/**
 *
 * @author purrnama
 */
public class User {
    private String username;
    private String password;
    private RoleType role;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.role = RoleType.UNASSIGNED;
    }
    
    public User(String username, String password, RoleType role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + role;
    }
    
    public static User parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        String password = col[1];
        User u = new User(name, password, RoleType.valueOf(col[2]));
        return u;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
    
    
}
