public class Table {
    boolean tobacco;
    boolean paper;
    boolean matches;



    synchronized public void setTobacco() {
        this.tobacco = false;
    }

    synchronized public void setPaper() {
        this.paper = false;
    }

    synchronized public void setMatches() {
        this.matches = false;
    }

    synchronized public void allTrue() {
        this.matches = true;
        this.paper = true;
        this.tobacco = true;
    }
}
