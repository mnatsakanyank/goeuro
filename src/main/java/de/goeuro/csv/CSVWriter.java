package de.goeuro.csv;


import de.goeuro.utils.FileUtils;

import java.io.Closeable;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriter<T> implements Closeable {

    private PrintWriter fw;

    private BeanToCsvRowProcessor<T> rowProcessor;

    public CSVWriter(File file, BeanToCsvRowProcessor<T> rowProcessor) {
        this.fw = FileUtils.getFileWriter(file);
        this.rowProcessor = rowProcessor;
    }

    public void writeBody(List<T> rowItems) {
        rowItems.stream().forEach(rowItem ->
                        this.fw.write(rowProcessor.process(rowItem))
        );
    }

    public void writeHeader(String headers) {
        this.fw.write(headers);
    }

    @Override
    public void close() {
        try {
            fw.close();
        } catch (Exception ex) {
            throw new RuntimeException("Exception while closing FileWriter", ex);
        }
    }
}
