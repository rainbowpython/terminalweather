package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException, InterruptedException{
        Weather weather = new Weather(new Scanner(System.in).next());
    }
}