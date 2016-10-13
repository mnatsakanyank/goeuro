package de.goeuro.client;

import de.goeuro.domain.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GoEuroApiClient {

    @GET("position/suggest/en/{city}")
    Call<List<City>> searchCityByName(@Path("city") String city);

}
