### Description of Dataset ###
The dataset contains continental and country-specific records of CO₂ emissions from fossil fuels, deforestation, and other sources. It includes key metrics such as per capita emissions, total emissions, and GDP per CO₂ emissions, spanning several decades. 

### Why This Dataset was Selected ###
This dataset was selected because it provides a comprehensive view of CO₂ emissions, making it an ideal choice for creating charts that highlight critical environmental issues. Graphing this data is extremely meaningful as it allows users to observe long-term trends, such as the rise or decline in global emissions or the comparison of CO2 levels in countries. These charts can help identify patterns and potential turning points in emission levels over time, as well as give insight into which countries contribute the most to global CO2 emissions. This encourages discussions about sustainability and the steps needed to address climate change. 

### Link to Dataset ###
[Data on CO2 and Greenhouse Gas Emissions by Our World in Data](https://github.com/owid/co2-data)

### Link to Demo Video ###
[DEMO VIDEO](https://youtu.be/tQYF4QlLeFU)

### UML Class Diagram and Object Oriented Approach ###
![alt text](<basic/CPTUMLDiagram.drawio (1).png>)

This application utilizes an object-oriented approach, demonstrating the design principle of separation of concerns by assigning each class a single, well-defined responsibility. For example, the CO2DataPoint class is dedicated solely to storing data points, such as the year and CO₂ per capita, while the CountryYearCO2 class extends it to include country-specific information. To manage collections of data, the CO2DataCollection class aggregates multiple CO2DataPoint objects, organizing them by country. Creating charts is handled by the CO2LineChart and CO2BarChart classes, which transform these data points into their respective chart formats for display. Data retrieval is managed by the CO2DataSearch class, which focuses on searching and processing data from the CSV file. Encapsulation is applied with all fields, as year and co2PerCapita are kept private and accessible only through controlled methods like getYear or getCo2PerCapita. This prevents direct modification of variables from other classes. Together, the use of these principles ensure the application is organized and demonstrates an object-oriented approach. 