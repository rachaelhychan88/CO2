package basic;

import java.io.*;

public class CO2Test {
    public static void main(String[] args) {
        // Test case 1: Valid country and year
        System.out.println("Running Test Case 1: Valid country and year");
        CountryYearCO2 result1 = findCO2fromYear("India", 2020);
        if (result1 != null) {
            System.out.println("Success: Data found");
            System.out.println("Country: " + result1.getCountry());
            System.out.println("Year: " + result1.getYear());
            System.out.println("CO2 Emissions: " + result1.getCo2PerCapita() + " tonnes per person");
        } else {
            System.out.println("Failed: No data found.");
        }

        // Test case 2: Invalid country
        System.out.println("\nRunning Test Case 2: Invalid country");
        CountryYearCO2 result2 = findCO2fromYear("Atlantis", 2020);
        if (result2 == null) {
            System.out.println("Success: No data found as expected.");
        } else {
            System.out.println("Failed: Unexpected data found.");
        }

        // Test case 3: Valid country, invalid year
        System.out.println("\nRunning Test Case 3: Valid country, invalid year");
        CountryYearCO2 result3 = findCO2fromYear("India", 1800);
        if (result3 == null) {
            System.out.println("Success: No data found as expected.");
        } else {
            System.out.println("Failed: Unexpected data found.");
        }
    }

    // Include your findCO2fromYear method here or make sure it's accessible to this class
    public static CountryYearCO2 findCO2fromYear(String country, int year) {
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        String searchWord = country;
        String searchYear = Integer.toString(year);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",", -1);

                // Check if the line contains the search word
                if (lineData[0].equals(searchWord) && lineData[1].equals(searchYear)) {
                    return new CountryYearCO2(country, year, Double.parseDouble(lineData[15]));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return null;
    }
}
