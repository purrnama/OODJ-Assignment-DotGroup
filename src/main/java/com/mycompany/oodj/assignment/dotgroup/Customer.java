/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;

/**
 *
 * @author purrnama
 */
public class Customer extends User {
    public Customer(String username, String password){
        super(username, password, RoleType.CUSTOMER);
    }
}
