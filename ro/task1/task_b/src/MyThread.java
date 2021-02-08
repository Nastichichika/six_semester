import javax.swing.*;
import java.util.concurrent.Semaphore;

class MyThread implements Runnable {
    final JSlider slider1;
    boolean operation;
    Semaphore semaphore;

    public MyThread(JSlider slider1, boolean operation, Semaphore semaphore) {
        this.slider1 = slider1;
        this.operation = operation;
        this.semaphore = semaphore;
    }
    @Override
    public void run(){
        int new_value;
        if(operation)
            new_value = -1;
        else
            new_value = 1;

        try {
            semaphore.acquire();
            while(semaphore.availablePermits() == 0) {
                synchronized (slider1) {
                    slider1.setValue(new_value + slider1.getValue());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 