package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockMethods4 {
	String n="";
	public DeadLockMethods4(String nn) {
		n=nn;
	}
	void delay(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		}catch(InterruptedException e){}
	}
	synchronized public void F1(DeadLockMethods4 DL) {
		System.out.println("forward="+this.n);
		delay(1000);
		DL.F2(this);
	}
	synchronized public void F2(DeadLockMethods4 DL) {
		System.out.println("backward="+DL.n);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("DeadLockMethods4");
		DeadLockMethods4 DD1 = new DeadLockMethods4("AAA");
		DeadLockMethods4 DD2 = new DeadLockMethods4("BBB");
		new Thread(new Runnable() {public void run(){DD1.F1(DD2);}}).start();
		new Thread(new Runnable() {public void run(){DD2.F1(DD1);}}).start();
	}

}
