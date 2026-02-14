package ch.nickl.weatherForecastFrauenfeldLLM.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "weather_snapshot")
@Getter
@Setter
public class WeatherSnapshotEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "latitude")
    public double latitude;

    @Column(name = "longitude")
    public double longitude;

    @Column(name = "time")
    public String time;

    @Column(name = "interval_sec")
    public int interval;

    @Column(name = "temperature_2m")
    public double temperature2m;

    @Column(name = "relative_humidity_2m")
    public int relativeHumidity2m;

    @Column(name = "apparent_temperature")
    public double apparentTemperature;

    @Column(name = "is_day")
    public int isDay;

    @Column(name = "precipitation")
    public double precipitation;

    @Column(name = "rain")
    public double rain;

    @Column(name = "showers")
    public double showers;

    @Column(name = "snowfall")
    public double snowfall;

    @Column(name = "weather_code")
    public int weatherCode;

    @Column(name = "cloud_cover")
    public int cloudCover;

    @Column(name = "pressure_msl")
    public double pressureMsl;

    @Column(name = "surface_pressure")
    public double surfacePressure;

    @Column(name = "wind_speed_10m")
    public double windSpeed10m;

    @Column(name = "wind_direction_10m")
    public int windDirection10m;

    @Column(name = "wind_gusts_10m")
    public double windGusts10m;

    @Column(name = "dew_point_2m")
    public double dewPoint2m;

    @Column(name = "uv_index")
    public double uvIndex;

    @Column(name = "visibility")
    public double visibility;

    @Column(name = "snow_depth")
    public double snowDepth;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    public OffsetDateTime createdAt;
}
