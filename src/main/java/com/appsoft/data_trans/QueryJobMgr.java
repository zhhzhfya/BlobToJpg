package com.appsoft.data_trans;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryJobMgr {

	private static Logger log = LoggerFactory.getLogger(QueryJobMgr.class);
	// 池中所保存的线程数，包括空闲线程。
	private final static int corePoolSize = 1;
	// 池中允许的最大线程数。
	private final static int maximumPoolSize = Runtime.getRuntime().availableProcessors()/2;
	// 当线程数大于核心线程时，此为终止前多余的空闲线程等待新任务的最长时间
	private final static long keepAliveTime = 2;
	
	public final static int queueSize = 10;
	// 执行前用于保持任务的队列5，即任务缓存队列
	private final static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(queueSize);

	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

	static {
		threadPoolExecutor.allowCoreThreadTimeOut(true);
	}

	public static void addJob(Runnable runner) {
		threadPoolExecutor.submit(runner);
	}

	public static int getQueueSize() {
		return workQueue.size();
	}
	public static void clearnQueue() {
		workQueue.clear();
	}
	public static void shutdown() {
		threadPoolExecutor.shutdown();
		log.info("threadPoolExecutor shutdown.");
	}
}
