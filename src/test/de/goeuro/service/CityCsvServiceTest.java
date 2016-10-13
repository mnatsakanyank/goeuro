package de.goeuro.service;

import de.goeuro.domain.City;
import de.goeuro.domain.GeoData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CityCsvServiceTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @InjectMocks
    CityCsvService cityCsvService;

    @Test
    public void testWriteToCSVFile() throws Exception {
        File file= folder.newFile("Berlin.csv");

        List<City> list = getCities();

        cityCsvService.writeToCSVFile(file,list);
        assertTrue(file.length() > 0);
        String content = new String(Files.readAllBytes(file.toPath()));
        assertEquals("_id;name;type;latitude;longitude\n1;Test;city;55.5555;66.6666\n",content);
    }

    private List<City> getCities() {
        List<City> list = new ArrayList<>();
        City testCity = new City();
        testCity.setId(1L);
        testCity.setName("Test");
        testCity.setType("city");
        GeoData geoData = new GeoData();
        geoData.setLatitude(55.5555);
        geoData.setLongitude(66.6666);
        testCity.setGeoData(geoData);
        list.add(testCity);
        return list;
    }
}