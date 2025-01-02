package basic;

import java.io.*;

public class CountryYearCO2 {
    private String country;
    private int year;
    private double co2PerCapita;

    // Constructor
    public CountryYearCO2(String country, int year, double co2PerCapita){
        this.country = country;
        this.year = year;
        this.co2PerCapita = co2PerCapita;
    }

    // Getters
    public String getCountry(){
        return country;
    }

    public int getYear(){
        return year;
    }

    public double getCo2PerCapita(){
        return co2PerCapita;
    }

    // Setters
    public void setCountry(String country){
        this.country = country;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setCo2PerCapita(double co2PerCapita){
        this.co2PerCapita = co2PerCapita;
    }

    @Override
    public String toString() {
        return "Country: " + country + ", Year: " + year + ", CO2 per capita: " + co2PerCapita;
    }
}
