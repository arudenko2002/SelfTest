package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockBlock3 {
	static Object f1=new Object();
	static Object f2=new Object();
	static class F {
		static public void checkF1() {
			synchronized(f1) {
				System.out.println("AAAAAAAAAA");
				try {
					TimeUnit.MILLISECONDS.sleep(1000);;
				}catch(InterruptedException e) {}
				synchronized(f2) {
					System.out.println("BBBBBBBBBB");
				}
			}
		}
		
		static public void checkF2() {
			synchronized(f2) {
				System.out.println("AAAAAAAAAA");
				try {
					TimeUnit.MILLISECONDS.sleep(1000);;
				}catch(InterruptedException e) {}
				synchronized(f1) {
					System.out.println("BBBBBBBBBB");
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HW!");
		F f=new F();
		new Thread(new Runnable(){public void run(){F.checkF1();}}).start();
		new Thread(new Runnable(){public void run(){F.checkF2();}}).start();
	}

}
