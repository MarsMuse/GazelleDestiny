package com.artisan.common.net.reactor.client;

import com.artisan.common.net.reactor.server.executor.ServerSocketThreadFactory;
import com.artisan.common.net.reactor.server.executor.ServerThreadPool;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

/**
 * reactor客户端模式
 *
 * @author xz man
 * @since 2021/3/15 18:41
 */
public class ReactorClient implements Runnable {
    private final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private String host;
    private int port;
    private CountDownLatch count = null;

    public ReactorClient(String host, int port, CountDownLatch count) {
        this.host = host;
        this.port = port;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("ReactorClient connecting...");
        Socket socket;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int loop = RANDOM.nextInt(500);

        BufferedReader reader;
        BufferedWriter writer;
        try {
            while (true) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()), 1024);
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 1024);
                if (--loop == 0) {
                    writer.write("bye");
                    System.out.println("Client 结束连接");
                } else {
                    writer.write(String.format("当前计数为%d\n", loop));
                }
                writer.flush();
                String message = reader.readLine();
                System.out.printf("获取到服务端的响应%s\n", message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != count) {
                count.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReactorClient rc = new ReactorClient("127.0.0.1", 8080, null);
        rc.run();
    }

    public static void parallelClient() throws InterruptedException {
        int clientSize = 12;
        CountDownLatch down = new CountDownLatch(clientSize);
        Executor executor = ServerThreadPool.getInstance();
        for(int i = 0 ;i<clientSize;i++){
            executor.execute(new ReactorClient("127.0.0.1", 8080, down));
        }
        down.await();
        System.out.println("客户端所有程序执行完成");
    }
}
