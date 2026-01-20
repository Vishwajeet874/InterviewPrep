package Thread;

public class Counter {

    private  int counter=0;
        // we can use synchronized when there is race condition or collision this can be used in block of code or when u r defining one method too
    public synchronized void increment() {
        counter++;

    }

    public int getCounter() {
        return counter;
    }
}
