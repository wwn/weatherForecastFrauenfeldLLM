package ch.nickl.weatherForecastFrauenfeldLLM.application.usecase;

import ch.nickl.weatherForecastFrauenfeldLLM.domain.event.WeatherSnapshotCreatedEvent;
import ch.nickl.weatherForecastFrauenfeldLLM.domain.model.WeatherSnapshotEntity;
import ch.nickl.weatherForecastFrauenfeldLLM.infrastructure.annotation.UseCase;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.WeatherApiClient;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.dto.OpenMeteoWeatherSnapshotResponseDto;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.mapper.WeatherMapper;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@UseCase
@ApplicationScoped
public class CreateWeatherSnapshotUseCase {


    @Inject
    @RestClient
    WeatherApiClient weatherApiClient;

    @Inject
    WeatherMapper weatherMapper;

    @Inject
    Event<WeatherSnapshotCreatedEvent> eventPublisher;

    @ConfigProperty(name = "weather.api.latitude")
    double latitude;

    @ConfigProperty(name = "weather.api.longitude")
    double longitude;

    @Scheduled(every = "${weather.fetch.interval}")
    public void scheduledFetchWeatherData() {
        invoke();
    }

    // TODO überlegen do I want it?
    public void onStart(@Observes StartupEvent startupEvent) {
        log.info("fetching weather snapshot at startup");
       // invoke();
    }

    @Transactional
    public void invoke() {
        try {
            String currentParams = "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m,dew_point_2m,uv_index,visibility,snow_depth";
            OpenMeteoWeatherSnapshotResponseDto response = weatherApiClient.getWeather(latitude, longitude, currentParams);

            WeatherSnapshotEntity entity = weatherMapper.toEntity(response);
            entity.persist();

            eventPublisher.fire(new WeatherSnapshotCreatedEvent(entity));

            log.info("weather snapshot persisted");

        } catch (Exception e) {
            log.error("could not create weather snapshot: " + e.getMessage());
        }
    }
}
