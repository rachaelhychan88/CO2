package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private BarChart co2Chart;
    
    @Override
    public void start(Stage stage) {
        // Create a TabPane to hold the tabs
        TabPane tabPane = new TabPane();

        // Create CO2 Bar Chart Tab
        CO2BarChart chart = new CO2BarChart();
        VBox chartLayout = chart.createChartWithLayout();
        Tab barChartTab = new Tab("CO2 Bar Chart");
        barChartTab.setContent(chartLayout); // Set the content of the BarChart Tab

        // Create CO2 Line Chart Tab
        Tab lineChartTab = new Tab("CO2 Line Chart");
        lineChartTab.setClosable(false);
        VBox lineChartLayout = new VBox();
        CO2LineChart lineChart = new CO2LineChart(); 
        lineChartTab.setContent(lineChartLayout); 

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(barChartTab, lineChartTab);

        // Create a scene and set the layout
        Scene scene = new Scene(tabPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("CO2 Emissions Charts");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
