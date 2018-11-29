package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockMethods {

	public static class A {
		String s="";
		public A(String s) {
			this.s=s;
		}
		synchronized public void AA(A a) {
			System.out.println("AA "+this.s+" "+a.s);
			try {
			    TimeUnit.MICROSECONDS.sleep(2000000);
			} catch (InterruptedException e) {
			    //Handle exception
			}
			a.BB(this);
		}
		synchronized public void BB(A a) {
			System.out.println("BB "+this.s+" "+a.s);
		}
	}
	
	public static class TH extends Thread {
		A a=null;
		A b=null;
		public TH(A a, A b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public void run() {
			this.a.AA(b);
		}
	}
	
	public static class TH2 extends Thread {
		A a=null;
		A b=null;
		public TH2(A a) {
			this.a = a;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeadLockMethods dlm = new DeadLockMethods();
		A a1 = new A("a1");
		A a2 = new A("a2");

		TH t1 = new TH(a1,a2);
		TH t2 = new TH(a2,a1);
		t1.start();
		t2.start();	
//		
//		new Thread(new Runnable() {public void run() {a1.AA(a2);}}).start(); 
//		new Thread(new Runnable() {public void run() {a2.AA(a1);}}).start(); 
	}

}
