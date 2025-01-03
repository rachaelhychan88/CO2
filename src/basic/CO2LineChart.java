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
import javafx.stage.Stage;
import java.io.*;
import javafx.event.EventHandler; 


public class CO2LineChart extends Application{
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        VBox layout = createChartWithLayout();
        
        // Create a scene to hold the chart and checkboxes
        Scene scene = new Scene(layout, 600, 600);

        //Attach the Scene to the Stage (the top-level window).
        primaryStage.setScene(scene);

        // Set the title of the Stage (window).
        primaryStage.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");

        // Show the Stage (make the window visible).
        primaryStage.show();
    }

    public VBox createChartWithLayout(){
        // create series for each sorting algorithm type
        XYChart.Series<Number, Number> northAmerica = new XYChart.Series<>();
        XYChart.Series<Number, Number> southAmerica = new XYChart.Series<>();
        XYChart.Series<Number, Number> australia = new XYChart.Series<>();
        XYChart.Series<Number, Number> europe = new XYChart.Series<>();
        XYChart.Series<Number, Number> africa = new XYChart.Series<>();
        XYChart.Series<Number, Number> asia = new XYChart.Series<>();

        // Set the name of the data series, which will appear in the chart legend.
        northAmerica.setName("North America");
        southAmerica.setName("South America");
        australia.setName("Australia");
        europe.setName("Europe");
        africa.setName("Africa");
        asia.setName("Asia");

        CountryYearCO2Collection northAmericaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection southAmericaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection australiaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection europeCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection africaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection asiaCollection = new CountryYearCO2Collection();

        northAmericaCollection.countryDataSearch("North America");
        southAmericaCollection.countryDataSearch("South America");
        australiaCollection.countryDataSearch("Australia");
        europeCollection.countryDataSearch("Europe");
        africaCollection.countryDataSearch("Africa");
        asiaCollection.countryDataSearch("Asia");

        for(int index = 0; index < northAmericaCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = northAmericaCollection.getCountryData().get(index);
            northAmerica.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        for(int index = 0; index < southAmericaCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = southAmericaCollection.getCountryData().get(index);
            southAmerica.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        for(int index = 0; index < australiaCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = australiaCollection.getCountryData().get(index);
            australia.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        for(int index = 0; index < asiaCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = asiaCollection.getCountryData().get(index);
            asia.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        for(int index = 0; index < africaCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = africaCollection.getCountryData().get(index);
            africa.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }
        for(int index = 0; index < europeCollection.getCountryData().size(); index ++){
            CountryYearCO2 data = europeCollection.getCountryData().get(index);
            europe.getData().add(new XYChart.Data<>(data.getYear(), data.getCo2PerCapita()));
        }

      
        // Create a NumberAxis for the x-axis (horizontal axis).
        NumberAxis xAxis = new NumberAxis("Year", 1965, 2023, 5);

        // Create a NumberAxis for the y-axis (vertical axis).
        NumberAxis yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0,3, 0.1);

        // Create a LineChart using the x-axis and y-axis.
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Set the title of the chart.
        lineChart.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");

        // create checkboxes for the sorting algorithms
        CheckBox northAmericaCheckBox = new CheckBox("North America"); 
        CheckBox southAmericaCheckBox = new CheckBox("South America"); 
        CheckBox australiaCheckBox = new CheckBox("Australia"); 
        CheckBox africaCheckBox = new CheckBox("Africa"); 
        CheckBox asiaCheckBox = new CheckBox("Asia"); 
        CheckBox europeCheckBox = new CheckBox("Europe"); 

        EventHandler<ActionEvent> northAmericaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (northAmericaCheckBox.isSelected()) 
                    lineChart.getData().add(northAmerica);
                else
                    lineChart.getData().remove(northAmerica);
            } 

        };

        EventHandler<ActionEvent> southAmericaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (southAmericaCheckBox.isSelected()) 
                    lineChart.getData().add(southAmerica);
                else
                    lineChart.getData().remove(southAmerica);
            } 

        };

        EventHandler<ActionEvent> australiaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (australiaCheckBox.isSelected()) 
                    lineChart.getData().add(australia);
                else
                    lineChart.getData().remove(australia);
            } 

        };

        EventHandler<ActionEvent> africaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (africaCheckBox.isSelected()) 
                    lineChart.getData().add(africa);
                else
                    lineChart.getData().remove(africa);
            } 

        };

        EventHandler<ActionEvent> asiaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (asiaCheckBox.isSelected()) 
                    lineChart.getData().add(asia);
                else
                    lineChart.getData().remove(asia);
            } 

        };

        EventHandler<ActionEvent> europeEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                if (europeCheckBox.isSelected()) 
                    lineChart.getData().add(europe);
                else
                    lineChart.getData().remove(europe);
            } 

        };
        
        northAmericaCheckBox.setOnAction(northAmericaEvent);
        southAmericaCheckBox.setOnAction(southAmericaEvent);
        asiaCheckBox.setOnAction(asiaEvent);
        africaCheckBox.setOnAction(africaEvent);
        europeCheckBox.setOnAction(europeEvent);
        australiaCheckBox.setOnAction(australiaEvent);

        VBox layout = new VBox(10, lineChart, northAmericaCheckBox, southAmericaCheckBox, asiaCheckBox, europeCheckBox, africaCheckBox, australiaCheckBox);
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
