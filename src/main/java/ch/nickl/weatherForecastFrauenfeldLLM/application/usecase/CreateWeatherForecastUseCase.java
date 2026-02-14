package ch.nickl.weatherForecastFrauenfeldLLM.application.usecase;

import ch.nickl.weatherForecastFrauenfeldLLM.domain.event.WeatherSnapshotCreatedEvent;
import ch.nickl.weatherForecastFrauenfeldLLM.domain.model.WeatherSnapshotEntity;
import ch.nickl.weatherForecastFrauenfeldLLM.infrastructure.WeatherForecastProvider;
import ch.nickl.weatherForecastFrauenfeldLLM.infrastructure.annotation.UseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@ApplicationScoped
public class CreateWeatherForecastUseCase {

    @Inject
    WeatherForecastProvider aiService;

    void onWeatherSnapshotCreated(@Observes WeatherSnapshotCreatedEvent event) {
        log.info("generating new weather forecast...");
        List<WeatherSnapshotEntity> snapshots = WeatherSnapshotEntity.find("order by createdAt desc").page(0, 24).list();
        if (snapshots.isEmpty()) {
            log.warn("no weather data available.");
            return;
        }

        List<String> historyEntries = new ArrayList<>();
        for (int i = snapshots.size() - 1; i >= 0; i--) {
            WeatherSnapshotEntity entity = snapshots.get(i);
            historyEntries.add(formatEntity(entity));
        }

        String history = String.join("\n", historyEntries);
        try {
            log.info("newest forecast:");
            log.info("{}", aiService.createForecast(history));
        } catch (Exception e) {
            log.error("(GitHub Llama Models) computer says no: " + e.getMessage());
        }
    }

    private String formatEntity(WeatherSnapshotEntity entity) {
        return String.format(
                "[%s] Interval: %ds, Temp: %.1f°C (Apparent: %.1f°C, Dew Point: %.1f°C), Humidity: %d%%, Day/Night: %s, Code: %d, Cloud Cover: %d%%, Visibility: %.1fm, UV-Index: %.1f, Precipitation: %.1fmm (Rain: %.1fmm, Showers: %.1fmm, Snow: %.1fmm, Snow Depth: %.1fm), Pressure MSL: %.1fhPa, Surface Pressure: %.1fhPa, Wind: %.1fkm/h (Direction: %d°, Gusts: %.1fkm/h)",
                entity.time,
                entity.interval,
                entity.temperature2m,
                entity.apparentTemperature,
                entity.dewPoint2m,
                entity.relativeHumidity2m,
                entity.isDay == 1 ? "Day" : "Night",
                entity.weatherCode,
                entity.cloudCover,
                entity.visibility,
                entity.uvIndex,
                entity.precipitation,
                entity.rain,
                entity.showers,
                entity.snowfall,
                entity.snowDepth,
                entity.pressureMsl,
                entity.surfacePressure,
                entity.windSpeed10m,
                entity.windDirection10m,
                entity.windGusts10m
        );
    }
}
