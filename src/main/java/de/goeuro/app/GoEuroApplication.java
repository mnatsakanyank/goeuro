package de.goeuro.app;

import de.goeuro.domain.City;
import de.goeuro.service.CityService;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GoEuroApplication {

    public static void main(String[] args) throws IOException {

        OptionParser parser = getParser();
        OptionSet options = parseOptions(parser, args);

        assert options != null;

        if (options.has("help")) {
            parser.printHelpOn(System.out);
            return;
        }

        String city = options.valueOf("city").toString();
        String filePath = options.has("path") ? options.valueOf("path").toString(): null;


        startProcess(city, filePath);
    }

    private static void startProcess(String city, String filePath) {
        CityService service = new CityService();
        Optional<List<City>> rs = service.searchByName(city);
        service.saveToCSV(city, filePath, rs);
    }

    private static OptionParser getParser() {
        OptionParser parser = new OptionParser();
        parser.accepts("city", "City to search").withRequiredArg().required().ofType(String.class);
        parser.accepts("path", "File path to store result").withRequiredArg().ofType(String.class);
        parser.accepts("help", "Print help information");
        return parser;
    }

    private static OptionSet parseOptions(OptionParser parser, String[] args) throws IOException {
        try {
            return parser.parse(args);
        } catch (OptionException oe) {
            parser.printHelpOn(System.out);
            System.exit(0);
            return null;
        }
    }
}
