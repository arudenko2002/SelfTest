package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMutexSemaphore {
	
	public final Lock lock = new ReentrantLock();
	int ilock=0;
	int unilock=0;	
	
	static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s\n", threadName, message);
   }
   public class myThread implements Runnable {

	   public void run() {
		   try {
			   synchronized(lock) {
				   Boolean b = lock.tryLock();
				   ilock++;
				   unilock--;
				   threadMessage("****** 24="+ilock+" "+unilock);
				   threadMessage("lt="+b);
	//			   if(!b){
	//				   lock.unlock();
	//				   ilock--;
	//				   unilock++;
	//				   threadMessage("****** 30="+ilock+" "+unilock);
	//			   }
	//			   if(b) {
	//				   lock.lock();
	//				   ilock++;
	//				   unilock--;
	//				   threadMessage("****** 36="+ilock+" "+unilock);
	//			   }
				   threadMessage("Sleeping");
	               Thread.sleep(4000);
	               threadMessage("Done");
	               lock.unlock();
	               ilock--;
				   unilock++;
				   threadMessage("******* 44="+ilock+" "+unilock);
			   }
           } catch (InterruptedException e) {
               threadMessage("I wasn't done!");
           }   
		   finally {
        	   lock.unlock();
        	   ilock--;
			   unilock++;
			   threadMessage("******* 52="+ilock+" "+unilock);
           }
	   }
   }
	
   void runthread() throws InterruptedException{
	   Thread t0 = new Thread(new myThread());
       Thread t1 = new Thread(new myThread());
       Thread t2 = new Thread(new myThread());
       t0.start();
       t1.start();
       t2.start();
   }
	
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		System.out.println("Hello thread, lock, mutex and semaphor");
		LockMutexSemaphore lms = new LockMutexSemaphore();
		lms.runthread();
	}

}
