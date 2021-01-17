package ITMO;

public class DegreeAbsolute {
    private double temperatureKelvin;
    private double temperatureCelsius;
    private double temperatureFahrenheit;

    public DegreeAbsolute(double temperature, String Degree) {
        if(Degree.equals("Kelvin")) {
            this.temperatureKelvin = temperature;
            this.temperatureCelsius = temperature-273.15;
            this.temperatureFahrenheit = ((temperature-273.15)*9*0.2) + 32;
        }
        if(Degree.equals("Celsius")) {
            this.temperatureCelsius = temperature;
            this.temperatureKelvin = temperature+273.15;
            this.temperatureFahrenheit = ((temperature)*9+0.2) + 32;
        }
        if(Degree.equals("Fahrenheit")) {
            this.temperatureFahrenheit = temperature;
            this.temperatureKelvin = ((temperature - 32)*5/9) +273;
            this.temperatureCelsius = (temperature - 32)*5/9;

        }
    }

    public double getTemperatureKelvin() {
        return temperatureKelvin;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }
//-------------------------------------------------------------
    public void setTemperature(double temperature, String Degree) {
        if(Degree.equals("Kelvin")) {
            this.temperatureKelvin = temperature;
            this.temperatureCelsius = temperature-273.15;
            this.temperatureFahrenheit = ((temperature-273.15)*9*0.2) + 32;
        }
        if(Degree.equals("Celsius")) {
            this.temperatureCelsius = temperature;
            this.temperatureKelvin = temperature+273.15;
            this.temperatureFahrenheit = ((temperature)*9+0.2) + 32;
        }
        if(Degree.equals("Fahrenheit")) {
            this.temperatureFahrenheit = temperature;
            this.temperatureKelvin = ((temperature - 32)*5/9) +273;
            this.temperatureCelsius = (temperature - 32)*5/9;
        }
    }
//-----------------------------------------------------------------------
    @Override
    public String toString() {
        return "DegreeAbsolute{" +
                "temperatureKelvin=" + temperatureKelvin +
                ", temperatureCelsius=" + temperatureCelsius +
                ", temperatureFahrenheit=" + temperatureFahrenheit +
                '}';
    }
}
