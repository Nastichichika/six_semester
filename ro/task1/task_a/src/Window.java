import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame
{
    private static MySlider slider1 = new MySlider(10, 90, 50);
    public Window()
    {
        super("Task1_a");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(10);
        slider1.setPreferredSize(new Dimension(250, 80));

        JPanel contents = new JPanel();

        getContentPane().add(contents);
        JLabel sl1 = new JLabel("First thread priority");
        JLabel sl2 = new JLabel("Second thread priority");
        JSlider slider2 = new JSlider(1, 9, 5);
        JSlider slider3 = new JSlider(1, 9, 5);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(2);
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(2);

        contents.add(sl1, BorderLayout.CENTER);
        contents.add(slider2, BorderLayout.CENTER);

        contents.add(sl2, BorderLayout.CENTER);
        contents.add(slider3, BorderLayout.CENTER);
        contents.add(slider1, BorderLayout.CENTER);

        JButton b = new JButton("Пуск");
        contents.add(b, BorderLayout.CENTER);

        Thread thread1 = new Thread(new MyThread(slider1, false ), "MyThread");
        Thread thread2 = new Thread(new MyThread(slider1, true), "MyThread");

        slider2.addChangeListener(
            e -> {
                thread1.setPriority(slider2.getValue());
            });
        slider3.addChangeListener(
            e -> {
                thread2.setPriority(slider3.getValue());
            });

        b.addActionListener(
            e -> {
                thread1.setDaemon(true);
                thread2.setDaemon(true);
                thread1.start();
                thread2.start();
            });
        setSize(280, 280);
        setVisible(true);
    }
}