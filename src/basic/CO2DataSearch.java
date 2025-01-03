package basic;

import java.io.*;

/**
 * The CO2Data Search class contains the methods used to search the CO2Data.csv file
 * for relevant information. 
 * 
 * @author: R. Chan
 */
public class CO2DataSearch {

    /**
     * This method searches the CO2Data.csv file for the first relevant line that contain the data of a specific country 
     * and how many lines the country appears in
     * 
     * @param country the country to search the data for
     * @return an integer array where the first element is the first occurrence of the country and the second 
     * element is the number of lines the country appears in
     */
    public static int[] searchYearCo2PerCap(String country){
        
        // Declare and initialize variables
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        String searchWord = country;
        int[] searchResult = new int[2];
        String line;
        int lineNumber = 0; // Tracks the current line number
        int linesWithWord = 0; // Tracks the number of lines where the word appears
        int firstOccurrenceLine = -1; // Tracks the line number of the first occurrence of the word


        try(BufferedReader br = new BufferedReader(new FileReader(path))){ // Set up BufferedReader to read the CO2Data.csv file

            while ((line = br.readLine()) != null){ 
                String[] lineData = line.split(",", -1); // Splits the line into different elements by the commas, ensuring to count empty spaces as elements
                lineNumber++;

                if (lineData[0].equals(searchWord)){ // Checks if the first element of the line (the country), matches the country of interest
                    linesWithWord++;

                    // Set the first occurrence line to the current line if it hasn't been set yet
                    if (firstOccurrenceLine == -1){
                        firstOccurrenceLine = lineNumber;
                    }
                }
            }

            // Sets the resulting array to the gathered data
            searchResult[0] = firstOccurrenceLine;
            searchResult[1] = linesWithWord;
            
        } catch (FileNotFoundException e){
        } catch(IOException e){
        }

        return searchResult;
    }

    /**
     * This method searches the CO2Data.csv file for the CO2 Emissions per Capita of a specific country at a specific year
     * 
     * @param country the country to search the data for
     * @param year the year to search the data for
     * @return a CountryYearCO2 object with the country, year, and the CO2 Emissions per Capita of that country for that year
     */
    public static CountryYearCO2 findCO2fromYear(String country, int year){

        // Declare and initialize variables
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        String searchWord = country;
        String searchYear = Integer.toString(year);
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){ // Set up BufferedReader to read the CO2Data.csv file

            while ((line = br.readLine()) != null){
                String[] lineData = line.split(",", -1); // Splits the line into different elements by the commas, ensuring to count empty spaces as elements

                if (lineData[0].equals(searchWord) && lineData[1].equals(searchYear)){ // Checks if the first element of the line (the country), matches the country of interest as well as if the second element of the line (the year), matches the year of interest
                    CountryYearCO2 yearResult = new CountryYearCO2(country, year, Double.parseDouble(lineData[15])); // lineData[15] as the 16th column of the csv file is the CO2 emissions per capita data
                    return yearResult;
                }
            }
            
        } catch (FileNotFoundException e){
        } catch(IOException e){
        }

        return null;
    }
}