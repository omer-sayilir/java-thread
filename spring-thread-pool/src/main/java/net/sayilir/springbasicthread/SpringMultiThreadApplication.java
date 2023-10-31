package net.sayilir.springbasicthread;

import net.sayilir.springbasicthread.runnable.RunnableWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringMultiThreadApplication {
    public static void main(String[] args){
        Executor executor= Executors.newSingleThreadExecutor();
        RunnableWorker runnableWorker= new RunnableWorker("worker 1");
        executor.execute(runnableWorker);
        SpringApplication.run(SpringMultiThreadApplication.class,args);
    }
}
