package ru.forolov.FirstRestApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.forolov.FirstRestApp.models.Sensor;
import ru.forolov.FirstRestApp.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void registration(Sensor sensor){
        sensorsRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name){
        return sensorsRepository.findByName(name);
    }
}
