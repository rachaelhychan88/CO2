package basic;

import java.io.*;
import java.util.Scanner;

public class CO2DataSearch {
    public static void main(String[] args){
        searchCountry("Canada");
    }

    public static int[] searchCountry(String country){
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
                lineNumber++; // Increment line number

                // Check if the line contains the search word
                if (line.contains(searchWord)) {
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

    
}