package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Sale {
    
    private String saleId;
    private String customerId;
    private String customerName;
    private LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String hallType;
    private double totalSale;

    public Sale(String salesId, String customerId, String customerName, LocalDate date, LocalTime time, 
            String hallType, String description, double totalSales) {
        this.saleId = salesId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.hallType = hallType;
        this.description = description;
        this.totalSale = totalSales;
    }
    
    @Override
    public String toString() {
        return saleId + "," + customerId + "," + customerName + "," +
            date + "," + time + "," + hallType + "," + 
            description + "," + totalSale;
    }
    
    public static Sale parse(String line){
        String[] col = line.split(",");

        String salesId = col[0];
        String customerId = col[1];
        String customerName = col[2];
        
        LocalDate date = LocalDate.parse(col[3], DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime time = LocalTime.parse(col[4], DateTimeFormatter.ISO_LOCAL_TIME);
        
        String hallType = col[5];
        String description = col[6];
        double totalSales = Double.parseDouble(col[7]);
        
        Sale sales = new Sale(
            salesId, customerId, customerName, 
            date, time, hallType, description, totalSales
        );

        return sales;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
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

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }
}
