package dto;
public class MeasurementDTO {

    private double value;

    private boolean rain;

    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
