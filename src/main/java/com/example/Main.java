package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException, InterruptedException{
        System.out.println("Hello world!");
        Weather weather = new Weather();
        weather.getWeather(new Scanner(System.in).next());
    }
}