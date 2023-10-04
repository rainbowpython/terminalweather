package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class Display {
    
    private File file;
    private Scanner scanner;
    private boolean rain;
    String iconColor;
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[37m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLACK = "\u001B[30m";
    public static final String MAGENTA = "\u001B[35m";

    Calendar calendar;
    Display(Weather weather) throws FileNotFoundException{
        //real image starts 14 spaces in
        file = new File("src\\main\\resources\\" +  weather.getIcon() + ".txt");
        scanner = new Scanner(file);
        calendar = Calendar.getInstance();

        int line = 0;
        int nameLength = 0;
        rain = (weather.getIcon().equals("rain") || weather.getIcon().equals("snow"));
        iconColor = WHITE;

        if (weather.getIcon().equals("clear-day") || weather.getIcon().equals("clear-night")) {
            iconColor = YELLOW;
        }
         
        while(scanner.hasNextLine()){
            
            if ((weather.getIcon().equals("partly-cloudy-day") || weather.getIcon().equals("partly-cloudy-night")) && line < 11) {
                colorizeIcon();
            } 
            else if (rain && line > 14) {
                System.out.print(CYAN + scanner.nextLine() + COLOR_RESET);
            }
            else {
                System.out.print(iconColor + scanner.nextLine() + COLOR_RESET);
            }
            

            if (line == 0) {
                String name = "Hello " + "\u001B[36m" + System.getProperty("user.name") + COLOR_RESET + "!! :3";
                System.out.print(name);
                nameLength = name.length();
            }

            if (line == 1) {
                System.out.print("Its ");
                switch (weather.getIcon()){
                    case "rain":
                        System.out.print("raining outside");
                    break;

                    case "cloudy":
                    case "partly-cloudy-night":
                    case "partly-cloudy-day":
                        System.out.print("cloudy outside");
                    break;

                    case "clear-day":
                        System.out.print("sunny outside");
                    break;

                    default:
                    break;
                }
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
                System.out.print(YELLOW + c + COLOR_RESET);
            }

            else{
                System.out.print(iconColor + c + COLOR_RESET);
            }
        }
    }
}
