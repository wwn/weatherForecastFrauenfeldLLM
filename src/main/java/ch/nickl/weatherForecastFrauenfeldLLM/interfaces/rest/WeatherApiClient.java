package ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest;

import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.dto.OpenMeteoWeatherSnapshotResponseDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "weather-api")
public interface WeatherApiClient {

    @GET
    @Path("/forecast")
    OpenMeteoWeatherSnapshotResponseDto getWeather(
            @QueryParam("latitude") double latitude,
            @QueryParam("longitude") double longitude,
            @QueryParam("current") String current
    );
}
