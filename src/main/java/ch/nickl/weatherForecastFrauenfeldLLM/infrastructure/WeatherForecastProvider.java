package ch.nickl.weatherForecastFrauenfeldLLM.infrastructure;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface WeatherForecastProvider {

    @SystemMessage("You are a weather expert. You will receive a complete list of all available current weather data from the last few hours and should create a precise forecast for the next day based on it. Please include a reference to the 'dew point' in your analysis if possible.")
    @UserMessage("Here is all available weather data from the last few hours: {data}. Analyze this data in depth (including details such as apparent temperature, dew point, air pressure changes, wind gusts, and various types of precipitation). What will the weather be like tomorrow? Answer concisely and well-founded.")
    String createForecast(String data);
}
