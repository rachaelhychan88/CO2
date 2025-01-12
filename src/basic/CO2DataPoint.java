package basic;

/**
 * This class is designed to store details about CO2 emissions 
 * per capita regarding the CO2 emissions per capita at a certain year.
 * 
 * @author: R. Chan
 */
public class CO2DataPoint {

    // Declare variables
    private int year;
    private double co2PerCapita;

    /**
     * Constructor - creates a new CO2DataPoint instance
     * 
     * @param year - the year
     * @param co2PerCapita - the CO2 per capita for that country in that year
     */
    public CO2DataPoint(int year, double co2PerCapita){
        this.year = year;
        this.co2PerCapita = co2PerCapita;
    }

    /**
     * Getter method for the year
     * 
     * @return the year
     */
    public int getYear(){
        return year;
    }

    /**
     * Getter method for the CO2 per capita
     * 
     * @return the CO2 per capita
     */
    public double getCo2PerCapita(){
        return co2PerCapita;
    }
    
}
