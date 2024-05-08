/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class WeatherAdaptor {

    private static WeatherAdaptor _instance;

    private WeatherAdaptor() {

    }

    public static WeatherAdaptor getInstance() {
        if (_instance == null) {
            _instance = new WeatherAdaptor();
        }
        return _instance;
    }

    public String convertWindDirection(double windDirectionDegrees) {
        if (windDirectionDegrees >= 337.5 || windDirectionDegrees < 22.5) {
            return "N";
        } else if (windDirectionDegrees >= 22.5 && windDirectionDegrees < 67.5) {
            return "NE";
        } else if (windDirectionDegrees >= 67.5 && windDirectionDegrees < 112.5) {
            return "E";
        } else if (windDirectionDegrees >= 112.5 && windDirectionDegrees < 157.5) {
            return "SE";
        } else if (windDirectionDegrees >= 157.5 && windDirectionDegrees < 202.5) {
            return "S";
        } else if (windDirectionDegrees >= 202.5 && windDirectionDegrees < 247.5) {
            return "SW";
        } else if (windDirectionDegrees >= 247.5 && windDirectionDegrees < 292.5) {
            return "W";
        } else {
            return "NW";
        }
    }

    public double convertWindSpeed(double windSpeedMps) {
        return windSpeedMps * 3.6;
    }

    public double convertKelvinToCelsius(double temperatureKelvin) {
        return temperatureKelvin - 273;
    }
}
