import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyThread implements Runnable {
    final JSlider slider1;
    boolean operation;
    AtomicInteger semaphore;

    public MyThread(JSlider slider1, boolean operation, AtomicInteger semaphore) {
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
        System.out.println(semaphore.get());
        while(semaphore.intValue() != 0) {
            synchronized (slider1) {
                slider1.setValue(new_value + slider1.getValue());
            }
        }
    }
} 