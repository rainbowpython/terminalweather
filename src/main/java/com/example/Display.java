package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Display {
    
    File file;
    Scanner scanner;
    public static final String COLOR_RESET = "\u001B[0m";
    Display(Weather weather) throws FileNotFoundException{
        //real image starts 14 spaces in
        file = new File("src\\main\\resources\\" +  weather.getIcon() + ".txt");
        scanner = new Scanner(file);

        int line = 0;
        int nameLength = 0;
        String iconColor = "\u001B[34m";

        if (weather.getIcon().equals("clear-day") || weather.getIcon().equals("clear-night")) {
            iconColor = "\u001B[33m";
        }

        while(scanner.hasNextLine()){
            
            if (weather.getIcon().equals("partly-cloudy-day")) {
                colorizeIcon();
            } else {
                System.out.print(iconColor + scanner.nextLine() + COLOR_RESET);
            }
            
            
            if (line == 0) {
                
                String name = "Hello " + "\u001B[36m" + System.getProperty("user.name") + COLOR_RESET + "!! :3";
                System.out.print(name);
                nameLength = name.length();
            }

            if (line == 1) {
                
                System.out.print("It's " );
            }
            
            if (line == 2) {
                System.out.print("\u001B[32m");
                for (int i = 0; i < nameLength; i++) {
                    System.out.print("=");
                }  
                System.out.print(COLOR_RESET);
            }

            if (line == 3) {
                LocalDate localDate = LocalDate.now();
                char[] date = localDate.toString().toCharArray();
                System.out.print("\u001B[34m"+"Date: " + COLOR_RESET);
                for (char c : date) {
                    if (c == '-') {
                        System.out.print('/');
                    } else {
                        System.out.print(c);
                    }
                }
            }

            
            
            System.out.println("");
            line++;
        }
    }

    //change colors on weathers
    public void colorizeIcon(){
        String next = scanner.nextLine();
        char[] line =  next.toCharArray();
        for (char c : line) {
            if (c == 'G' || c == 'B') {
                System.out.print("\u001B[33m" + c + COLOR_RESET);
            }
            else{
                System.out.print(c);
            }
        }
    }
}
