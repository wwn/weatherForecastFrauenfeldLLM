package ch.nickl.weatherForecastFrauenfeldLLM.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenMeteoWeatherSnapshotResponseDto {
    public double latitude;
    public double longitude;
    public Current current;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        public String time;
        public int interval;
        public double temperature_2m;
        public int relative_humidity_2m;
        public double apparent_temperature;
        public int is_day;
        public double precipitation;
        public double rain;
        public double showers;
        public double snowfall;
        public int weather_code;
        public int cloud_cover;
        public double pressure_msl;
        public double surface_pressure;
        public double wind_speed_10m;
        public int wind_direction_10m;
        public double wind_gusts_10m;
        public double dew_point_2m;
        public double uv_index;
        public double visibility;
        public double snow_depth;
    }
}
