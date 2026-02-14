# weatherForecastFrauenfeldLLM
LLM playground weather forecast for misty Frauenfeld

This is a LLM playground to create a simple weather forecast.

* fetch daily data from https://openweathermap.org/api
* store 'em in Postgres
* provide 'em as "history" an d kind of chat substitution to the LLM
* log a forecast


you gonna need a model by https://github.com/marketplace/models/
(should work with any model, lust limited by Quarkus lang chain)

fill the .env-dummy with your stuff and rename it to .env


sample output:

1st sample output:


``

2026-02-14 14:43:55,911 INFO  [ch.nic.wea.app.use.CreateWeatherForecastUseCase] (vert.x-worker-thread-1) newest forecast:
2026-02-14 14:43:55,911 INFO  [ch.nic.wea.app.use.CreateWeatherForecastUseCase] (vert.x-worker-thread-1) ### Weather Analysis (2026-02-14)

**Overview:**  
The collected data over the last few hours shows a stable, cool, and humid pattern with persistent overcast skies (100% cloud cover), light continuous rain (0.1mm per hour), and moderate NE winds.

---

**Detailed Observations:**

- **Temperature:**  
  Relatively steady at **6.5�6.7�C**, with an apparent temperature of **3.5�3.8�C** (wind and humidity make it feel colder).

- **Dew Point & Humidity:**  
  The **dew point is holding at 3.3�3.4�C** with **humidity at a high 79�80%**, indicating air near saturation. This supports ongoing damp, drizzly, and chilly conditions; any cooling tonight could trigger mist or low cloud.

- **Cloud Cover & Precipitation:**  
  **Complete overcast (100%)** with consistent, light **rainfall (0.1mm/hour)**, likely to persist given the stable cloud deck and saturated air. No snow.

- **Pressure:**  
  **Pressure is rising slightly** (1001.5 to 1002.8 hPa), often a sign of improving or stabilizing weather but not clear enough for immediate change.

- **Wind:**  
  Winds from the NE at **9�10 km/h**, gusting up to **20�23 km/h**. This adds a chill (apparent temps 2�3�C lower than actual). It also means unsettled weather will persist if the airflow continues.

- **Visibility:**  
  **Excellent (31�34 km)**, so no mist/fog at present.

---

### Forecast for Tomorrow (2026-02-15):

- **Morning:**  
  Expect **continued overcast skies**, possibly patchy drizzle or light rain lingering, especially early. Temperatures around **5�7�C**, with high humidity near 80%, keeping the air damp and chilly. If temperatures dip close to the current **dew point (around 3�4�C)** overnight, brief mist or low stratus clouds are possible.

- **Afternoon:**  
  **Cloud cover remains high (90�100%)**, though pressure�s slow rise could mean drizzle eases to overcast dry spells.  
  **Light NE winds (10 km/h)** persist; temps **7�8�C**, still chilly due to the wind and humidity.

- **Precipitation:**  
  Likely traces of light rain/drizzle in the morning, becoming more sporadic into the afternoon, but persistent dampness.

- **Wind:**  
  Winds remain **NE, 10 km/h**, occasional gusts over 20 km/h possible, so it will continue to feel colder than the measured temperature.

- **No risk of snow** with dew point and temperature both above freezing, and **visibility should stay good**.

---

#### **Summary:**
Tomorrow will be **cloudy, chilly, and damp**�light rains or drizzle are possible, especially through the morning, with little temperature variation (5�8�C). High humidity and a dew point near the air temperature ensure a persistent damp feeling, though conditions may slowly stabilize as air pressure rises. It will remain breezy and feel colder than the actual temperature, with little risk of improvement before late in the day.

---

**Key factor:**  
**Dew point (3�4�C)** remains close to low temperatures, so the air stays near saturation�expect persistent dampness and little drying out until a significant change in air mass.``