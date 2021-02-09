import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Window extends JFrame
{
    private static Thread thread1;
    private static Thread thread2;
    private static MySlider slider1 = new MySlider(10, 90, 50);
    public Window()
    {
        super("Task1_b");

        AtomicInteger semaphore = new AtomicInteger(0);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MySlider slider1 = new MySlider(10, 90, 50);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(10);
        slider1.setPreferredSize(new Dimension(250, 80));

        JPanel contents = new JPanel();
        contents.add(slider1, BorderLayout.NORTH);

        JButton start1 = new JButton("Пуск1");
        contents.add(start1, BorderLayout.SOUTH);

        JButton start2 = new JButton("Пуск2");
        contents.add(start2);

        JButton stop1 = new JButton("Стоп1");
        contents.add(stop1);

        JButton stop2 = new JButton("Стоп2");
        contents.add(stop2);

        getContentPane().add(contents);

        start1.addActionListener(
                e ->{
                    if(semaphore.compareAndSet(0,1)) {
                        thread1 = new Thread(new MyThread(slider1, true),"MyThread");
                        thread1.start();
                        thread1.setPriority(Thread.MIN_PRIORITY);
                    }
                    else {
                        JOptionPane.showMessageDialog(contents, "Занято потоком");
                    }
                });

        start2.addActionListener(
                e -> {
                    if(semaphore.compareAndSet(0,2)) {
                        thread2 = new Thread(new MyThread(slider1, false), "MyThread");
                        thread2.start();
                        thread2.setPriority(Thread.MAX_PRIORITY);
                    }
                    else {
                        JOptionPane.showMessageDialog(contents, "Занято потоком");
                    }
        });

        stop1.addActionListener(
                e -> {
                    if(semaphore.compareAndSet(1,0)) {
                        thread1.interrupt();
                    }
        });

        stop2.addActionListener(
                e -> {
                    if( semaphore.compareAndSet(2,0)) {
                        thread2.interrupt();
                    }
        });
        setSize(330, 170);
        setVisible(true);
    }
}