package ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.mapper;

import ch.nickl.weatherForecastFrauenfeldLLM.domain.model.WeatherSnapshotEntity;
import ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.dto.OpenMeteoWeatherSnapshotResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface WeatherMapper {

    ZoneId ZURICH_ZONE = ZoneId.of("Europe/Zurich");

    @Mapping(source = "current.time", target = "time")
    @Mapping(source = "current.interval", target = "interval")
    @Mapping(source = "current.temperature_2m", target = "temperature2m")
    @Mapping(source = "current.relative_humidity_2m", target = "relativeHumidity2m")
    @Mapping(source = "current.apparent_temperature", target = "apparentTemperature")
    @Mapping(source = "current.is_day", target = "isDay")
    @Mapping(source = "current.precipitation", target = "precipitation")
    @Mapping(source = "current.rain", target = "rain")
    @Mapping(source = "current.showers", target = "showers")
    @Mapping(source = "current.snowfall", target = "snowfall")
    @Mapping(source = "current.weather_code", target = "weatherCode")
    @Mapping(source = "current.cloud_cover", target = "cloudCover")
    @Mapping(source = "current.pressure_msl", target = "pressureMsl")
    @Mapping(source = "current.surface_pressure", target = "surfacePressure")
    @Mapping(source = "current.wind_speed_10m", target = "windSpeed10m")
    @Mapping(source = "current.wind_direction_10m", target = "windDirection10m")
    @Mapping(source = "current.wind_gusts_10m", target = "windGusts10m")
    @Mapping(source = "current.dew_point_2m", target = "dewPoint2m")
    @Mapping(source = "current.uv_index", target = "uvIndex")
    @Mapping(source = "current.visibility", target = "visibility")
    @Mapping(source = "current.snow_depth", target = "snowDepth")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(getCurrentUtcTime())")
    WeatherSnapshotEntity toEntity(OpenMeteoWeatherSnapshotResponseDto response);

    default OffsetDateTime getCurrentUtcTime() {
        return OffsetDateTime.now(ZoneId.of("UTC"));
    }

    @Named("toZurichTime")
    default ZonedDateTime toZurichTime(OffsetDateTime utcTime) {
        if (utcTime == null) return null;
        return utcTime.atZoneSameInstant(ZURICH_ZONE);
    }
}
