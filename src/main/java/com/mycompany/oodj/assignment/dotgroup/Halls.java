/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author purrnama
 */
public class Halls {
    
    private ArrayList<Hall> halls;
    private File hallFile;
    
    private Halls() {
        System.out.println("Halls: Initializing..");
        halls = new ArrayList<>();
        try {
            hallFile = new File("data/halls.txt");
            if(hallFile.createNewFile()){
                System.out.println("Halls: New halls.txt file created.");
            } else {
                System.out.println("Halls: Reading from existing halls.txt file");
            }
            Scanner scanner = new Scanner(hallFile);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] splitData = data.split(",");
                String name = splitData[0];
                double hourlyRate = Double.parseDouble(splitData[1]);
                int seats = Integer.parseInt(splitData[2]);
                Hall h = new Hall(name, hourlyRate, seats);
                halls.add(h);
            }
        } catch (IOException e){
            System.out.println("Halls: An error occurred.");
        }
    }
    
    public static Halls getInstance() {
        return HallsHolder.INSTANCE;
    }
    
    private static class HallsHolder {

        private static final Halls INSTANCE = new Halls();
    }
    
    public void AddNewHall(Hall h){
        
    }
}
