/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.time.LocalDateTime;
/**
 *
 * @author purrnama
 */
public class Period {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Hall hall;
    private PeriodType type;
    private String title;
    private Customer bookedBy;
    private Scheduler scheduledBy;
    private PeriodStatus status;
    
    public Period(){
        
    }

    public Period(LocalDateTime startTime, LocalDateTime endTime, Hall hall, PeriodType type, String title, Customer bookedBy, Scheduler scheduledBy, PeriodStatus status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;
        this.type = type;
        this.title = title;
        this.bookedBy = bookedBy;
        this.scheduledBy = scheduledBy;
        this.status = status;
    }

    @Override
    public String toString() {
        return startTime + "," + endTime + "," + hall + "," + type + "," + title + "," + bookedBy + "," + scheduledBy + "," + status;
    }
    
    public static Period parse(String line){
        String[] col = line.split(",");
        LocalDateTime startTime = LocalDateTime.parse(col[0]);
        LocalDateTime endTime = LocalDateTime.parse(col[1]);
        Hall hall = Hall.parse(col[2] + "," + col[3] + "," + col[4]);
        PeriodType type = PeriodType.valueOf(col[5]);
        String title = col[6];
        Customer bookedBy = new Customer(col[7], col[8]);
        Scheduler scheduledBy = new Scheduler(col[10], col[11]);
        PeriodStatus status = PeriodStatus.valueOf(col[13]);
        Period p = new Period(startTime, endTime, hall, type, title, bookedBy, scheduledBy, status);
        return p;
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

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public PeriodType getType() {
        return type;
    }

    public void setType(PeriodType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Scheduler getScheduledBy() {
        return scheduledBy;
    }

    public void setScheduledBy(Scheduler scheduledBy) {
        this.scheduledBy = scheduledBy;
    }

    public PeriodStatus getStatus() {
        return status;
    }

    public void setStatus(PeriodStatus status) {
        this.status = status;
    }
    
    
    
}
