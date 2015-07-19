package com.nobodyiam.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason on 7/19/15.
 */
public class ExecutorsUtil {
    public static ExecutorService newFixedThreadPool(int nThreads, int blockingQueueSize) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(blockingQueueSize));
    }
}
