package ru.forolov.FirstRestApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

    @Min(value = -100)
    @Max(value = 100)
    @NotNull(message = "Данное поле не может иметь пустое значение")
    private double value;

    @NotNull(message = "Данное поле не может иметь пустое значение")
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

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", rain=" + rain +
                ", sensor=" + sensor +
                '}';
    }
}
