package net.sayilir.springmultithread.controller;

import net.sayilir.springmultithread.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class WeatherController {
    @Autowired
     private WeatherService weatherService;

    private final ThreadPoolTaskExecutor taskExecutor;

    public WeatherController(@Qualifier("taskExecutor") ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

//    public WeatherController(WeatherService weatherService, ThreadPoolTaskExecutor taskExecutor) {
//        this.weatherService = weatherService;
//        this.taskExecutor = taskExecutor;
//    }

    @GetMapping(value = "/getWeatherList", produces = "application/json")
    public ResponseEntity getWeatherList() {

        List<Double> getAllList = getWeatherAllList();
        return ResponseEntity.ok().body(getAllList);
    }

    private List<Double> getWeatherAllList() {
        List<Double> getAllList = new ArrayList<>();
        CompletableFuture<Double> worker1, worker2, worker3;
        try {
            System.out.println( taskExecutor.getActiveCount()+" "+ taskExecutor.getPoolSize()+ " "+  taskExecutor.getThreadPoolExecutor().getQueue().size());
            worker1 = weatherService.getWeatherStatus("ankara");
            worker2 = weatherService.getWeatherStatus("gaziantep");
            worker3 = weatherService.getWeatherStatus("izmir");
            getAllList.add(worker1.get());
            getAllList.add(worker2.get());
            getAllList.add(worker3.get());

        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return getAllList;

    }


}
