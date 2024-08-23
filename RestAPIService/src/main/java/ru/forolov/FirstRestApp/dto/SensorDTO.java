package ru.forolov.FirstRestApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty
    @Size(min = 3, max = 30, message = "Название сенсора должно быть длинною от 3 до 30 символов.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}