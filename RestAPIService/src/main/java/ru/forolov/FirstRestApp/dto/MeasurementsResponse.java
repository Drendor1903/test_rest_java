package ru.forolov.FirstRestApp.dto;

import ru.forolov.FirstRestApp.models.Measurement;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementDTO> measurementsDTO;

    public MeasurementsResponse(List<MeasurementDTO> measurementsDTO){
        this.measurementsDTO = measurementsDTO;
    }

    public List<MeasurementDTO> getMeasurementsDTO() {
        return measurementsDTO;
    }

    public void setMeasurementsDTO(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }
}
