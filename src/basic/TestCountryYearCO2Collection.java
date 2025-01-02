package basic;

public class TestCountryYearCO2Collection {
    public static void main(String[] args) {
           
            CountryYearCO2Collection collection = new CountryYearCO2Collection();
    
            collection.countryDataSearch("Africa");
    
            for (CountryYearCO2 data : collection.getCountryData()) {
                System.out.println(data);
            }
    }
}
