package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Period;

public class Sales {
    public Sales() {
            
    }

    public Sales(Customer customer, double totalSales, double hourly, double weekly, double monthly, double yearly) {
//        this.customer = null;
        this.totalSales = totalSales;
        this.hourly = hourly;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }
    public Sales(double totalSales, double hourly, double weekly, double monthly, double yearly) {
//        this.customer = null;
        this.totalSales = totalSales;
        this.hourly = hourly;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }

//    public static Sales parse(String line){
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
////        Sales sales = new Sales(null, totalSales, hourly, weekly, monthly, yearly);
////        return sales;
//        }
//        
//        return 
//    }
    
//    
    private int salesId;
    private String customer_id;
    private String customer_name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Period period;
    private Duration duration;
    private String hallType;
    private String reason;
    
//    private Customer customer;
    public Sales(int salesId, String customer_id, String customer_name, LocalDateTime startTime, LocalDateTime endTime, Period period, Duration duration, String hallType, String reason, double totalSales) {
        this.salesId = salesId;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
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
