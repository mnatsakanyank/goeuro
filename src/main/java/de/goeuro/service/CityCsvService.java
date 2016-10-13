package de.goeuro.service;

import de.goeuro.csv.CSVWriter;
import de.goeuro.csv.CityToCsvRowProcessor;
import de.goeuro.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.goeuro.csv.CityToCsvRowProcessor.NEW_LINE_SEPARATOR;
/**
 * @author Aram Mkrtchyan.
 */
public class CityCsvService implements CsvService<City> {

    final static Logger log = LoggerFactory.getLogger(CityCsvService.class);

    private static List<String> columns = Arrays.asList(
            "_id", "name", "type",
            "latitude", "longitude"
    );

    private static final String CITIES_FILE_HEADER = columns.stream().collect(Collectors.joining(";"));

    @Override
    public void writeToCSVFile(File csvFile, List<City> itemsToSave) {
        try (CSVWriter<City> csvWriter = new CSVWriter<>(csvFile, new CityToCsvRowProcessor())) {
            csvWriter.writeHeader(CITIES_FILE_HEADER + NEW_LINE_SEPARATOR);
            csvWriter.writeBody(itemsToSave);
            log.debug("CSV file was created successfully:" + csvFile.getAbsolutePath());
            System.out.println("File was successfully created: " + csvFile.getAbsolutePath());
        } catch (Exception e) {
            log.error("Error while creating csv file", e);
            System.err.println("Error while creating csv file. Check log file for more details");
        }
    }
}
