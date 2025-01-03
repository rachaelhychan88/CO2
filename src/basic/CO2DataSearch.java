package basic;

import java.io.*;

public class CO2DataSearch{
    public static void main(String[] args){
        searchYearCo2PerCap("Canada");
    }

    public static int[] searchYearCo2PerCap(String country){
        // Set path
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