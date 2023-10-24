package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Display {
    
    private File file;
    private Scanner scanner;
    private boolean rain;
    String iconColor;
    LocalDate localDate;

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
        
        if (System.getProperty("os.name").toLowerCase().equals("windows")) {
            file = new File("src\\main\\resources\\" +  weather.getIcon() + ".txt");
        } else {
            file = new File("src/main/resources/" +  weather.getIcon() + ".txt");
        }

        scanner = new Scanner(file);
        calendar = Calendar.getInstance();
        localDate = LocalDate.now();
        int year = Integer.parseInt(localDate.toString().substring(0,localDate.toString().indexOf("-")));
        int month = Integer.parseInt(localDate.toString().substring(5,7));
        int day = Integer.parseInt(localDate.toString().substring(8));

        calendar.setTime(new Date(year, month, day));

        int line = 0;
        int nameLength = 0;
        rain = (weather.getIcon().equals("rain") || weather.getIcon().equals("snow"));
        iconColor = WHITE;

        if (weather.getIcon().equals("clear-day") || weather.getIcon().equals("clear-night")) {
            iconColor = YELLOW;
        }
         
        while(scanner.hasNextLine()){
            
            if ((weather.getIcon().equals("partly-cloudy-day") || weather.getIcon().equals("partly-cloudy-night")) && line < 13) {
                colorizeIcon();
            } 
            else if (rain && line > 14) {
                System.out.print(CYAN + scanner.nextLine() + COLOR_RESET);
            }
            else if (weather.getIcon().equals("thunder") && line > 16){
                System.out.println(YELLOW + scanner.nextLine() + COLOR_RESET);
            }
            else {
                System.out.print(iconColor + scanner.nextLine() + COLOR_RESET);
            }
            

            if (line == 0) {
                String name = "Hello " + CYAN + System.getProperty("user.name") + COLOR_RESET + "!! :3";
                System.out.print(name);
                nameLength = name.length();
            }

            if (line == 1) {
                switch (weather.getIcon()){
                    case "rain":
                        System.out.print("It's " + CYAN + "raining" + COLOR_RESET + " outside");
                    break;

                    case "cloudy":
                        System.out.print("It's " + WHITE + "cloudy" + COLOR_RESET + " outside");
                    break;

                    case "partly-cloudy-night":
                    case "partly-cloudy-day":
                        System.out.print("It's kinda " + WHITE + "cloudy" + COLOR_RESET + " outside");
                    break;

                    case "clear-night":
                    System.out.print("It's " + YELLOW + "clear" + COLOR_RESET + " outside");
                    break;

                    case "clear-day":
                    
                        System.out.print("It's " + YELLOW + "sunny" + COLOR_RESET + " outside");
                    break;

                    case "thunder":
                        System.out.print("It's" + YELLOW + "thundering" + COLOR_RESET + " outside");
                    break;

                    case "snow":
                        System.out.print("It's " + WHITE + "snowing" + COLOR_RESET + " outside");
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
                System.out.print(BLUE +"Date: " + COLOR_RESET);

                for (char c : date) {
                    if (c == '-') {
                        System.out.print('/');
                    } else {
                        System.out.print(c);
                    }
                }
            
            }

            if (line == 4) {
                String[] dayOfWeek = { "Tuesday", "Wensday", "Thursday", "Friday", "Saturday", "Sunday", "Monday",};
                System.out.print(BLUE + "Day: " + COLOR_RESET + dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]);
            }
            
            if (line == 5) {
                System.out.print(BLUE + "Temp: " + COLOR_RESET + weather.getTemp() + "°F");
            }

            if (line == 6) {
                System.out.print(BLUE + "Wind Speed: " + COLOR_RESET + weather.getWind + "°F");
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
