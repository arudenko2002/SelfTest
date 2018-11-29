package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockMethod3 {
	static class A {
		String ss="";
		public A(String s) {
			ss=s;
		}
		synchronized void F1(A F1) {
			System.out.println("HW!!!"+this.ss+"  "+F1.ss);
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch(InterruptedException e){}
			F1.F2(this);
		}
		synchronized void F2(A F1) {
			System.out.println("HW!!!"+this.ss+"  "+F1.ss);
		}
	}
	
	static class TH extends Thread {
		A c1=null;
		A c2=null;
		public TH(A a1,A a2) {
			c1=a1;
			c2=a2;
		}
		@Override
		public void run() {
			c1.F1(c2);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HW!!!");
		A a1=new A("A");
		A a2=new A("B");
		TH t1=new TH(a1,a2);
		TH t2=new TH(a2,a1);
		t1.start();
		t2.start();
		
	}

}
