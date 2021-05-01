public class Monk {
    public int temple;
    public int power;
    public int countOfVictory = 0;
    public Monk(int power, int temple){
        this.power = power;
        this.temple = temple;
    }
    synchronized void addVictory() {
        this.countOfVictory++;
    }
}
