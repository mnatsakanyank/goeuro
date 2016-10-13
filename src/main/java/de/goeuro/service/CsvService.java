package de.goeuro.service;

import java.io.File;
import java.util.List;

public interface CsvService<T> {

    void writeToCSVFile(File csvFile, List<T> itemsToSave);

}

