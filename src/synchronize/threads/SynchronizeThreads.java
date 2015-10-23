/*
 * Michael Feuerstein
 * Synchronize Threads Lab
 * 10/23/2015
 */
package synchronize.threads;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class SynchronizeThreads {
    private static Sum account = new Sum();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         ExecutorService executor = Executors.newCachedThreadPool();
         
          // Create and launch 1000 AddOne threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(new AddOne());
        }
        executor.shutdown();
        
        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        // display result of thread addition
        System.out.println("Your sum is  " + Sum.getSum());
        
    }
    
    private static class AddOne implements Runnable {
        public void run() {
           account.addOne();
        }
    }

    private static class Sum {
        
        private static int sum = new Integer(0);
        private static Lock lock = new ReentrantLock();

        public static int getSum() {
            return sum;
        }

        public void addOne() {
            
            lock.lock();
            try {
                 Thread.sleep(4);
                sum++;
            }

            catch (InterruptedException ex) {
            }
            
            finally{
                lock.unlock();
            }
        }
    }
}

