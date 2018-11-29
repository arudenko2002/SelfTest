package selftest;

import java.util.concurrent.TimeUnit;

public class DeadLockMethods2 {
	
	static class F {
		String A="";
		public F(String A) {
			this.A=A;
		}
		synchronized void F1(F FF2) {
			System.out.println("AAAAAAAA "+this.A+" "+FF2.A);
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				}catch(InterruptedException e) {
					
				}
			FF2.F2(this);
		}
		synchronized void F2(F FF2) {
			System.out.println("BBBBBBBB "+this.A+" "+FF2.A);
		}
	}
	
	static class TH extends Thread {
		F FF=null;
		F FF2=null;
		public TH(F F1, F F2) {
			this.FF=F1;
			this.FF2=F2;
		}
		@Override
		public void run() {
			FF.F1(FF2);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		//DeadLockMethods2 db = new DeadLockMethods2();
		F F1=new F("A");
		F F2=new F("B");
		TH t1 = new TH(F1,F2);
		TH t2 = new TH(F2,F1);
		//t1.start();
	    //t2.start();
		
		//new Thread(new Runnable(){public void run(){F1.F1(F2);}}).start();
		//new Thread(new Runnable(){public void run(){F2.F1(F1);}}).start();
	}

}
