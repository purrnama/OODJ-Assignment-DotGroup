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
    
}
