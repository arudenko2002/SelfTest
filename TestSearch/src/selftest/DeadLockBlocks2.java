package selftest;
import java.util.concurrent.TimeUnit;

public class DeadLockBlocks2 {
	Object F1=new Object();
	Object F2=new Object();
	class C {
		void F00() {
			synchronized(F1) {
				System.out.println("000000000");
				try {
				TimeUnit.MILLISECONDS.sleep(1000);
				}catch(InterruptedException e) {
					
				}
				synchronized(F2) {
					System.out.println("AAAAAAAAA");
				}
			}
		}
		void F11() {
			synchronized(F2) {
				System.out.println("111111111");
				try {
				TimeUnit.MILLISECONDS.sleep(1000);
				}catch(InterruptedException e) {
					
				}
				synchronized(F1) {
					System.out.println("BBBBBBBBBBB");
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeadLockBlocks2 db = new DeadLockBlocks2();
		C C1=db.new C();
		System.out.println("Hello world!");
		new Thread(new Runnable(){public void run(){C1.F00();}}).start(); 
		new Thread(new Runnable(){public void run(){C1.F11();}}).start();
	}

}
