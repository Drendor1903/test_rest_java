package ru.forolov.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.forolov.FirstRestApp.models.Measurement;
import ru.forolov.FirstRestApp.models.Sensor;
import ru.forolov.FirstRestApp.repositories.MeasurementsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void add(Measurement measurement){
        measurement.setMeasurementDateTime(new Date());
        measurement.setSensor(sensorsService.getSensorByName(measurement.getSensor().getName()).get());
        measurementsRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }
}
