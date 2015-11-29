package org.gaopan;

public class TestVolatile {
	private ThreadA threadA = new ThreadA(this);
	private ThreadB threadB = new ThreadB(this);

	private boolean running = true;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public ThreadA getThreadA() {
		return threadA;
	}

	public ThreadB getThreadB() {
		return threadB;
	}

	public void exec() throws Exception {
		threadA.start();
		threadB.start();
		threadA.join();
		threadB.join();

		System.out.println("end");
	}

	public static void main(String[] args) throws Exception {
		TestVolatile test = new TestVolatile();
		test.exec();
	}

}

class ThreadA extends Thread {
	private TestVolatile parent;

	public ThreadA(TestVolatile parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void run() {
		while (parent.isRunning()) {
			try {
				Thread.sleep(1000);
				System.out.println("pulse");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println("ThreadA end");
	}

}

class ThreadB extends Thread {
	private TestVolatile parent;

	public ThreadB(TestVolatile parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			parent.setRunning(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("ThreadB end");
	}

}
