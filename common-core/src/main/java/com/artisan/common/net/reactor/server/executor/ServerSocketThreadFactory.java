package com.artisan.common.net.reactor.server.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 服务端线程工厂
 *
 * @author zou yao
 * @since 2021/3/15 18:29
 */
public class ServerSocketThreadFactory implements ThreadFactory {
    private static final String NAME_START = "[";
    private static final String POOL_NAME_DEFAULT = "xz-man-pool";
    private static final String NAME_END = "]";
    private static final String GAP_SYMBOL = "-";
    private final AtomicInteger threadNo = new AtomicInteger(0);
    private final String nameStart;

    public ServerSocketThreadFactory() {
        nameStart = NAME_START + POOL_NAME_DEFAULT + GAP_SYMBOL;
    }

    public ServerSocketThreadFactory(String poolName) {
        nameStart = NAME_START + poolName + GAP_SYMBOL;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = nameStart + threadNo.getAndIncrement() + NAME_END;
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(true);
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
