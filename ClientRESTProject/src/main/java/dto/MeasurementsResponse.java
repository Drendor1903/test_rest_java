package dto;


import java.util.List;

public class MeasurementsResponse {
    List<MeasurementDTO> measurements;

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurementsDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
