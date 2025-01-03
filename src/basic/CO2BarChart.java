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

public class CO2BarChart extends Application {

    private BarChart<String, Number> co2Chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    /**
     * Starts the JavaFX application and sets up the primary stage for the bar chart and ComboBox
     * @param stage The primary stage for this JavaFX bar chart
     */
    @Override
    public void start(Stage stage) {
        // Create the chart and layout
        VBox layout = createChartWithLayout();

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("CO2 Emissions Bar Chart");
        stage.show();
    }

    /**
     * Creates a VBox layout containing a bar chart (CO2 Emissions Per Capita for the Top 10 Most Populated Countries) 
     * and ComboBox for year selection
     * 
     * @return A VBox containing the bar chart and the ComboBox for year selection
     */
    public VBox createChartWithLayout() {
        // Initialize chart and axis
        createContent();

        // Add a ComboBox for year selection
        ComboBox<Integer> yearSelector = new ComboBox<>();
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int year = 1985; year <= 2021; year++) {
            years.add(year);
        }

        yearSelector.setItems(years);
        yearSelector.setPromptText("Select Year");
        yearSelector.setOnAction(e -> updateChart(yearSelector.getValue()));

        // Create layout and add ComboBox and chart
        VBox layout = new VBox(10, yearSelector, co2Chart);
        layout.setPrefSize(800, 600);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    /**
     * This method creates and configures the chart
     */
    public void createContent() {
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};
        xAxis = new CategoryAxis();
        xAxis.setLabel("Countries");
        xAxis.setCategories(FXCollections.observableArrayList(countries));
        yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0, 3, 0.1);

        co2Chart = new BarChart<>(xAxis, yAxis);
        co2Chart.setTitle("CO2 Emissions Per Capita for the Top 10 Most Populated Countries");
    }

    /**
     * This method is used to update the chart with data for a selected year
     * @param year the selected year
     */
    public void updateChart(int year) {
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};
        co2Chart.getData().clear();

        // Fetch data for the selected year
        ArrayList<CountryYearCO2> dataForYear = new ArrayList<>();

        for (String country : countries) {
            dataForYear.add(CO2DataSearch.findCO2fromYear(country, year));
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Year " + year);

        for (CountryYearCO2 currCountryYearCO2 : dataForYear) {
            series.getData().add(new XYChart.Data<>(currCountryYearCO2.getCountry(), currCountryYearCO2.getCo2PerCapita()));
        }

        co2Chart.getData().add(series);
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}

