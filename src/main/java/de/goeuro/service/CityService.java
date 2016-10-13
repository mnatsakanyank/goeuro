package de.goeuro.service;

import de.goeuro.app.GoEuroConfig;
import de.goeuro.client.GoEuroApiClient;
import de.goeuro.domain.City;
import de.goeuro.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CityService {

    final static Logger logger = LoggerFactory.getLogger(CityService.class);

    private GoEuroApiClient apiClient = GoEuroConfig.getGoEuroApiClient();

    private CityCsvService csvService = new CityCsvService();

    public Optional<List<City>> searchByName(String cityName) {
        Optional<List<City>> rs = Optional.empty();
        try {
            rs = Optional.of(apiClient.searchCityByName(cityName).execute().body());
        } catch (Exception e) {
            logger.error("Exception while retrieving data from api", e);
            System.err.println("Exception while retrieving data from api. Check log file for more details");
        }
        return rs;
    }

    public void saveToCSV(String name, String filePath, Optional<List<City>> rs) {
        if (!rs.orElseGet(Collections::emptyList).isEmpty()) {
            csvService.writeToCSVFile(FileUtils.getCSVFile(filePath, name), rs.get());
        } else {
            System.out.println("No result for: " + name);
        }
    }

}
