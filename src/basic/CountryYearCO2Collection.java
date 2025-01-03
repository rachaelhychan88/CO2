package basic;

import java.io.*;
import java.util.ArrayList;

/**
 * This class contains methods that extract and organize the data from the CO2Data.csv file containing CO2 emissions for a specific country
 * into an array list of CounryYearCO2 objects. 
 * 
 * @author: R. Chan
 */
public class CountryYearCO2Collection {

    private ArrayList<CountryYearCO2> countryData;

    /**
     * Constructor - creates a new CountryYearCO2Collection instance
     */
    public CountryYearCO2Collection() {
        this.countryData = new ArrayList<>();
    }

    /**
     * This method makes the CountryYearCO2Collection contain CountryYearCO2 Objects for each 
     * relevant line of the CO2Data.csv file
     * @param country the country of interest
     */
    public void countryDataSearch(String country){

        // Declare and initialize variables
        int[] countryStartOccurrence = CO2DataSearch.searchYearCo2PerCap(country);
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        int countryLineCounter = 0;
        String line;
        double co2PerCapita;
        String[] lineData;
        String strCo2Value;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            // To pass the lines that are not the country's data
            for(int index = 1; index < countryStartOccurrence[0]; index ++){
                br.readLine();
            }

            // Read the file line by line until it is no longer the country
            while ((line = br.readLine()) != null && countryLineCounter < countryStartOccurrence[1]) {
                
                lineData = line.split(",", -1); // Splits the line by the comma, making sure to count the empty spaces as elements
                strCo2Value = lineData[15];

                if (!(strCo2Value.isEmpty())) {
                    co2PerCapita = Double.parseDouble(strCo2Value); // Parse the string to a double
                    CountryYearCO2 currLineCountryYearCO2 = new CountryYearCO2(lineData[0], Integer.parseInt(lineData[1]), co2PerCapita);
                    countryData.add(currLineCountryYearCO2);
                }

                countryLineCounter ++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }


    }

    /**
     * Getter method for the CountryYearCO2Collection instance
     * 
     * @return the CountryYearCO2Collection instance
     */
    public ArrayList<CountryYearCO2> getCountryData() {
        return countryData;
    }
}
