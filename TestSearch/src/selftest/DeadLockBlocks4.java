package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockBlocks4 {
	Object O1=new Object();
	Object O2=new Object();
	void F1() {
		synchronized(O1) {
			System.out.println("O1-O2");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			}catch (InterruptedException e) {}
			synchronized(O2) {
				System.out.println("O1-O2");
			}
		}
	}
	
	void F2() {
		synchronized(O2) {
			System.out.println("O2-O1");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			}catch (InterruptedException e) {}
			synchronized(O1) {
				System.out.println("O2-O1");
			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeadLockBlocks4 dl4 = new DeadLockBlocks4();
		System.out.println("Dead Lock Block #4");
		new Thread(new Runnable(){public void run(){dl4.F1();}}).start();
		new Thread(new Runnable(){public void run(){dl4.F2();}}).start();
	}

}
