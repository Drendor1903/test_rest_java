package ru.forolov.FirstRestApp.util;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
