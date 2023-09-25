package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Time;

import org.json.*;
public class Weather {
    private String current;
    
    public static final int CURRENT = 1;

    private String precipitation;
    private String windSpeed;
    private String temp;
    private String time;
    private String icon;
    private String humidity;
    private String windDir;
    Weather(String city) throws IOException, InterruptedException{
        getWeather(city);
    }

    public void getWeather(String city) throws IOException, InterruptedException{
        //api request
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "?unitGroup=us&include=current&key=UCVG62VSNL8D8CJHSR2KXK6L5&contentType=json"))
        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().substring(response.body().indexOf("currentConditions")));

        current = response.body().substring(response.body().indexOf("currentConditions"));
        precipitation = current.substring(current.indexOf("precip\":")+8, current.indexOf(",", current.indexOf("precip\":")+1));
        windSpeed =  current.substring(current.indexOf("windspeed\":")+11, current.indexOf(",", current.indexOf("windspeed\":")+1));
        temp = current.substring(current.indexOf("temp\":")+6, current.indexOf(",", current.indexOf("temp\":")+1));
        icon = current.substring(current.indexOf("icon\":")+6, current.indexOf(",", current.indexOf("icon\":")+1));
        windDir = current.substring(current.indexOf("winddir\":")+9, current.indexOf(",", current.indexOf("winddir\":")+1));
        System.out.println(precipitation);
    }

    public String getCurrentWeather(){
        return current;
    }

    //in inches
    public String getPrecipitation(){
        return precipitation;
    }

    //in miles per hour
    public String getWindSpeed(){
        return windSpeed;
    }

    //in degrees fahrenheit 
    public String getTemp(){
        return temp;
    }

    //returns string to determine icon
    public String getIcon(){
        return icon;
    }

    //! probably dont use this lmao, unit unsure
    public String getHumidity(){
        return humidity;
    }

    public String getWindDir(){
        return windDir;
    }

}
