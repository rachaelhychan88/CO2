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

    /**
     * Creates a VBox layout containing a line chart (Per Capita CO2 Emissions Across Continents (1965–2023)) 
     * and checkboxes for selecting the continents' data series.
     * 
     * @return A VBox containing the line chart and associated checkboxes
     */
    public VBox createChartWithLayout(){

        String[] continents = {"North America", "South America", "Australia", "Europe", "Africa", "Asia"};
        String[] camelCaseContinent = new String[continents.length];

        for(int index = 0; index < continents.length; index ++){
            camelCaseContinent[index] = toCamelCase(continents[index]);
        }

        for(int index = 0; index < continents.length; index ++){
            CountryYearCO2Collection camelCaseContinent[index] = new CountryYearCO2Collection();
        }

        // Creates instances of the CountryYearCO2Collection class for each of the continents
        CountryYearCO2Collection northAmericaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection southAmericaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection australiaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection europeCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection africaCollection = new CountryYearCO2Collection();
        CountryYearCO2Collection asiaCollection = new CountryYearCO2Collection();

        // Searches the CO2Data.csv file for relevant information to add to the instance for each continent
        northAmericaCollection.countryDataSearch("North America");
        southAmericaCollection.countryDataSearch("South America");
        australiaCollection.countryDataSearch("Australia");
        europeCollection.countryDataSearch("Europe");
        africaCollection.countryDataSearch("Africa");
        asiaCollection.countryDataSearch("Asia");

        // Creates a series for each continent
        XYChart.Series<Number, Number> northAmerica = new XYChart.Series<>();
        XYChart.Series<Number, Number> southAmerica = new XYChart.Series<>();
        XYChart.Series<Number, Number> australia = new XYChart.Series<>();
        XYChart.Series<Number, Number> europe = new XYChart.Series<>();
        XYChart.Series<Number, Number> africa = new XYChart.Series<>();
        XYChart.Series<Number, Number> asia = new XYChart.Series<>();

        // Sets the name of the data series that will appear in the chart legend.
        northAmerica.setName("North America");
        southAmerica.setName("South America");
        australia.setName("Australia");
        europe.setName("Europe");
        africa.setName("Africa");
        asia.setName("Asia");

        // For each of the continent's CountryYearCO2Collection, loop through them and create data points
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

      
        // Creates a NumberAxis for the x-axis (year)
        NumberAxis xAxis = new NumberAxis("Year", 1965, 2023, 5);

        // Creates a NumberAxis for the y-axis (CO2 Emissions)
        NumberAxis yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0,3, 0.1);

        // Creates a LineChart using the x-axis and y-axis
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Sets the title of the chart
        lineChart.setTitle("Per Capita CO2 Emissions Across Continents (1965–2023)");

        // Creates checkboxes for each of the continents
        CheckBox northAmericaCheckBox = new CheckBox("North America"); 
        CheckBox southAmericaCheckBox = new CheckBox("South America"); 
        CheckBox australiaCheckBox = new CheckBox("Australia"); 
        CheckBox africaCheckBox = new CheckBox("Africa"); 
        CheckBox asiaCheckBox = new CheckBox("Asia"); 
        CheckBox europeCheckBox = new CheckBox("Europe"); 

        EventHandler<ActionEvent> northAmericaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove North America data series based on checkbox state
                if (northAmericaCheckBox.isSelected()) 
                    lineChart.getData().add(northAmerica);
                else
                    lineChart.getData().remove(northAmerica);
            } 

        };

        EventHandler<ActionEvent> southAmericaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove South America data series based on checkbox state
                if (southAmericaCheckBox.isSelected()) 
                    lineChart.getData().add(southAmerica);
                else
                    lineChart.getData().remove(southAmerica);
            } 

        };

        EventHandler<ActionEvent> australiaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove Australia data series based on checkbox state
                if (australiaCheckBox.isSelected()) 
                    lineChart.getData().add(australia);
                else
                    lineChart.getData().remove(australia);
            } 

        };

        EventHandler<ActionEvent> africaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove Africa data series based on checkbox state
                if (africaCheckBox.isSelected()) 
                    lineChart.getData().add(africa);
                else
                    lineChart.getData().remove(africa);
            } 

        };

        EventHandler<ActionEvent> asiaEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove Asia data series based on checkbox state
                if (asiaCheckBox.isSelected()) 
                    lineChart.getData().add(asia);
                else
                    lineChart.getData().remove(asia);
            } 

        };

        EventHandler<ActionEvent> europeEvent = new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
                // Add or remove Europe data series based on checkbox state
                if (europeCheckBox.isSelected()) 
                    lineChart.getData().add(europe);
                else
                    lineChart.getData().remove(europe);
            } 

        };
        
        // Attaches event handlers to their respective checkboxes
        northAmericaCheckBox.setOnAction(northAmericaEvent);
        southAmericaCheckBox.setOnAction(southAmericaEvent);
        asiaCheckBox.setOnAction(asiaEvent);
        africaCheckBox.setOnAction(africaEvent);
        europeCheckBox.setOnAction(europeEvent);
        australiaCheckBox.setOnAction(australiaEvent);

        // To make the checkboxes horizontal
        HBox checkboxLayout = new HBox(15, northAmericaCheckBox, southAmericaCheckBox, asiaCheckBox, europeCheckBox, africaCheckBox, australiaCheckBox);
        checkboxLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15, lineChart, checkboxLayout);
        layout.setAlignment(Pos.CENTER); // Align elements to center

        return layout;
    }

    public static String toCamelCase(String input) {
        // Split the string into words using spaces
        String[] words = input.split(" ");

        // Start with the first word in lowercase
        String camelCaseString = words[0].toLowerCase();

        // Process the rest of the words
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()){
                // Capitalize the first letter and add the rest of the word in lowercase
                camelCaseString = camelCaseString + word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
        }

        return camelCaseString;
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
