package thread;

public class TestThreadSeriExec {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(()->{
			System.out.println("I'm " + Thread.currentThread().getName());
		},"T1");
		
		Thread t2 = new Thread(()->{
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I'm " + Thread.currentThread().getName());
		},"T2");
		
		Thread t3 = new Thread(()->{
			try {
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I'm " + Thread.currentThread().getName());
		},"T3");
		
		
		t1.start();
		t2.start();
		t3.start();
	}
}
