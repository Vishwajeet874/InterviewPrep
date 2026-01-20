public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("T1 is running");
        try {
            Thread.sleep(2000);
            System.out.println("t1 ");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println(myThread.getState());
        myThread.start();

        System.out.println(myThread.getState());
        Thread.sleep(100);
        System.out.println(myThread.getState());

        myThread.join();
        System.out.println(myThread.getState());


        // we need runnable interface when there is scenario when we cant able to extend a thread class A extends class B implements runnable
    }
}
