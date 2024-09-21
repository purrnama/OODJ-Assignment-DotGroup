/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
/**
 *
 * @author purrnama
 */
public class OODJAssignmentDotGroup {

    public static void main(String[] args) {
        FileOperation file = FileOperation.getInstance();
        System.out.println("Hello World!");
        Hall h = new Hall("Auditorium",200.00,500);
        User u = new User("Qayyum","12345", RoleType.ADMINISTRATOR);
        Period p = new Period(LocalDateTime.now(), LocalDateTime.now(), h, PeriodType.BOOKING, "Period", new Customer("foo", "bar"), new Scheduler("foo", "bar"), PeriodStatus.PENDING_PAYMENT);
        System.out.println(p.toString());
    }
}
