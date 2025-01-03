package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.layout.VBox;

/**
 * The CO2BarChart Class creates a bar chart comparing the CO2 Emissions Per Capita for the Top 10 Most 
 * Populated Countries in the World, and contains a ComboBox that allows the user to select from years 
 * 1985 to 2021 to view the CO2 emissions per capita of that year. 
 * 
 * @author: R. Chan 
 */
public class CO2BarChart extends Application {

    // Declare variables
    private BarChart<String, Number> co2Chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    /**
     * Starts the JavaFX application and sets up the primary stage for the bar chart and ComboBox
     * @param stage The primary stage for this JavaFX bar chart
     */
    @Override
    public void start(Stage stage) {

        VBox layout = createChartWithLayout();

        // Creates a scene to hold the bar chart and ComboCox
        Scene scene = new Scene(layout);

        // Attaches the Scene to the Stage
        stage.setScene(scene);

        // Sets the title of the stage
        stage.setTitle("CO2 Emissions Bar Chart");

        // Makes the Stage (window) visible
        stage.show();
    }

    /**
     * Creates a VBox layout containing a bar chart (CO2 Emissions Per Capita for the Top 10 Most Populated Countries in the World) 
     * and ComboBox for year selection
     * 
     * @return A VBox containing the bar chart and the ComboBox for year selection
     */
    public VBox createChartWithLayout() {

        // Initialize chart
        createContent();

        // Add a ComboBox for year selection
        ComboBox<Integer> yearSelector = new ComboBox<>();
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int year = 1985; year <= 2021; year++) {
            years.add(year);
        }
        yearSelector.setItems(years);
        yearSelector.setPromptText("Select Year");
        yearSelector.setOnAction(e -> updateChart(yearSelector.getValue())); // To update the chart if a new year is selected

        // Create layout and add ComboBox and chart
        VBox layout = new VBox(10, yearSelector, co2Chart);
        layout.setPrefSize(800, 600);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    /**
     * This method creates and configures the chart
     */
    public void createContent(){
        // Define the countries to be displayed on the x-axis
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};
        
        // Configure the x-axis with country names
        xAxis = new CategoryAxis();
        xAxis.setLabel("Countries");
        xAxis.setCategories(FXCollections.observableArrayList(countries));

        // Configure the y-axis with a range and label for CO2 emissions
        yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0, 3.2, 0.1);

        // Initialize the bar chart with the configured axes
        co2Chart = new BarChart<>(xAxis, yAxis);
        co2Chart.setTitle("CO2 Emissions Per Capita for the Top 10 Most Populated Countries in the World");
    }

    /**
     * This method is used to update the chart with data for a selected year
     * @param year The selected year
     */
    public void updateChart(int year){

        // List of countries to display on the chart
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};

        // Clear existing data from the chart
        co2Chart.getData().clear();

        // Create an array list to hold the CO2 data for the selected year
        ArrayList<CountryYearCO2> dataForYear = new ArrayList<>();

        // Find the CO2 data for each country for the selected year
        for (String country : countries) {
            dataForYear.add(CO2DataSearch.findCO2fromYear(country, year));
        }

        // Create a new data series for the specified year
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Year " + year);

        // Add to the data series with the CO2 data for each country
        for (CountryYearCO2 currCountryYearCO2 : dataForYear) {
            series.getData().add(new XYChart.Data<>(currCountryYearCO2.getCountry(), currCountryYearCO2.getCo2PerCapita()));
        }

        // Add the data series to the chart
        co2Chart.getData().add(series);
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}

