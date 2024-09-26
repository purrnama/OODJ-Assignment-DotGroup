package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class ManagerSales {

    public ManagerSales(String salesId, String customerId, String customerName, LocalDate date, LocalTime time, 
            String hallType, String description, double totalSales) {
        this.salesId = salesId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.hallType = hallType;
        this.description = description;
        this.totalSales = totalSales;
    }
    
    @Override
    public String toString() {
        return salesId + "," + customerId + "," + customerName + "," +
            date + "," + time + "," + hallType + "," + 
            description + "," + totalSales;
    }
    
    public static ManagerSales parse(String line){
        String[] col = line.split(",");

        String salesId = col[0];
        String customerId = col[1];
        String customerName = col[2];
        
        LocalDate date = LocalDateTime.parse(col[3]).toLocalDate();
        LocalTime time = LocalDateTime.parse(col[3]).toLocalTime();
        
        String hallType = col[4];
        String description = col[5];
        double totalSales = Double.parseDouble(col[6]);
        
        ManagerSales sales = new ManagerSales(
            salesId, customerId, customerName, 
            date, time, hallType, description, totalSales
        );

        return sales;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getHallType() {
        return hallType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
    
    private String salesId;
    private String customerId;
    private String customerName;
    private LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String hallType;
    private double totalSales;
}
