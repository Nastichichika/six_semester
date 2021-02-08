import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Window extends JFrame
{
    private static MySlider slider1 = new MySlider(10, 90, 50);
    public Window()
    {
        super("Task1_b");

        Semaphore semaphore = new Semaphore(1);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MySlider slider1 = new MySlider(10, 90, 50);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(10);
        slider1.setPreferredSize(new Dimension(250, 80));

        JPanel contents = new JPanel();
        contents.add(slider1, BorderLayout.NORTH);

        JButton start1 = new JButton("Пуск1");
        contents.add(start1, BorderLayout.SOUTH);
        AtomicInteger t_num = new AtomicInteger();

        JButton start2 = new JButton("Пуск2");
        contents.add(start2);

        JButton stop1 = new JButton("Стоп1");
        contents.add(stop1);

        JButton stop2 = new JButton("Стоп2");
        contents.add(stop2);

        getContentPane().add(contents);


        start1.addActionListener(
                e ->{
                    int check = semaphore.availablePermits();
                    if(check == 0) {
                        JOptionPane.showMessageDialog(contents, "Занято потоком");
                        return;
                    }

                    Thread thread1 = new Thread(new MyThread(slider1, true, semaphore),"MyThread");
                    thread1.start();
                    thread1.setPriority(1);
                    t_num.set(1);
        });

        start2.addActionListener(
                e -> {
                    int check = semaphore.availablePermits();
                    if(check == 0) {
                        JOptionPane.showMessageDialog(contents, "Занято потоком");
                        return;
                    }

                    Thread thread2 = new Thread(new MyThread(slider1, false, semaphore),"MyThread");
                    thread2.start();
                    thread2.setPriority(1);

                    t_num.set(2);
        });

        stop1.addActionListener(
                e -> {
                    if(t_num.get() != 1){
                        return;
                    }
                    semaphore.release();
                    System.out.println(semaphore.availablePermits());
        });

        stop2.addActionListener(
                e -> {
                    if(t_num.get() != 2){
                        return;
                    }
                    semaphore.release();
                    System.out.println(semaphore.availablePermits());
        });
        setSize(330, 170);
        setVisible(true);
    }
}