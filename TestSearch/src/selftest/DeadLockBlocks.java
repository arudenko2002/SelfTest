package selftest;

public class DeadLockBlocks {
	final static Object Lock1=new Object();
	final static Object Lock2=new Object();
	
	public static class A extends Thread {
	@Override
	public void run() {
		System.out.println("A waiting..");
		synchronized(Lock1) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
			synchronized(Lock2) {
				System.out.println("Done");
			}
		}
	}
	}
	
	public static class B extends Thread {
	@Override
	public void run() {
		System.out.println("B waiting..");
		synchronized(Lock2) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
			synchronized(Lock1) {
				System.out.println("Done");
			}
		}
	}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B b = new B();
		A a = new A();
		a.start();
		b.start();
	}

}
