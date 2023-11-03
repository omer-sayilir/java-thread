package net.sayilir.springmultithread.controller;

import net.sayilir.springmultithread.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Qualifier("taskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

Logger logger= LoggerFactory.getLogger(WeatherController.class);

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

            worker1 = weatherService.getWeatherStatus("ankara");
            logger.info("After three calls to async getWeatherStatus, active count: {}, Pool size: {}, Queue Size: {}", taskExecutor.getActiveCount(), taskExecutor.getPoolSize(), taskExecutor.getThreadPoolExecutor().getQueue().size());
            worker2 = weatherService.getWeatherStatus("gaziantep");
            logger.info( taskExecutor.getActiveCount()+" "+ taskExecutor.getPoolSize()+ " "+  taskExecutor.getThreadPoolExecutor().getQueue().size());
            worker3 = weatherService.getWeatherStatus("izmir");
            logger.info( taskExecutor.getActiveCount()+" "+ taskExecutor.getPoolSize()+ " "+  taskExecutor.getThreadPoolExecutor().getQueue().size());
            int sleeptime = 0;
            for(int i = 0; i < 20; i++) {
                Thread.sleep(100);
                sleeptime=(i+1)*100;
                logger.info("After {} milliseconds, active count: {}, Pool size: {}, Queue Size: {}", sleeptime, taskExecutor.getActiveCount(), taskExecutor.getPoolSize(), taskExecutor.getThreadPoolExecutor().getQueue().size());
            }
            getAllList.add(worker1.get());
            getAllList.add(worker2.get());
            getAllList.add(worker3.get());

        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return getAllList;

    }


}
