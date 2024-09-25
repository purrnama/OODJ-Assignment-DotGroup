/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author purrnama
 */
public class FileOperation {
    
    private File hallsFile;
    private File usersFile;
    private File scheduleFile;
    private File issuesFile;
    private File salesFile;
    
    private FileOperation() {
        System.out.println("Initializing files..");
        try {
            hallsFile = new File("data", "halls.txt");
            usersFile = new File("data", "users.txt");
            scheduleFile = new File("data", "schedule.txt");
            issuesFile = new File("data", "issues.txt");
            salesFile = new File("data", "sales.txt");
            
            if(hallsFile.createNewFile()){
                System.out.println("New halls.txt file created.");
            } else {
                System.out.println("Reading from existing halls.txt file");
            }
            if(usersFile.createNewFile()){
                System.out.println("New users.txt file created.");
            } else {
                System.out.println("Reading from existing users.txt file");
            }
            if(scheduleFile.createNewFile()){
                System.out.println("New schedule.txt file created.");
            } else {
                System.out.println("Reading from existing schedule.txt file");
            }
            if(issuesFile.createNewFile()){
                System.out.println("New issues.txt file created.");
            } else {
                System.out.println("Reading from existing issues.txt file");
            }
            if(salesFile.createNewFile()){
                System.out.println("New sales.txt file created.");
            } else {
                System.out.println("Reading from existing sales.txt file");
            }
            
        } catch (IOException e){
            System.out.println("An error occurred when initializing files.");
        }
    }
    
    // generic method for writing to a file, used internally
    private void write(File f, String s){
         try{
            FileWriter fw = new FileWriter(f, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
            System.out.println("Successfully wrote to " + f.getName());
        }
        catch (IOException e){
            System.out.println("An error occurred when writing to " + f.getName());
        }
    }
    private void overwrite(File f, ArrayList<String> list){
        try{
            FileWriter fw = new FileWriter(f, false);
            try (BufferedWriter bw = new BufferedWriter(fw)){
                for (String line : list){
                    bw.write(line);
                    bw.newLine();
                }
                bw.flush();
            }
            System.out.println("Successfully overwrote " + f.getName());
        }
        catch(IOException e) {
            System.out.println("An error occurred when overwriting " + f.getName());
        }
    }
    // generic method for reading a file, used internally
    // returns an ArrayList
    private ArrayList<String> readFile(File f) {
        ArrayList data = new ArrayList();
        try{
            Scanner s = new Scanner(f);
            
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(!line.isEmpty()){
                    data.add(line);
                }
            }
        }
        catch (IOException e){
        }
        return data;
    }
    
    // generic method for removing a matching line in file, used internally
    private void removeLine(File f, String s){
        
        ArrayList<String> currentList = readFile(f);
        ArrayList<String> newList = new ArrayList();
        int filesRemoved = 0;
        for (String line : currentList){
            if(!line.equals(s)){
                newList.add(line);
            } else {
                filesRemoved++;
            }
        }
        overwrite(f, newList);
        System.out.println("Removed " + filesRemoved + " file(s) from " + f.getName());
    }
    private void editLine(File f, String old_s, String new_s){
        ArrayList<String> currentList = readFile(f);
        ArrayList<String> newList = new ArrayList();
        int filesEdited = 0;
        for (String line : currentList){
            if(!line.equals(old_s)){
                newList.add(line);
            } else {
                newList.add(new_s);
                filesEdited++;
            }
        }
        overwrite(f, newList);
        System.out.println("Edited " + filesEdited + " from " + f.getName());
    }
    
    private void warning(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    private boolean isPeriodClashing(Period p1){
        ArrayList<Period> periods = read(FileType.SCHEDULE);
        for(Period p2 : periods){
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p1.getStartTime().isBefore(p2.getEndTime()));
            System.out.println(p2.getStartTime().isBefore(p1.getEndTime()));
            if(p1.getStartTime().isBefore(p2.getEndTime()) && p2.getStartTime().isBefore(p1.getEndTime())){
                return true;
            }
        }
        return false;
    }
    
