package de.goeuro.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author Aram Mkrtchyan.
 */
public final class FileUtils {

    private FileUtils() {
        throw new AssertionError("You have no instance of FileUtils!");
    }

    public static File getCSVFile(String filePath, String name) {
        StringBuilder filePathBuilder = new StringBuilder();
        appendFilePathIfExists(filePath, filePathBuilder);
        generateFileName(name, filePathBuilder);
        return new File(filePathBuilder.toString());
    }

    private static void generateFileName(String name, StringBuilder filePathBuilder) {
        filePathBuilder
                .append(name)
                .append("_")
                .append(System.currentTimeMillis())
                .append(".csv");
    }

    private static void appendFilePathIfExists(String filePath, StringBuilder filePathBuilder) {
        Optional.ofNullable(filePath).ifPresent(filePathBuilder::append);
    }

    public static PrintWriter getFileWriter(File csv) {
        try {
            return new PrintWriter(csv);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Exception while creating FileWriter", e);
        }
    }

}
