package ru.forolov.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.forolov.FirstRestApp.dto.SensorDTO;
import ru.forolov.FirstRestApp.models.Sensor;
import ru.forolov.FirstRestApp.services.SensorsService;
import ru.forolov.FirstRestApp.util.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);

        System.out.println(sensor.getName());
        sensorValidator.validate(sensor,bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error: errors ) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }

            throw new SensorNotRegisteredException(errorMessage.toString());
        }

        sensorsService.registration(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegisteredException e){
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new  ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
