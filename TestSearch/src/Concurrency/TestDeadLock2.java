package Concurrency;

public class TestDeadLock2 {
   public static Object Lock1 = new Object();
   public static Object Lock2 = new Object();
   
   private static class ThreadDemo1 extends Thread {
      public void run() {
    	  System.out.println("ThreadDemo1 started");
         synchronized (Lock1) {
            System.out.println("Thread 1: Holding lock 1...");
            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println("Thread 1: Waiting for lock 2...");
            synchronized (Lock2) {
               System.out.println("Thread 1: Holding lock 1 & 2...");
            }
         }
      }
   }
   
   private static class ThreadDemo_No_Dead_Lock extends Thread {
      public void run() {
    	  System.out.println("ThreadDemo_No_Dead_Lock started");
         synchronized (Lock1) {
            System.out.println("Thread No dead lock: Holding lock 2...");
            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println("Thread No Dead Lock: Waiting for lock 1...");
            synchronized (Lock2) {
               System.out.println("Thread No Dead Lock: Holding lock 1 & 2...");
            }
         }
      }
   } 

   private static class ThreadDemo2 extends Thread {
      public void run() {
    	 System.out.println("ThreadDemo2 started");
         synchronized (Lock2) {
            System.out.println("Thread 2: Holding lock 2...");
            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println("Thread 2: Waiting for lock 1...");
            synchronized (Lock1) {
               System.out.println("Thread 2: Holding lock 1 & 2...");
            }
         }
      }
   } 
   
   public static void main(String[] args) {   
      ThreadDemo1 T1 = new ThreadDemo1();
      ThreadDemo_No_Dead_Lock NoDLwithThreadDemo1 = new  ThreadDemo_No_Dead_Lock();
      ThreadDemo2 T2 = new ThreadDemo2();
      T1.start();
      T2.start();
      NoDLwithThreadDemo1.start();
   }
}
