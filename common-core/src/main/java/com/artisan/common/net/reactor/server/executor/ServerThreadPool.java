package com.artisan.common.net.reactor.server.executor;

import java.util.concurrent.*;

/**
 * 服务端线程池
 *
 * @author zou yao
 * @since 2021/3/15 18:14
 */
public class ServerThreadPool {

    private static volatile Executor executor = null;
    public static Executor getInstance() {
        if (null == executor) {
            synchronized (ServerThreadPool.class) {
                if (null == executor) {
                    executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(4096),
                            new ServerSocketThreadFactory("server-socket"),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }
}
