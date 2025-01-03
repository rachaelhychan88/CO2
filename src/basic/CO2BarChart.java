package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javafx.scene.layout.VBox;

public class CO2BarChart extends Application{

    private BarChart co2Chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

        @Override
    public void start(Stage stage) {
        createContent();

        // Add a ComboBox for year selection
        ComboBox<Integer> yearSelector = new ComboBox<>();
        yearSelector.setItems(FXCollections.observableArrayList(2019, 2020, 2021));
        yearSelector.setPromptText("Select Year");
        yearSelector.setOnAction(e -> updateChart(yearSelector.getValue()));

        VBox layout = new VBox(10, yearSelector);
        layout.setPrefSize(800, 600);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("CO2 Emissions Bar Chart");
        stage.show();
    }

    public void createContent(){
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};
        xAxis = new CategoryAxis();
        xAxis.setLabel("Countries");
        xAxis.setCategories(FXCollections.observableArrayList(countries));
        yAxis = new NumberAxis("CO2 Emissions (in tonnes per person)", 0, 3, 0.1);

        co2Chart = new BarChart<>(xAxis, yAxis);
        co2Chart.setTitle("CO2 Emissions by Country");
    }

    private void updateChart(int year) {
        String[] countries = {"India", "China", "United States", "Indonesia", "Pakistan", "Nigeria", "Brazil", "Bangladesh", "Russia", "Mexico"};
        co2Chart.getData().clear();

        // Fetch data for the selected year
        ArrayList<CountryYearCO2> dataForYear = new ArrayList<>();

        for (String country : countries){
            dataForYear.add(CO2DataSearch.findCO2fromYear(country, year));
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Year " + year);

        for (CountryYearCO2 currCountryYearCO2 : dataForYear) {
            series.getData().add(new XYChart.Data<>(currCountryYearCO2.getCountry(), currCountryYearCO2.getCo2PerCapita()));
        }

        co2Chart.getData().add(series);
    }
}