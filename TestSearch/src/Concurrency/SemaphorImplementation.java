package Concurrency;


import java.util.concurrent.Semaphore;
 
public class SemaphorImplementation {
	// If equal 0 it will be deadloack.  If >0, e.g 10-no deallocks
    private static final int MAX_CONCURRENT_THREADS = 0;
    private final Semaphore projectAdminLOCK = new Semaphore(MAX_CONCURRENT_THREADS, true);
    
    public void projectStartTest() {
        for (int i = 0; i < 10; i++) {
            ProjectPerson person = new ProjectPerson();
            person.start();
        }
    }
    
    public class ProjectPerson extends Thread {
        @Override
        public void run() {
        	System.out.println("item="+this.getId());
            try {
                
                // Acquire Lock
                projectAdminLOCK.acquire();
            } catch (InterruptedException e) {
                System.out.println("received InterruptedException");
                return;
            }
            System.out.println("Thread " + this.getId() + " start using Project's resource - Acquire()");
            try {
                sleep(10);
            } catch (Exception e) {
                
            } finally {
                
                // Release Lock
                projectAdminLOCK.release();
            }
            System.out.println("Thread " + this.getId() + " stops using Project's resource -  Release()\n");
        }
    }
    
    public static void main(String[] args) {
        SemaphorImplementation test = new SemaphorImplementation();
        test.projectStartTest();
        
    }
}
