package Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAcc {
    private int balance=100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " initiating " + amount + " from bank");
        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if(balance>=amount) {
                    System.out.println(Thread.currentThread().getName() + " withdrawing " + amount + " from bank");
                    Thread.sleep(5000);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " withdrawl done " + amount + " from bank");
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " insuffiecient " + amount + " from bank");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        balance -= amount;
    }
}
