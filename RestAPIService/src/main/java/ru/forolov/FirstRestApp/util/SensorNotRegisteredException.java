package ru.forolov.FirstRestApp.util;

public class SensorNotRegisteredException extends RuntimeException {
    public SensorNotRegisteredException(String msg){
        super(msg);
    }
}
