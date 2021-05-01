import javax.swing.*;

class MyThread implements Runnable {
    final JSlider slider1;
    boolean operation;

    public MyThread(JSlider slider1, boolean operation) {
        this.slider1 = slider1;
        this.operation = operation;
    }
    @Override
    public void run(){
        int new_value;
        if(operation)
            new_value = -1;
        else
            new_value = 1;
        while(!Thread.currentThread().isInterrupted()) {
            synchronized (slider1) {
                slider1.setValue(new_value + slider1.getValue());
            }
        }
    }
} 