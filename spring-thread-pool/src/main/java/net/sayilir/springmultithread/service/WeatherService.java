package net.sayilir.springmultithread.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {
    @Async
    public CompletableFuture<Double> getWeatherStatus(String city) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000*3));
        System.out.println("oldu mu?"+city);
        Double temperature;
        switch (city) {
            case "ankara":
                temperature = 19.0;
                break;
            case "gaziantep":
                temperature = 21.2;
                break;
            case "izmir":
                temperature = 31.8;
                break;
            default:
                temperature = null;
        }
        return CompletableFuture.completedFuture(temperature);
    }

}
