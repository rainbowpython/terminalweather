package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.*;
public class Weather {
    private String current;
    
    public static final int CURRENT = 1;
    private int precipitation;
    private int windSpeed;
    private double temp;
    private String icon;
    Weather(){

    }
    public void getWeather(String city) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "?unitGroup=metric&include=current&key=UCVG62VSNL8D8CJHSR2KXK6L5&contentType=json"))
        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        FileWriter file = new FileWriter("weather.txt");
        file.write(response.body());
        System.out.println(response.body().substring(response.body().indexOf("currentConditions")));
        current = response.body().substring(response.body().indexOf("currentConditions"));
        for (int i = 0; i < response.body().length(); i++) {
            //System.out.println(response.body());
            
        }
        
    }

    public String getCurrentWeather(){
        return current;
    }
}
