package com.anders.experiment.多线程.并发模式;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 简单点说：Future模式就是把子任务交给线程池去执行，然后主线程去做一些其他的事，做完后通过future.get()去获取子任务完成的结果，如果子任务还没有完成，则主线程继续等待
 * 
 * @author Anders
 * 
 */
public class Future模式 {
	public static void main(String[] args) {
		// ------------------------------------invoke 1
		// ExecutorService exe1 = Executors.newCachedThreadPool();
		// ExecutorService exe1 = Executors.newFixedThreadPool(3);
		// ExecutorService exe1 = Executors.newSingleThreadExecutor();
		// ExecutorService exe1 = Executors.newSingleThreadScheduledExecutor();
		// ExecutorService exe1 = Executors.newScheduledThreadPool(3);

		// for (int i = 0; i < 5; i++)
		// {
		// exe1.execute(new LiftOff());
		// }
		// exe1.shutdown();

		// ------------------------------------invoke 2
		ExecutorService exe1 = Executors.newCachedThreadPool();
		ExecutorCompletionService<String> ecs = new ExecutorCompletionService<String>(exe1);
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// results.add(exe1.submit(new TaskWithResult(i)));
			if (i % 2 == 0) {
				results.add(ecs.submit(new TaskWithResult1(i)));
			} else {
				results.add(ecs.submit(new TaskWithResult(i)));
			}
		}
		System.out.println("线程池关闭");
		exe1.shutdown();
		// exe1.shutdownNow();

		for (Future<String> fs : results) {
			try {
				// 使用fs.isDone()进行判断，确定任务是否完成，fs.get()会一直等待任务完成
				// if (fs.isDone())
				// System.out.println(fs.get());//如果第一个线程慢，后面的线程会一直等
				System.out.println(ecs.take().get());// 哪个线程先结束，则该线程拿到值
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	public String call() {
		System.out.println(Thread.currentThread() + " : 休眠5秒");

		try {
			Thread.sleep(5000);
			System.out.println(this + "休眠5秒");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "result of TaskWithResult " + id;
	}
}

class TaskWithResult1 implements Callable<String> {
	private int id;

	public TaskWithResult1(int id) {
		this.id = id;
	}

	public String call() {
		System.out.println(Thread.currentThread() + " : 休眠10秒");

		try {
			Thread.sleep(10000);
			System.out.println(this + "休眠10秒");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "result of TaskWithResult " + id;
	}
}
