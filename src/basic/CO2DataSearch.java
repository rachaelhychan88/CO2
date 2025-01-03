package basic;

import java.io.*;

/**
 * The CO2Data Search class contains the methods used to search the CO2Data.csv file
 * for relevant information. 
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
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        String searchWord = country;
        int[] result = new int[2];

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            int lineNumber = 0; // Tracks the current line number
            int linesWithWord = 0; // Tracks the number of lines where the word appears
            int firstOccurrenceLine = -1; // Tracks the line number of the first occurrence

            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",", -1);
                lineNumber++; // Increment line number

                // Check if the line contains the search word
                if (lineData[0].equals(searchWord)) {
                    linesWithWord++;

                    // Set the first occurrence line if it hasn't been set yet
                    if (firstOccurrenceLine == -1) {
                        firstOccurrenceLine = lineNumber;
                    }
                }
            }

            result[0] = firstOccurrenceLine;
            result[1] = linesWithWord;
            
        } catch (FileNotFoundException e) {
        } catch(IOException e) {
        }

        return result;
    }

    /**
     * This method searches the CO2Data.csv file for the CO2 Emissions per Capita of a specific country at a specific year
     * 
     * @param country the country to search the data for
     * @param year the year to search the data for
     * @return a CountryYearCO2 object with the country, year, and the CO2 Emissions per Capita of that country for that year
     */
    public static CountryYearCO2 findCO2fromYear(String country, int year){
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        String searchWord = country;
        String searchYear = Integer.toString(year);

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",", -1);

                // Check if the line contains the search word
                if (lineData[0].equals(searchWord) && lineData[1].equals(searchYear)) {
                    CountryYearCO2 yearResult = new CountryYearCO2(country, year, Double.parseDouble(lineData[15]));

                    return yearResult;
                }
            }
            
        } catch (FileNotFoundException e) {
        } catch(IOException e) {
        }

        return null;
    }
}