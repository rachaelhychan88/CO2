package basic;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Desktop;
import java.net.URI;

/**
 * This class launches a Java FX application that contains the bar chart comparing the CO2 Emissions
 * Per Capita for the Top 10 Most Populated Countries in the World and the line chart comparing the Per Capita 
 * CO2 Emissions Across Continents from 1965 to 2023 on separate clickable tabs
 * 
 * @author: R. Chan
 */
public class Main extends Application{

    TabPane tabPane = new TabPane();
    
    /**
     * Sets up the primary stage with a TabPane containing tabs for the bar chart and a line chart
     *
     * @param stage the primary stage for this JavaFX application
     */
    @Override
    public void start(Stage stage) {

        // Create CO2 Bar Chart Tab
        VBox introLayout = introLayout();
        Tab introTab = new Tab("Intro");
        introTab.setClosable(false);
        introTab.setContent(introLayout);

        // Create CO2 Bar Chart Tab
        CO2BarChart barChart = new CO2BarChart();
        VBox barChartLayout = barChart.createChartWithLayout();
        Tab barChartTab = new Tab("CO2 Bar Chart");
        barChartTab.setClosable(false);
        barChartTab.setContent(barChartLayout);

        // Create CO2 Line Chart Tab
        Tab lineChartTab = new Tab("CO2 Line Chart");
        lineChartTab.setClosable(false);
        CO2LineChart lineChart = new CO2LineChart(); 
        VBox lineChartLayout = lineChart.createChartWithLayout();
        lineChartTab.setContent(lineChartLayout); 


        // Add tabs to the TabPane
        tabPane.getTabs().addAll(introTab, lineChartTab, barChartTab);

        // Create a scene and set the layout
        Scene scene = new Scene(tabPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("CO2 Emissions Charts");
        stage.show();
    }

    public VBox introLayout(){
        Text spacing = new Text("...");
        spacing.setStyle("-fx-fill: white;");

        Text spacing2 = new Text("...");
        spacing2.setStyle("-fx-fill: white;");

        Text title = new Text("Visualizing Global CO₂ Emissions Per Capita");
        title.setFont(Font.font("Impact", 40)); // Specify font name and size
        title.setStyle("-fx-fill: darkgreen;"); // Set text color

        Text subtitle = new Text ("- Insights into Our Carbon Footprint -");
        subtitle.setFont(Font.font("Georgia", 30)); // Specify font name and size
        subtitle.setStyle("-fx-fill: black;"); // Set text color

        Text explore = new Text ("Explore the Data!");
        explore.setFont(Font.font("impact", 30)); // Specify font name and size
        explore.setStyle("-fx-fill: darkgreen;"); // Set text color

        Text graphDesc = new Text();
        graphDesc.setText("These graphs provide an overview of CO₂ emissions per capita around the world, highlighting critical environmental issues. Graphing this data is important as it allows you to observe long-term trends, such as the rise or decline in global emissions or the comparison of CO2 levels in countries. These charts can help identify patterns and potential turning points in emission levels over time, as well as give insight into which countries contribute the most to global CO2 emissions. These graphs aim to encourage discussions about sustainability and the steps needed to address climate change.\r\n" + //
                        "");
        graphDesc.setWrappingWidth(350); // Enable word wrapping
        graphDesc.setFont(Font.font("Georgia", 17)); // Specify font name and size
        graphDesc.setStyle("-fx-fill: black;"); // Set text color

        Image CO2Graphic = new Image("file:\\C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Graphic.png");

        // Create an ImageView object to display the image
        ImageView imageView = new ImageView(CO2Graphic);

        // set the width and height of the image
        imageView.setFitWidth(380);  // Set the width
        imageView.setFitHeight(350);  // Set the height

        VBox introTitle = new VBox(10, title, subtitle);
        introTitle.setAlignment(Pos.CENTER);
        VBox graphDescandButtons = new VBox(10, graphDesc, explore, TabWithButton(tabPane));
        HBox graphDescandImg = new HBox(20, spacing, graphDescandButtons, imageView);
        VBox introLayout = new VBox(15, spacing2, introTitle, graphDescandImg);
        return introLayout;
    }

    public HBox TabWithButton(TabPane tabPane) {
        // Create the button to switch to another tab
        Button lineChart = new Button("Go to Line Chart");
        lineChart.setOnAction(e -> tabPane.getSelectionModel().select(1)); // Switch to the first tab (index 0)

        Button barChart = new Button("Go to Bar Chart");
        barChart.setOnAction(e -> tabPane.getSelectionModel().select(2)); // Switch to the second tab (index 1)

        Button dataLink = new Button("Go to Data Set");
        dataLink.setOnAction(event -> {
            try {
                // The URL to open
                URI uri = new URI("https://github.com/owid/co2-data");
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);  // Opens the URL in the default browser
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Arrange the button in a layout
        HBox layout = new HBox(10, lineChart, barChart, dataLink);

        return layout;
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
