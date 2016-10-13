package de.goeuro.service;

import de.goeuro.client.GoEuroApiClient;
import de.goeuro.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import retrofit2.Call;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    CityCsvService cityCsvService;

    @Mock
    GoEuroApiClient goEuroApiClient;

    @Mock
    Call httpCallMock;

    @InjectMocks
    CityService cityService;

    @Test
    public void saveToCSV_cityExisits() throws Exception {
        City berlin = new City();
        berlin.setId(1L);
        berlin.setName("Berlin");
        berlin.setType("city");

        cityService.saveToCSV("Berlin", null, Optional.of(Arrays.asList(berlin)));

        verify(cityCsvService, times(1)).writeToCSVFile(any(File.class), any(List.class));
    }

    @Test
    public void saveToCSV_noResultForSity() throws Exception {

        cityService.saveToCSV("Berlin", null, Optional.empty());

        verify(cityCsvService, never()).writeToCSVFile(any(File.class), any(List.class));
    }

    @Test
    public void searchByName() throws Exception {
        List<City> list = new ArrayList<>();
        City testCity = new City();
        testCity.setId(1L);
        testCity.setName("Test");
        testCity.setType("city");
        list.add(testCity);

        Mockito.when(goEuroApiClient.searchCityByName("Test")).thenReturn(httpCallMock);
        Mockito.when(httpCallMock.execute()).thenReturn(retrofit2.Response.success(list));

        Optional<List<City>> result = cityService.searchByName("Test");
        assertEquals(list,result.get());
}
}