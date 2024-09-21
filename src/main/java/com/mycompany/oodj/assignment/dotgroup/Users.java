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
public class Users {
    
    private ArrayList<User> users;
    private File userFile;
    
    private Users() {
        System.out.println("Users: Initializing..");
        users = new ArrayList<>();
        try {
            userFile = new File("data/users.txt");
            if(userFile.createNewFile()){
                System.out.println("Users: New users.txt file created.");
            } else {
                System.out.println("Users: Reading from existing users.txt file");
            }
            Scanner scanner = new Scanner(userFile);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] splitData = data.split(",");
                String username = splitData[0];
                String password = splitData[1];
                RoleType role = RoleType.valueOf(splitData[2]);
                User u = new User(username, password, role);
                users.add(u);
            }
        } catch (IOException e){
            System.out.println("Users: An error occurred.");
        }
    }
    
    public static Users getInstance() {
        return HallsHolder.INSTANCE;
    }
    
    private static class HallsHolder {

        private static final Users INSTANCE = new Users();
    }
    
    public void AddNewHall(Hall h){
        
    }
}