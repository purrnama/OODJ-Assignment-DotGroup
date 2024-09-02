/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Period(LocalDateTime startTime, LocalDateTime endTime, Hall hall, PeriodType type, String title, PeriodStatus status, Customer bookedBy, Scheduler scheduledBy) {
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
        String strBookedBy = "null";
        if(bookedBy != null){
            strBookedBy = bookedBy.getUsername();
        }
        String strScheduledBy = "null";
        if(scheduledBy != null){
            strScheduledBy = scheduledBy.getUsername();
        }
        return startTime + "," + endTime + "," + hall + "," + type + "," + title + "," + status + "," + strBookedBy + "," + strScheduledBy;
    }
    
    public static Period parse(String line){
        FileOperation file = FileOperation.getInstance();
        String[] col = line.split(",");
        LocalDateTime startTime = LocalDateTime.parse(col[0]);
        LocalDateTime endTime = LocalDateTime.parse(col[1]);
        Hall hall = Hall.parse(col[2] + "," + col[3] + "," + col[4]);
        PeriodType type = PeriodType.valueOf(col[5]);
        String title = col[6];
        PeriodStatus status = PeriodStatus.valueOf(col[7]);
        Customer bookedBy = null;
        Scheduler scheduledBy = null;
        ArrayList<User> users = file.read(FileType.USERS);
        for(User u : users){
            if(u.getUsername() == col[8]){
                bookedBy = new Customer(u.getUsername(), u.getPassword());
            }
            if(u.getUsername() == col[9]){
                scheduledBy = new Scheduler(u.getUsername(), u.getPassword());
            }
        }
        Period p = new Period(startTime, endTime, hall, type, title, status, bookedBy, scheduledBy);
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
