package ru.forolov.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100)
    @Max(value = 100)
    @NotNull
    private double value;

    @NotNull
    @Column(name = "rain")
    private boolean rain;

    @Column(name = "measurement_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measurementDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor", referencedColumnName = "sensor_name")
    private Sensor sensor;

    public Measurement(){}

    public Measurement(double value, boolean rain) {
        this.value = value;
        this.rain = rain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(Date measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", rain=" + rain +
                ", sensor=" + sensor +
                '}';
    }
}
