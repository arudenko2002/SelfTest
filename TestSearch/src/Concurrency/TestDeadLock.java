package Concurrency;

import java.util.concurrent.TimeUnit;

public class TestDeadLock {
	public class counter {
		int i=0;
		int c=0;
		synchronized void increment() {
			i++;
			c++;
//			try {
//				System.out.println("Waiting... inc "+i+"  "+c);
//                wait();
//                System.out.println("Waited inc "+i+"  "+c);
//            } catch (InterruptedException e) {}
//			System.out.println("Notifying... inc "+i+"  "+c);
//			notify();
//			System.out.println("Notified inc "+i+"  "+c);
		}
		synchronized void decrement() {
			i--;
			c++;
//			System.out.println("Notifying... dec "+i+"  "+c);
//			notify();
//			System.out.println("Notified dec "+i+"  "+c);
//			try {
//				System.out.println("Waiting... dec "+i+"  "+c);
//                wait();
//                System.out.println("Waited dec "+i+"  "+c);
//            } catch (InterruptedException e) {}
//			System.out.println("Notifying... dec "+i+"  "+c);
		}
		int getCounter() {
			return i;
		}
	}
	public class Friend2 {
		String var;	
		counter c;
		public int vol=0;
		public Friend2(String v, counter c){this.var=v;this.c=c;}
		String getVar(){return this.var;}
		synchronized void bow(Friend2 s, int flag, int number){
			System.out.println("report!"+this.var+"  "+s.getVar());
			if(flag>0) {

				for(int i=0; i< number;i++){
					vol++;
					c.increment();
					//delay(10);
				}
			}
			System.out.println("after report!"+this.var+"  "+s.getVar()+"   vol="+vol+"   iii="+c.getCounter());
			s.bow2(this);
			System.out.println("Leaving bow");
		}
		void bow2(Friend2 s){
			synchronized(this) {
				System.out.println("SASHA!"+this.var+"  "+s.getVar());
			}
		}
		synchronized void bow3(Friend2 s, int flag, int number){
			System.out.println("3 report!"+this.var+"  "+s.getVar());
			if(flag>0) {
				for(int i=0; i< number;i++){
					vol++;
					c.decrement();
					//delay(2);
				}

			}
			System.out.println("after3 report!"+this.var+"  "+s.getVar()+"   vol="+vol+"   iii="+c.getCounter());
			s.bow2(this);
			System.out.println("Leaving bow3");
		}
		
		void delay(int timeunits) {
			try {
			    //TimeUnit.NANOSECONDS.sleep(100);
			    TimeUnit.MICROSECONDS.sleep(timeunits);
			    //TimeUnit.MILLISECONDS.sleep(100);
			    //TimeUnit.SECONDS.sleep(100);
			    //TimeUnit.MINUTES.sleep(100);
			    //TimeUnit.HOURS.sleep(100);
			    //TimeUnit.DAYS.sleep(100);
			} catch (InterruptedException e) {
			    //Handle exception
			}
		}
	}
    public static void main(String[] args) {
    	int deadlockflag=1;
    	TestDeadLock tdl = new TestDeadLock();
    	counter c = tdl.new counter();
    	Friend2 aaa2 = tdl.new Friend2("aaa",c);
    	Friend2 bbb2 = tdl.new Friend2("bbb",c);
        Thread th1 = new Thread(new Runnable() {public void run(){aaa2.bow(bbb2, deadlockflag, 10000);}});
        th1.start();
//Increment decrement in counter syncronized
        new Thread(new Runnable() {public void run(){bbb2.bow3(aaa2,deadlockflag, 10000);}}).start();
//	Fix the deadlock
//        try {
//        	th1.join();
//        }catch(InterruptedException ex) {
//        	
//        }
        new Thread(new Runnable() {public void run(){bbb2.bow(aaa2, 0, 10000);}}).start();
    }
}

