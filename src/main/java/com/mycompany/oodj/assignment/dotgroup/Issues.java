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
public class Issues {
    
    private ArrayList<Issue> issues;
    private File issuesFile;
    
    private Issues() {
        System.out.println("Issues: Initializing..");
        issues = new ArrayList<>();
        try {
            issuesFile = new File("data/issues.txt");
            if(issuesFile.createNewFile()){
                System.out.println("Issues: New issues.txt file created.");
            } else {
                System.out.println("Issues: Reading from existing issues.txt file");
            }
            Scanner scanner = new Scanner(issuesFile);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] splitData = data.split(",");
                String subject = splitData[0];
                String body = splitData[1];
                IssueStatus status = IssueStatus.valueOf(splitData[2]);
                Issue i = new Issue(subject, body, status);
                issues.add(i);
            }
        } catch (IOException e){
            System.out.println("Issues: An error occurred.");
        }
    }
    
    public static Issues getInstance() {
        return HallsHolder.INSTANCE;
    }
    
    private static class HallsHolder {

        private static final Issues INSTANCE = new Issues();
    }
    
    public void AddNewHall(Hall h){
        
    }
}
