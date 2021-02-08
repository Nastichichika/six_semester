import javax.swing.*;

public class MySlider extends JSlider {

    public MySlider(int i, int i1, int i2) {
        super(i, i1, i2);
    }

    @Override
    public synchronized int getValue() {
        return super.getValue();
    }

    @Override
    public synchronized void setValue(int n) {
        super.setValue(n);
    }
}
