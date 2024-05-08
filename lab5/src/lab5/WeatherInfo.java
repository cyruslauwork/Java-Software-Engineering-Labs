/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.net.URL;
import weather.OpenWeather;
import weather.WeatherSummary;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class WeatherInfo {

    private static WeatherInfo _instance;

    private WeatherInfo() {

    }

    public static WeatherInfo getInstance() {
        if (_instance == null) {
            _instance = new WeatherInfo();
        }
        return _instance;
    }

    private String description;
    private String temperature;
    private String windSpeedDirection;
    private URL icon;

    OpenWeather ow = new OpenWeather();
    WeatherAdaptor wa = WeatherAdaptor.getInstance();

    public WeatherInfo getInfo(String city_name) {
        WeatherSummary ws = ow.getWeatherSummary(city_name);
//        System.out.println(city_name);

        if (ws != null) {
            description = ws.getDescription();
            temperature = String.format("%.2f°C", wa.convertKelvinToCelsius(ws.getTemperature()));
            double windSpeedKph = wa.convertWindSpeed(ws.getWindSpeed());
            String windDirectionText = wa.convertWindDirection(ws.getWindDirection());
            windSpeedDirection = String.format("%.2f km/hr %s", windSpeedKph, windDirectionText);
            icon = ws.getIcon();

            return _instance;
        } else {
            return null;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeedDirection() {
        return windSpeedDirection;
    }

    public URL getIcon() {
        return icon;
    }
}
