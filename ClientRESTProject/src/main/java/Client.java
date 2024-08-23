import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String sensor = "Test";

        registerSensor(sensor);

        Random random = new Random();

        double maxTemperature = 50.0;
        for (int i = 0; i < 500; i++){
            sendMeasurement((random.nextDouble() * maxTemperature),
                    random.nextBoolean(), sensor);
        }
    }

    private static void registerSensor(String sensorName) {
        String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("name", sensorName);

        requestWithJsonData(url, jsonObject);
    }

    private static void sendMeasurement(double value, boolean rain, String sensorName){
        String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("value", value);
        jsonObject.put("rain", rain);
        jsonObject.put("sensor", Map.of("name", sensorName));

        requestWithJsonData(url, jsonObject);
    }

    private static void requestWithJsonData(String url, Map<String, Object> jsonObject){
        final RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(jsonObject, httpHeaders);

        try {
            restTemplate.postForObject(url, httpEntity, String.class);

            System.out.println("Успешно");

        }catch (HttpClientErrorException e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

    }
}
