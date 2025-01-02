package basic;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountryYearCO2Collection {
    private List<CountryYearCO2> countryData;

    public CountryYearCO2Collection() {
        this.countryData = new ArrayList<>();
    }

    // make method for the searching for the data points here to add to the collection
    public void countryDataSearch(String country){
        int[] countryStartOccurrence = CO2DataSearch.searchYearCo2PerCap(country);
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        int countryLineCounter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            // To pass the lines that are NOT the country's data
            for(int index = 1; index < countryStartOccurrence[0]; index ++){
                br.readLine();
            }

            // Read the file line by line until it is no longer the country
            while ((line = br.readLine()) != null && countryLineCounter < countryStartOccurrence[1]) {
                // Split each line into parts based on commas
                String[] lineData = line.split(",");
                CountryYearCO2 currLineCountryYearCO2 = new CountryYearCO2(lineData[0], Integer.parseInt(lineData[1]), Double.parseDouble(lineData[15]));
                countryData.add(currLineCountryYearCO2);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }


    }

}
