package net.sayilir.springmultithread.runnable;

public class RunnableWorker implements  Runnable {
    String name;
    public RunnableWorker(String name) {
    this.name=name;
    }

    @Override
    public void run() {
        System.out.println("Worker thread:" + name + " started");
        try{
            Thread.sleep(2000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Worker "+ name + " completed");
    }
}
