package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Period;

import javax.swing.JFrame;

import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import java.util.Map;
import java.util.HashMap;

public class ManagerSales {
    public ManagerSales() {
        this.salesId = "sales-Test";
        this.customerId = "ID_test";
        this.customerName = "DFFF";
        this.startTime = LocalDateTime.now();
        this.endTime = startTime.plusDays(2);
//        this.period = period;
        this.duration = Duration.between(startTime, endTime);
          
        this.durationInSeconds = Math.abs(duration.toSeconds());
        
        this.hallType = "Auditorium";
        this.reason = "Waaa";
        
        double d = 1223.45;
        this.totalSales = d;
    }

    public ManagerSales(Customer customer, double totalSales, double hourly, double weekly, double monthly, double yearly) {
//        this.customer = null;
        this.totalSales = totalSales;
        this.hourly = hourly;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }
    public ManagerSales(double totalSales, double hourly, double weekly, double monthly, double yearly) {
//        this.customer = null;
        this.totalSales = totalSales;
        this.hourly = hourly;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }

    public ManagerSales(String salesId, String customerId, String customerName, LocalDateTime startTime, LocalDateTime endTime, 
            Duration durationInSeconds, String hallType, String reason, double totalSales) {
        this.salesId = salesId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.startTime = startTime; 
        this.endTime = endTime;
//        this.period = period;         
        this.duration = durationInSeconds;
        this.hallType = hallType;
        this.reason = reason;
        this.totalSales = totalSales;
    }
    
//    public ManagerSales(String salesId, String customerId, String customerName, LocalDateTime startTime, LocalDateTime endTime, 
//            Duration durationInSeconds, String hallType, String reason, double totalSales) {
//        this.salesId = salesId;
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.startTime = startTime; 
//        this.endTime = endTime;
////        this.period = period;         
//        this.duration = durationInSeconds;
//        this.hallType = hallType;
//        this.reason = reason;
//        this.totalSales = totalSales;
//    }
    
    public static ManagerSales parse(String line){
        String[] col = line.split(",");
//        Map<String, Object> map = new HashMap<>();
//        
//        map.put("ManagerSales ID",         new ValueTypeString(col[0]));
//        map.put("Customer ID",      new ValueTypeString(col[1]));
//        map.put("Customer Name",    new ValueTypeString(col[2]));
//        map.put("Start DateTime",   new ValueTypeDateTime(col[3]));
//        map.put("End DateTime",     new ValueTypeDateTime(col[4]));
//        map.put("Duration",         new ValueTypeString(col[5]));
//        map.put("Hall Type",        new ValueTypeString(col[6]));
//        map.put("Reason",           new ValueTypeString(col[7]));
//        map.put("Total ManagerSales",      new ValueTypeString(col[8]));
        
        
        String salesId = col[0];
        String customerId = col[1];
        String customerName = col[2];
        
        LocalDateTime startTime = LocalDateTime.parse(col[3]);
        
        String.format("%s", startTime);
        LocalDateTime endTime = LocalDateTime.parse(col[4]);
        String.format("%s", endTime);
        
        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration); // H M S Y M D W
//        long durationInSeconds = duration.toSeconds();
//        long durationInSeconds = duration.toSeconds();
//        long durationInSeconds = duration.toSeconds();
//        String durationInSeconds = duration.toString();
//        long durationInSeconds = col[5].toString();
        
        String hallType = col[6];
        String reason = col[7];
        double totalSales = Double.parseDouble(col[8]);
        
        ManagerSales sales = new ManagerSales(
            salesId, customerId, customerName, 
            startTime, endTime, duration, hallType, reason, totalSales
        );

//        ManagerSales sales = new ManagerSales();
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public long getDurationInSeconds() {
        return durationInSeconds;
    }
    
    public String getHallType() {
        return hallType;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily = daily;
    }

    public double getHourly() {
        return hourly;
    }

    public void setHourly(double hourly) {
        this.hourly = hourly;
    }

    public double getWeekly() {
        return weekly;
    }

    public void setWeekly(double weekly) {
        this.weekly = weekly;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public double getYearly() {
        return yearly;
    }

    public void setYearly(double yearly) {
        this.yearly = yearly;
    }
    
//    public String toString(){
////        return this.getUsername() + "," + this.getPassword() + "," + RoleType.ADMINISTRATOR;
//    }
    
    public JFrame openPanel(JFrame login){
        //replace this with your implementation
        return new JFrame();
    }
//    public static ManagerSales parse(String line){
//        
//        
//        String[] col = line.split(",");
//        String salesId = col[0];
//        
//        for (String cols : col) {
////        double totalSales = Double.parseDouble(col[1]);
////        double hourly = Double.parseDouble(col[2];
////        double weekly = Double.parseDouble(col[3];
////        double monthly = Double.parseDouble(col[4];
////        double yearly = Double.parseDouble(col[5];
////        ManagerSales sales = new ManagerSales(null, totalSales, hourly, weekly, monthly, yearly);
////        return sales;
//        }
//        
//        return 
//    }
    
//    
    private String salesId;
    private String customerId;
    private String customerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Period period;
    
    private Duration duration;

    private long durationInDays;
    private long durationInHours;
    private long durationInSeconds;
    private long durationInMilliSeconds;

    private String hallType;
    private String reason;
    
//    private Customer customer;
    public ManagerSales(String salesId, String customerId, String customerName, LocalDateTime startTime, LocalDateTime endTime, Period period, Duration duration, String hallType, String reason, double totalSales) {
        this.salesId = salesId;
        this.customerId = customerId;
        this.customerId = customerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.period = period;
        this.duration = duration;
        this.hallType = hallType;
        this.reason = reason;
        this.totalSales = totalSales;
    }
//    private Scheduler scheduler;
            
    private double totalSales;
    private double daily;
    private double hourly;
    private double weekly;
    private double monthly;
    private double yearly;	
}
