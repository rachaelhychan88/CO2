package basic;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;

/**
 * This class is designed to store CO2 emissions for a specific country
 * through an array list of CO2DataPoint objects.
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
            countryData = CO2DataSearch.loadData(countryName);
            isDataLoaded = true; // Mark data as loaded
        }
        return countryData;
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

        // Declare and initialize variable
        XYChart.Series<Number, Number> countryChart = new XYChart.Series<>();

        for (CO2DataPoint data : countryDataSearch()) {
            countryChart.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita())); // Add data points to the chart
        }
        countryChart.setName(countryName); // Set the country's name to the chart

        return countryChart;
    }
}
