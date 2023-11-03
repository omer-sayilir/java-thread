package net.sayilir.springbasicthread;

import net.sayilir.springbasicthread.runnable.RunnableWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringMultiThreadApplication {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for (int i=0; i<40;i++){
            RunnableWorker runnableWorker = new RunnableWorker("worker "+i);
            executorService.execute(runnableWorker);
        }

        SpringApplication.run(SpringMultiThreadApplication.class,args);


        System.out.println("All thread completed");
    }
}
