import dto.MeasurementDTO;
import dto.MeasurementsResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {
    public static void main(String[] args) {
        List<Double> temperatures = getTemperatureFromServer();
        drawCharts(temperatures);
    }

    private static List<Double> getTemperatureFromServer(){
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        MeasurementsResponse measurementsResponse = restTemplate.getForObject(url, MeasurementsResponse.class);

        if (measurementsResponse == null || measurementsResponse.getMeasurements() == null){
            return Collections.emptyList();
        }else return measurementsResponse.getMeasurements().stream().map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }

    private static void drawCharts(List<Double> temperatures){
        double[] xDraw = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yDraw = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y",
                "name", xDraw, yDraw);

        new SwingWrapper(chart).displayChart();
    }
}
