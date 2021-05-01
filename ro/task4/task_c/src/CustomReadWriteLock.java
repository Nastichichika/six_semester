public class CustomReadWriteLock {
    static final int WRITE_LOCKED = -1, FREE = 0;

    private int numberOfReaders = FREE;
    private Thread currentWriteLockOwner;

    public synchronized void ReadLock() throws InterruptedException {
        while(numberOfReaders == WRITE_LOCKED) wait();
        numberOfReaders++;
    }
    public synchronized void ReadUnlock() {
        if(numberOfReaders <= 0) throw new IllegalMonitorStateException();
        numberOfReaders--;
        if(numberOfReaders == FREE) notifyAll();
    }
    public synchronized void WriteLock() throws InterruptedException {
        while(numberOfReaders != FREE) wait();
        numberOfReaders = WRITE_LOCKED;
        currentWriteLockOwner = Thread.currentThread();
    }
    public synchronized void WriteUnlock() {
        if(numberOfReaders!=WRITE_LOCKED || currentWriteLockOwner!=Thread.currentThread())
            throw new IllegalMonitorStateException();
        numberOfReaders = FREE;
        currentWriteLockOwner = null;
        notifyAll();
    }
}
