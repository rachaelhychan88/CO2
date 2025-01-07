package basic;

/**
 * This class is designed to store and provide access to details about CO2 emissions 
 * per capita for a specific country at a specific year.
 * 
 * @author: R. Chan
 */
public class CountryYearCO2 {

    // Declare variables
    private String country;
    private int year;
    private double co2PerCapita;

    /**
     * Constructor - creates a new CountryYearCO2 instance
     * 
     * @param country - the country 
     * @param year - the year
     * @param co2PerCapita - the CO2 per capita for that country in that year
     */
    public CountryYearCO2(String country, int year, double co2PerCapita){
        this.country = country;
        this.year = year;
        this.co2PerCapita = co2PerCapita;
    }

    /**
     * Getter method for the country
     * @return the country
     */
    public String getCountry(){
        return country;
    }

    /**
     * Getter method for the year
     * @return the year
     */
    public int getYear(){
        return year;
    }

    /**
     * Getter method for the CO2 per capita
     * @return the CO2 per capita
     */
    public double getCo2PerCapita(){
        return co2PerCapita;
    }
}
