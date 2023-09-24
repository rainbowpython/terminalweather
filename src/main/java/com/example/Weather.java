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
    private String precipitation;
    private String windSpeed;
    private String temp;
    private String icon;
    private String humidity;
    private String windDirection;
    Weather(){

    }
    public void getWeather(String city) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "?unitGroup=metric&include=current&key=UCVG62VSNL8D8CJHSR2KXK6L5&contentType=json"))
        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().substring(response.body().indexOf("currentConditions")));
        current = response.body().substring(response.body().indexOf("currentConditions"));
        temp = current.substring(current.indexOf("temp\":")+6, current.indexOf(",", current.indexOf("temp\":")+1));
        precipitation = current.substring(current.indexOf("precip\":")+6, current.indexOf(",", current.indexOf("temp\":")+1));
        System.out.println(temp);
        for (int i = 0; i < response.body().length(); i++) {
            
        }
        
    }

    public String getCurrentWeather(){
        return current;
    }

    public String getTemp(){
        return temp;
    }
}
