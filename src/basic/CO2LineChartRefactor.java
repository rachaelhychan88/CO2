package basic;

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

/**
 * The CO2LineChart Class creates a line chart comparing the Per Capita CO2 Emissions Across Continents 
 * from 1965 to 2023, and contains checkboxes that allow the data for different continents to be viewed.
 * 
 * @author: R. Chan 
 */
public class CO2LineChartRefactor extends Application{

    /**
     * Starts the JavaFX application and sets up the primary stage for the line chart and checkboxes
     * 
     * @param primaryStage The primary stage for this JavaFX line chart
     * @throws FileNotFoundException If required data files are not found during the application setup
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{

        VBox layout = createChartWithLayout(); 

        // Creates a scene to hold the line chart and checkboxes
        Scene scene = new Scene(layout, 600, 600);

        //Attaches the Scene to the Stage
        primaryStage.setScene(scene);

        // Sets the title of the Stage
        primaryStage.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");

        // Makes the Stage (window) visible
        primaryStage.show();
    }

    public VBox createChartWithLayout() {

        String[] continents = {"North America", "South America", "Australia", "Europe", "Africa", "Asia"};
      
        // Creates a NumberAxis for the x-axis (year)
        NumberAxis xAxis = new NumberAxis("Year", 1965, 2023, 5);
    
        // Creates a NumberAxis for the y-axis (CO2 Emissions)
        NumberAxis yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0,3, 0.1);
    
        // Creates a LineChart using the x-axis and y-axis
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    
        for (int index = 0; index < continents.length; index++) {
            CO2DataCollection countryChart = new CO2DataCollection(continents[index]);
            lineChart.getData().add(countryChart.getChart()); // Only get the chart once
        }
    
        // Sets the title of the chart
        lineChart.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");
    
        VBox layout = new VBox(15, lineChart);
        layout.setAlignment(Pos.CENTER); // Align elements to center
    
        return layout;
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
