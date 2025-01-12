package basic;

import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import javafx.event.EventHandler; 

public class CO2DataCollection {
    private String countryName;
    private ArrayList<CO2DataPoint> countryData;
    private boolean isDataLoaded = false;  // Flag to track if the data is already loaded

    public CO2DataCollection(String countryName) {
        this.countryName = countryName;
        this.countryData = new ArrayList<>();
    }

    /**
     * This method makes the CountryYearCO2Collection contain CountryYearCO2 Objects for each 
     * relevant line of the CO2Data.csv file
     */
    public ArrayList<CO2DataPoint> countryDataSearch(){
        if (!isDataLoaded) { // Check if the data is already loaded
            loadData();
            isDataLoaded = true;  // Mark data as loaded
        }
        return countryData;
    }

    /**
     * Load the data from the file and populate the countryData list.
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
                br.readLine();  // Skip lines until the start of the country's data
            }

            while ((line = br.readLine()) != null && countryLineCounter < countryStartOccurrence[1]) {
                lineData = line.split(",", -1); 
                strCo2Value = lineData[15];

                if (!strCo2Value.isEmpty()) {
                    co2PerCapita = Double.parseDouble(strCo2Value);
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

    public String getName() {
        return countryName;
    }

    public XYChart.Series<Number, Number> getChart() {
        XYChart.Series<Number, Number> countryChart = new XYChart.Series<>();
        for (CO2DataPoint data : countryDataSearch()) {
            countryChart.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        countryChart.setName(countryName);

        return countryChart;
    }
}
