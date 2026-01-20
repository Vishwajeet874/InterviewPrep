package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTut {
    private final Lock lock = new ReentrantLock();
    public void outerLock(){
        lock.lock();
        try{
            System.out.println("Outer method ");
            innerMethod();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {

            lock.unlock();
        }

    }

    private void innerMethod() {
        lock.lock();
        try{
            System.out.println("Inner method ");
        }
        finally {

            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTut lockTut = new LockTut();
        lockTut.outerLock();
    }

    //this condition is called deadlock because inner method is depending on the outer method to get finished and outer method is depending on inner method to get finished.
}
