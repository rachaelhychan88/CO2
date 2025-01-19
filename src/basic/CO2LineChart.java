package basic;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler; 

/**
 * The CO2LineChart class creates a line chart comparing the Per Capita CO2 Emissions Across Continents 
 * from 1965 to 2023, and contains checkboxes that allow the data for different continents to be viewed.
 * 
 * @author: R. Chan 
 */
public class CO2LineChart {

    private String[] continents = {"North America", "South America", "Australia", "Europe", "Africa", "Asia", "World"}; // Array containing the continents that will be graphed

    /**
     * Creates a VBox layout containing a line chart (Per Capita CO2 Emissions Across Continents (1965–2023)) 
     * and checkboxes for selecting the continents' data series.
     * 
     * @return A VBox containing the line chart and associated checkboxes
     */
    public VBox createChartWithLayout() {
      
        // Creates a NumberAxis for the x-axis (year)
        NumberAxis xAxis = new NumberAxis("Year", 1965, 2023, 5);
    
        // Creates a NumberAxis for the y-axis (CO2 Emissions)
        NumberAxis yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0, 3, 0.1);
    
        // Creates a LineChart using the x-axis and y-axis
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Map to store series for each continent
        Map<String, XYChart.Series<Number, Number>> continentSeriesMap = new HashMap<>();

        HBox checkboxLayout = new HBox(15);
        checkboxLayout.setAlignment(Pos.CENTER);
    
        // Loop through each continent and set up checkboxes
        for (int index = 0; index < continents.length; index++) {
            CO2DataCollection countryChart = new CO2DataCollection(continents[index]);
            XYChart.Series<Number, Number> series = countryChart.getChart();  // Get the chart series for the continent
            
            // Store the series in the map so you can reference it later
            continentSeriesMap.put(continents[index], series);

            // Create a checkbox for the continent
            CheckBox countryCheckBox = new CheckBox(continents[index]);
            EventHandler<ActionEvent> countryEvent = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) { 
                    if (countryCheckBox.isSelected()) {
                        // Add the series if the checkbox is selected
                        lineChart.getData().add(series);
                    } else {
                        // Remove the series if the checkbox is not selected
                        lineChart.getData().remove(series);
                    }
                } 
            };

            // Attach the event handler to the checkbox
            countryCheckBox.setOnAction(countryEvent);

            // Add the checkbox to the layout
            checkboxLayout.getChildren().add(countryCheckBox);
        }
        
        // Set the title of the chart
        lineChart.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");

        // Create a VBox to hold the chart and checkboxes
        VBox layout = new VBox(15, lineChart, checkboxLayout);
        layout.setAlignment(Pos.CENTER); // Aligns elements to the center
    
        return layout;
    }
}
