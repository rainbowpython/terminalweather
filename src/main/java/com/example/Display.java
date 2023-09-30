package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Display {
    
    File file;

    public static final String COLOR_RESET = "\u001B[0m";
    Display(Weather weather) throws FileNotFoundException{
        //real image starts 14 spaces in
        file = new File("src\\main\\resources\\" +  weather.getIcon() + ".txt");
        Scanner scanner = new Scanner(file);

        int line = 0;
        int nameLength = 0;
        String iconColor = "\u001B[34m";

        if (weather.getIcon().equals("clear-day") || weather.getIcon().equals("clear-day")) {
            iconColor = "\u001B[33m";
        }

        while(scanner.hasNextLine()){
            System.out.print(iconColor + scanner.nextLine() + COLOR_RESET);

            if (line == 0) {
                System.out.print("Hello " + System.getProperty("user.name") + "!! :3");
                String name = "Hello " + System.getProperty("user.name") + "!! :3";
                nameLength = name.length();
            }
            
            if (line == 1) {
                System.out.print("\u001B[31m");
                for (int i = 0; i < nameLength; i++) {
                    System.out.print("=");
                }  
                System.out.print(COLOR_RESET);
            }

            if (line == 2) {
                LocalDateTime localDateTime = LocalDateTime.now();
                System.out.print(localDateTime);
            }
            
            System.out.println("");
            line++;
        }
    }
}
