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
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for (int i=0; i<10;i++){
            RunnableWorker runnableWorker = new RunnableWorker("worker "+i);
            executorService.execute(runnableWorker);
        }
        executorService.shutdown();
        SpringApplication.run(SpringMultiThreadApplication.class,args);

        while(!executorService.isTerminated()){
            //wait
        }
        System.out.println("All thread completed");
    }
}
