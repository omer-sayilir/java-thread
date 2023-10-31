package net.sayilir.springbasicthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringBasicThreadApplication {

    public static void main(String[] args) {

        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(()->{
            try{
                Thread.sleep(10000);
                System.out.println("hey");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        System.out.println("Hello from spring application");

        SpringApplication.run(SpringBasicThreadApplication.class, args);
    }


}
