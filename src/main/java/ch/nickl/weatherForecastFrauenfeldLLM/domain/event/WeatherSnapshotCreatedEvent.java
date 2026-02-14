package ch.nickl.weatherForecastFrauenfeldLLM.domain.event;

import ch.nickl.weatherForecastFrauenfeldLLM.domain.model.WeatherSnapshotEntity;

public record WeatherSnapshotCreatedEvent(WeatherSnapshotEntity snapshot) {
    @Override
    public String toString() {
        return "WeatherSnapshotCreatedEvent[id=" + (snapshot != null ? snapshot.id : "null") + "]";
    }
}
