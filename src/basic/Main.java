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
 * This class launches a Java FX application that contains an intro screen, the bar chart comparing the CO2 Emissions
 * Per Capita for the Top 10 Most Populated Countries in the World, and the line chart comparing the Per Capita 
 * CO2 Emissions Across Continents from 1965 to 2023 on separate clickable tabs
 * 
 * @author: R. Chan
 */
public class Main extends Application{

    // Declare and initialize variable
    private TabPane tabPane = new TabPane();
    
    /**
     * Sets up the primary stage with a TabPane containing tabs for the bar chart and a line chart
     *
     * @param stage the primary stage for this JavaFX application
     */
    @Override
    public void start(Stage stage) {

        // Create Intro Tab
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

    /**
     * Creates a VBox layout containing the intro screen and buttons that lead to the other charts
     * 
     * @return a VBox containing the intro screen and buttons
     */
    public VBox introLayout(){

        // Create spacing to separate Text better
        Text spacing = new Text("...");
        spacing.setStyle("-fx-fill: white;"); 
        Text spacing2 = new Text("...");
        spacing2.setStyle("-fx-fill: white;"); 

        // Create Title
        Text title = new Text("Visualizing Global CO₂ Emissions Per Capita");
        title.setFont(Font.font("Impact", 40)); 
        title.setStyle("-fx-fill: darkgreen;"); 

        // Create Subtitle
        Text subtitle = new Text ("- Insights into Our Carbon Footprint -");
        subtitle.setFont(Font.font("Georgia", 30)); // Specify font name and size
        subtitle.setStyle("-fx-fill: black;"); // Set text colour

        // Create Button title
        Text explore = new Text ("Explore the Data!");
        explore.setFont(Font.font("impact", 30)); // Specify font name and size
        explore.setStyle("-fx-fill: darkgreen;"); // Set text colour

        // Create Graph Description
        Text graphDesc = new Text();
        graphDesc.setText("These graphs provide an overview of CO₂ emissions per capita around the world, highlighting critical environmental issues. Graphing this data is important as it allows you to observe long-term trends, such as the rise or decline in global emissions or the comparison of CO2 levels in countries. These charts can help identify patterns and potential turning points in emission levels over time, as well as give insight into which countries contribute the most to global CO2 emissions. These graphs aim to encourage discussions about sustainability and the steps needed to address climate change.\r\n" + //
                        "");
        graphDesc.setWrappingWidth(350); 
        graphDesc.setFont(Font.font("Georgia", 17));
        graphDesc.setStyle("-fx-fill: black;"); 

        // Display the image
        Image CO2Graphic = new Image("file:\\C:\\Users\\vetra\\github-classroom\\4-0-data-visualization-rachael-solo\\src\\basic\\CO2Graphic.png");
        ImageView imageView = new ImageView(CO2Graphic);

        // Set the width and height of the image
        imageView.setFitWidth(380); 
        imageView.setFitHeight(350); 

        // Create the VBox for the title
        VBox introTitle = new VBox(10, title, subtitle);
        introTitle.setAlignment(Pos.CENTER);

        // Create the VBox for the left bottom side of the window
        VBox graphDescandButtons = new VBox(10, graphDesc, explore, TabWithButton(tabPane));

        // Create the HBox for the bottom parts of the window
        HBox graphDescandImg = new HBox(20, spacing, graphDescandButtons, imageView);

        // Create the VBox combining the VBox and HBox together to form the layout
        VBox introLayout = new VBox(15, spacing2, introTitle, graphDescandImg);

        return introLayout;
    }

    /**
     * Creates a HBox layout for the buttons that allow the user to switch to the charts and data set
     * 
     * @param tabPane TabPane that contains all the tabs 
     * @return A HBox containing the buttons
     */
    public HBox TabWithButton(TabPane tabPane) {

        // Create the buttons to switch to another tab
        Button lineChart = new Button("Go to Line Chart");
        lineChart.setOnAction(e -> tabPane.getSelectionModel().select(1)); // Switch to the second tab (index 1)

        Button barChart = new Button("Go to Bar Chart");
        barChart.setOnAction(e -> tabPane.getSelectionModel().select(2)); // Switch to the third tab (index 2)

        Button dataLink = new Button("Go to Data Set");
        dataLink.setOnAction(event -> {
            try { 
                
                URI uri = new URI("https://github.com/owid/co2-data"); // The URL to open
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);  // Opens the URL in the default browser

            } catch (Exception e) {
            }
        });

        // Arrange the buttons in a horizontal layout
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
