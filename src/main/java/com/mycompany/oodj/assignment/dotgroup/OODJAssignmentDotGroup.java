/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodj.assignment.dotgroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 *
 * @author purrnama
 */
public class OODJAssignmentDotGroup {

    public static void main(String[] args) {
        FileOperation file = FileOperation.getInstance();
        System.out.println("Hello World!");
        Hall h = new Hall("Example",700.00,800);
        Scheduler s = new Scheduler("Qayyum", "12345");
        //file.create(s);
        Period p = new Period(LocalDateTime.now(), LocalDateTime.now(), h, PeriodType.BOOKING, "Period", PeriodStatus.PENDING_PAYMENT, new Customer("foo", "bar"), new Scheduler("foo", "bar"));
        ArrayList<User> users = file.read(FileType.USERS);
        for( User u : users ) {
            System.out.println(u.toString());
        }
    }
}