    private boolean isUserExists(User u1){
        ArrayList<User> users = read(FileType.USERS);
        for(User u2 : users){
            if(u2.getUsername().equals(u1.getUsername())){
                return true;
            }
        }
        return false;
    }
    
    // create method overloads for each class
    public void create(Hall h){
        write(hallsFile, h.toString());
    }
    public void create(User u){
        if(isUserExists(u)){
            warning("User with username " + u.getUsername() + " already exists. Please use a different username." , "Invalid User");
        }
        write(usersFile, u.toString());
    }
    public void create(Period p){
        if(isPeriodClashing(p)){
            warning("New period is clashing with an existing period.", "Invalid Period");
            return;
        }
        write(scheduleFile, p.toString());
    }
    public void create(Issue i){
        write(issuesFile, i.toString());
    }
    
    public ArrayList read(FileType type){
        if(type == FileType.HALLS){
            ArrayList<String> data = readFile(hallsFile);
            ArrayList<Hall> hallData = new ArrayList();
            for (String line : data){
                Hall h = Hall.parse(line);
                hallData.add(h);
            }
            return hallData;
        }
        if(type == FileType.USERS){
            ArrayList<String> data = readFile(usersFile);
            ArrayList userData = new ArrayList();
            for (String line : data){
                String[] col = line.split(",");
                switch (RoleType.valueOf(col[2])){
                    case MANAGER: {
                        Manager m = Manager.parse(line);
                        userData.add(m);
                        continue;
                    }
                    case CUSTOMER: {
                        Customer c = Customer.parse(line);
                        userData.add(c);
                        continue;
                    }
                    case SCHEDULER: {
                        Scheduler s = Scheduler.parse(line);
                        userData.add(s);
                        continue;
                    }
                    case ADMINISTRATOR: {
                        Administrator a = Administrator.parse(line);
                        userData.add(a);
                        continue;
                    }
                }
            }
            return userData;
        }
        if(type == FileType.SCHEDULE){
            ArrayList<String> data = readFile(scheduleFile);
            ArrayList<Period> periodData = new ArrayList();
            for (String line : data){
                Period p = Period.parse(line);
                periodData.add(p);
            }
            return periodData;
        }
        if(type == FileType.ISSUES){
            ArrayList<String> data = readFile(issuesFile);
            ArrayList<Issue> issueData = new ArrayList();
            for (String line : data){
                Issue i = Issue.parse(line);
                issueData.add(i);
            }
            return issueData;
        }
        
        if (type == FileType.SALES) {
            ArrayList<String> data = readFile(salesFile);
            ArrayList<ManagerSales> salesData = new ArrayList();
            for (String line : data) {
                ManagerSales i = ManagerSales.parse(line);
                salesData.add(i);
            }
            return salesData;
        }
        return new ArrayList();
    }
    
    
    public void update(Hall old_h, Hall new_h){
        editLine(hallsFile, old_h.toString(), new_h.toString());
    }
    public void update(User old_u, User new_u){
        editLine(usersFile, old_u.toString(), new_u.toString());
    }
    public void update(Period old_p, Period new_p){
        editLine(scheduleFile, old_p.toString(), new_p.toString());
    }
    public void update(Issue old_i, Issue new_i){
        editLine(issuesFile, old_i.toString(), new_i.toString());
    }
    
    public void delete(Hall h){
        removeLine(hallsFile, h.toString());
    }
    public void delete(User u){
        removeLine(usersFile, u.toString());
    }
    public void delete(Period p){
        removeLine(scheduleFile, p.toString());
    }
    public void delete(Issue i){
        removeLine(issuesFile, i.toString());
    }
    
    public static FileOperation getInstance() {
        return FileOperationHolder.INSTANCE;
    }
    
    private static class FileOperationHolder {

        private static final FileOperation INSTANCE = new FileOperation();
    }
}
