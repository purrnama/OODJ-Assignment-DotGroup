/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;

/**
 *
 * @author purrnama
 */
public class Hall {
    private String name;
    private double hourlyRate;
    private int totalSeats;

    public Hall(String name, double hourlyRate, int totalSeats) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.totalSeats = totalSeats;
    }
    
    public static Hall parse(String line){
        String[] col = line.split(",");
        String name = col[0];
        double hourlyRate = Double.parseDouble(col[1]);
        int totalSeats = Integer.parseInt(col[2]);
        Hall h = new Hall(name, hourlyRate, totalSeats);
        return h;
    }

    @Override
    public String toString() {
        return name + "," + hourlyRate + "," + totalSeats;
    }
    
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    
    
}
