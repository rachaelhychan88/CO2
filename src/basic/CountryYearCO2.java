package basic;

/**
 * This class is designed to store and provide access to details about CO2 emissions 
 * per capita for a specific country at a specific year.
 * 
 * @author: R. Chan
 */
public class CountryYearCO2 extends CO2DataPoint{

    // Declare variables
    private String country;

    /**
     * Constructor - creates a new CountryYearCO2 instance
     * 
     * @param country - the country 
     * @param year - the year
     * @param co2PerCapita - the CO2 per capita for that country in that year
     */
    public CountryYearCO2(String country, int year, double co2PerCapita){
        super(year, co2PerCapita);
        this.country = country;
    }

    /**
     * Getter method for the country
     * 
     * @return the country
     */
    public String getCountry(){
        return country;
    }

}
