package de.goeuro.csv;


import de.goeuro.domain.City;

public class CityToCsvRowProcessor implements BeanToCsvRowProcessor<City> {

    private static final String COMMA_DELIMITER = ";";
    public static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public String process(City city) {
        StringBuilder builder = new StringBuilder(50);
        builder.append(city.getId().toString()).append(COMMA_DELIMITER);
        builder.append(city.getName()).append(COMMA_DELIMITER);
        builder.append(city.getType()).append(COMMA_DELIMITER);
        builder.append(city.getGeoData().getLatitude().toString()).append(COMMA_DELIMITER);
        builder.append(city.getGeoData().getLongitude().toString()).append(NEW_LINE_SEPARATOR);
        return builder.toString();
    }
}
