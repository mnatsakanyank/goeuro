package de.goeuro.app;

import de.goeuro.client.GoEuroApiClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GoEuroConfig {

    public static GoEuroApiClient getGoEuroApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.goeuro.com/api/v2/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(GoEuroApiClient.class);
    }

}
