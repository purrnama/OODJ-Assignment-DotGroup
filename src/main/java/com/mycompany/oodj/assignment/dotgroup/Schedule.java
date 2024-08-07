/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author purrnama
 */
public class Schedule {
    
    private ArrayList<Period> periods;
    private File periodFile;
    
    private Schedule() {
        System.out.println("Schedule: Initializing..");
        try {
            periodFile = new File("data/periods.txt");
            if(periodFile.createNewFile()){
                System.out.println("Schedule: New periods.txt file created.");
            } else {
                System.out.println("Schedule: Reading from existing periods.txt file");
            }
            Scanner scanner = new Scanner(periodFile);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] splitData = data.split(",");
                Period p = new Period();
            }
        } catch (IOException e){
            System.out.println("Schedule: An error occurred.");
        }
    }
    
    public static Schedule getInstance() {
        return ScheduleHolder.INSTANCE;
    }
    
    private static class ScheduleHolder {

        private static final Schedule INSTANCE = new Schedule();
    }
}
