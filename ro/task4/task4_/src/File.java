import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class File {
    public ArrayList<Record> records = new ArrayList<>();
    private ReadWriteLock reedWriteLock = new ReentrantReadWriteLock();
    public File() {
        this.records.add(new Record("Anastasiia",  "Kuzmych", "0956963345"));
        records.add(new Record("Ilya", "Korenevsky", "0994586312"));
        records.add(new Record("Alex", "Smith",  "0672586341"));
        records.add(new Record("Max", "Black",  "0505542563"));
    }

    public void LockRead() {

        reedWriteLock.readLock().lock();
    }
    public void UnlockRead() {

        reedWriteLock.readLock().unlock();
    }
    public void LockWrite() {

        reedWriteLock.writeLock().lock();
    }
    public void UnlockWrite() {

        reedWriteLock.writeLock().unlock();
    }
}
