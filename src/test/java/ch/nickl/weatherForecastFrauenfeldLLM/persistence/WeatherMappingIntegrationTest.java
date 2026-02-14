package ch.nickl.weatherForecastFrauenfeldLLM.persistence;

import ch.nickl.weatherForecastFrauenfeldLLM.domain.model.WeatherSnapshotEntity;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.dto.OpenMeteoWeatherSnapshotResponseDto;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.mapper.WeatherMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class WeatherMappingIntegrationTest {

    @Inject
    WeatherMapper weatherMapper;

    @Test
    @Transactional
    public void testMapAndPersist() {
        OpenMeteoWeatherSnapshotResponseDto dto = new OpenMeteoWeatherSnapshotResponseDto();
        dto.latitude = 47.5584;
        dto.longitude = 8.8968;

        dto.current = new OpenMeteoWeatherSnapshotResponseDto.Current();
        dto.current.time = "2024-05-20T12:00";
        dto.current.interval = 900;
        dto.current.temperature_2m = 22.5;
        dto.current.relative_humidity_2m = 60;
        dto.current.apparent_temperature = 21.8;
        dto.current.is_day = 1;
        dto.current.precipitation = 0.0;
        dto.current.rain = 0.0;
        dto.current.showers = 0.0;
        dto.current.snowfall = 0.0;
        dto.current.weather_code = 1;
        dto.current.cloud_cover = 20;
        dto.current.pressure_msl = 1013.2;
        dto.current.surface_pressure = 1010.5;
        dto.current.wind_speed_10m = 12.5;
        dto.current.wind_direction_10m = 250;
        dto.current.wind_gusts_10m = 18.0;
        dto.current.dew_point_2m = 14.2;
        dto.current.uv_index = 5.5;
        dto.current.visibility = 15000.0;
        dto.current.snow_depth = 0.0;

        WeatherSnapshotEntity entity = weatherMapper.toEntity(dto);

        assertThat(entity.latitude).isEqualTo(dto.latitude);
        assertThat(entity.longitude).isEqualTo(dto.longitude);
        assertThat(entity.temperature2m).isEqualTo(dto.current.temperature_2m);
        assertThat(entity.relativeHumidity2m).isEqualTo(dto.current.relative_humidity_2m);
        assertThat(entity.time).isEqualTo(dto.current.time);

        entity.persist();
        assertThat(entity.id).isNotNull();
        Long id = entity.id;


        WeatherSnapshotEntity retrieved = WeatherSnapshotEntity.findById(id);

        assertThat(retrieved).isNotNull();
        assertThat(retrieved.latitude).isEqualTo(dto.latitude);
        assertThat(retrieved.longitude).isEqualTo(dto.longitude);
        assertThat(retrieved.time).isEqualTo(dto.current.time);
        assertThat(retrieved.interval).isEqualTo(dto.current.interval);
        assertThat(retrieved.temperature2m).isEqualTo(dto.current.temperature_2m);
        assertThat(retrieved.relativeHumidity2m).isEqualTo(dto.current.relative_humidity_2m);
        assertThat(retrieved.apparentTemperature).isEqualTo(dto.current.apparent_temperature);
        assertThat(retrieved.isDay).isEqualTo(dto.current.is_day);
        assertThat(retrieved.precipitation).isEqualTo(dto.current.precipitation);
        assertThat(retrieved.rain).isEqualTo(dto.current.rain);
        assertThat(retrieved.showers).isEqualTo(dto.current.showers);
        assertThat(retrieved.snowfall).isEqualTo(dto.current.snowfall);
        assertThat(retrieved.weatherCode).isEqualTo(dto.current.weather_code);
        assertThat(retrieved.cloudCover).isEqualTo(dto.current.cloud_cover);
        assertThat(retrieved.pressureMsl).isEqualTo(dto.current.pressure_msl);
        assertThat(retrieved.surfacePressure).isEqualTo(dto.current.surface_pressure);
        assertThat(retrieved.windSpeed10m).isEqualTo(dto.current.wind_speed_10m);
        assertThat(retrieved.windDirection10m).isEqualTo(dto.current.wind_direction_10m);
        assertThat(retrieved.windGusts10m).isEqualTo(dto.current.wind_gusts_10m);
        assertThat(retrieved.dewPoint2m).isEqualTo(dto.current.dew_point_2m);
        assertThat(retrieved.uvIndex).isEqualTo(dto.current.uv_index);
        assertThat(retrieved.visibility).isEqualTo(dto.current.visibility);
        assertThat(retrieved.snowDepth).isEqualTo(dto.current.snow_depth);

        assertThat(retrieved.createdAt).isNotNull();
    }
}
