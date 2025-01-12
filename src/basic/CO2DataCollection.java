package basic;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.chart.XYChart;

/**
 * This class contains methods that extract and organize the data from the CO2Data.csv file containing CO2 emissions for a specific country
 * into an array list of CO2DataPoint objects. 
 * 
 * @author: R. Chan
 */
public class CO2DataCollection {

    // Declare variables
    private String countryName;
    private ArrayList<CO2DataPoint> countryData;
    private boolean isDataLoaded = false;  // To track if the data is already loaded

    /**
     * Constructor - creates a new CO2DataCollection instance
     * 
     * @param countryName - the country of interest
     */
    public CO2DataCollection(String countryName) {
        this.countryName = countryName;
        this.countryData = new ArrayList<>();
    }

    /**
     * This method makes the CO2DataCollection contain CO2DataPoint objects for each 
     * relevant line of the CO2Data.csv file
     */
    public ArrayList<CO2DataPoint> countryDataSearch() {
        if (!isDataLoaded) { // Check if the data is already loaded
            loadData();
            isDataLoaded = true; // Mark data as loaded
        }
        return countryData;
    }

    /**
     * This method loads the data from the CO2Data.csv file and populates the countryData array list
     */
    private void loadData() {
        int[] countryStartOccurrence = CO2DataSearch.searchYearCo2PerCap(countryName);
        String path = "C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Data.csv";
        int countryLineCounter = 0;
        String line;
        double co2PerCapita;
        String[] lineData;
        String strCo2Value;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int index = 1; index < countryStartOccurrence[0]; index++) {
                br.readLine(); // Skip lines until the start of the country's data
            }

            // Read the file line by line until it is no longer the country
            while ((line = br.readLine()) != null && countryLineCounter < countryStartOccurrence[1]) {
                lineData = line.split(",", -1); // Splits the line by the comma, making sure to count the empty spaces as elements
                strCo2Value = lineData[15];

                if (!strCo2Value.isEmpty()) {
                    co2PerCapita = Double.parseDouble(strCo2Value); // Parse the string to a double
                    CO2DataPoint currLineYearCO2 = new CO2DataPoint(Integer.parseInt(lineData[1]), co2PerCapita);
                    countryData.add(currLineYearCO2);
                }

                countryLineCounter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Getter method for the country name
     * 
     * @return the country's name
     */
    public String getName() {
        return countryName;
    }

    /**
     * Getter method for the line chart of a country
     * 
     * @return the country's line chart
     */
    public XYChart.Series<Number, Number> getChart() {
        XYChart.Series<Number, Number> countryChart = new XYChart.Series<>();
        for (CO2DataPoint data : countryDataSearch()) {
            countryChart.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita())); // Add data points to the chart
        }
        countryChart.setName(countryName); // Set the country's name to the chart

        return countryChart;
    }
}
