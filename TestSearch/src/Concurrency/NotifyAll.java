package Concurrency;

import java.util.Random;

public class NotifyAll {
	public class Drop {
	    // Message sent from producer
	    // to consumer.
	    private String message;
	    // True if consumer should wait
	    // for producer to send message,
	    // false if producer should wait for
	    // consumer to retrieve message.
	    private boolean empty = true;

	    public synchronized String take() {
	        // Wait until message is
	        // available.
	        while (empty) {
	            try {
	                wait();
	            } catch (InterruptedException e) {}
	        }
	        // Toggle status.
	        empty = true;
	        // Notify producer that
	        // status has changed.
	        notifyAll();
	        return message;
	    }

	    public synchronized void put(String message) {
	        // Wait until message has
	        // been retrieved.
	        while (!empty) {
	            try { 
	                wait();
	            } catch (InterruptedException e) {}
	        }
	        // Toggle status.
	        empty = false;
	        // Store message.
	        this.message = message;
	        // Notify consumer that status
	        // has changed.
	        notifyAll();
	    }
	}
	//The producer thread, defined in Producer, sends a series of familiar messages. The string "DONE" indicates that all messages have been sent. To simulate the unpredictable nature of real-world applications, the producer thread pauses for random intervals between messages.

	public class Producer implements Runnable {
	    private Drop drop;

	    public Producer(Drop drop) {
	        this.drop = drop;
	    }

	    public void run() {
	        String importantInfo[] = {
	            "Mares eat oats",
	            "Does eat oats",
	            "Little lambs eat ivy",
	            "A kid will eat ivy too"
	        };
	        Random random = new Random();

	        for (int i = 0;
	             i < importantInfo.length;
	             i++) {
	            drop.put(importantInfo[i]);
	            try {
	                Thread.sleep(random.nextInt(5000));
	            } catch (InterruptedException e) {}
	        }
	        drop.put("DONE");
	    }
	}
	//The consumer thread, defined in Consumer, simply retrieves the messages and prints them out, until it retrieves the "DONE" string. This thread also pauses for random intervals.

	public class Consumer implements Runnable {
	    private Drop drop;

	    public Consumer(Drop drop) {
	        this.drop = drop;
	    }

	    public void run() {
	        Random random = new Random();
	        for (String message = drop.take();
	             ! message.equals("DONE");
	             message = drop.take()) {
	            System.out.format("MESSAGE RECEIVED: %s%n", message);
	            try {
	                Thread.sleep(random.nextInt(5000));
	            } catch (InterruptedException e) {}
	        }
	    }
	}
	
	public class family implements Runnable {
		String s;
		public family(String s) {
			this.s=s;
		}
		public void run() {
			mama(s);
		}
		synchronized void mama(String s){sasha("11 months "+s);}
		synchronized void sasha(String s){System.out.println("SASHA!"+s);}
	}
	
	
	public class cluster {
		String s;
		public cluster(String s) {
			this.s=s;
		}
		synchronized void mama(String s){System.out.println("mama!"+s);sasha("11 months "+s);}
		synchronized void sasha(String s){System.out.println("SASHA!"+s);}
	}

	synchronized void mama(String s){System.out.println("mama!"+s);sasha("11 months "+s);}
	//synchronized void papa(String s){sasha("12 months "+s);}
	synchronized void sasha(String s){System.out.println("SASHA!"+s);}

    public static void main(String[] args) {
    	NotifyAll na = new NotifyAll();
    	NotifyAll na2 = new NotifyAll();
        Drop drop = na.new Drop();
//        family f1 = na.new family("aaa");
//        family f2 = na.new family("bbb");
//        cluster master=na.new cluster("master");
//        cluster slave=na.new cluster("slave");
//        new Thread(new Runnable() {public void run(){na.mama("aaa");}}).start();
//        new Thread(new Runnable() {public void run(){na.mama("bbb");}}).start();
//        (new Thread(f1)).start();
//        (new Thread(f2)).start();
        (new Thread(na.new Producer(drop))).start();
        (new Thread(na.new Consumer(drop))).start();
    }

}
