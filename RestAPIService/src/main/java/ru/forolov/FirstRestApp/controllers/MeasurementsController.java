package ru.forolov.FirstRestApp.controllers;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.forolov.FirstRestApp.dto.MeasurementDTO;
import ru.forolov.FirstRestApp.dto.MeasurementsResponse;
import ru.forolov.FirstRestApp.models.Measurement;
import ru.forolov.FirstRestApp.services.MeasurementsService;
import ru.forolov.FirstRestApp.services.SensorsService;
import ru.forolov.FirstRestApp.util.MeasurementErrorResponse;
import ru.forolov.FirstRestApp.util.MeasurementNotCreatedException;
import ru.forolov.FirstRestApp.util.MeasurementValidator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult){

        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);
        System.out.println(measurement);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error: errors ) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }

            throw new MeasurementNotCreatedException(errorMessage.toString());
        }

        measurementsService.add(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public MeasurementsResponse findAll(){
        return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long rainyDaysCount(){
        return measurementsService.findAll().stream().filter(Measurement::isRain).count();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e){
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new  ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
