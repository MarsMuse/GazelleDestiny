package com.artisan.common.net.reactor.server;


import com.artisan.common.net.reactor.server.executor.ServerThreadPool;
import com.artisan.common.net.reactor.server.executor.SocketTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * reactor模式服务端程序
 *
 * @author xz man
 * @since 2021/3/15 15:31
 */
public class ReactorServer {

    private Integer port;

    public ReactorServer(Integer port) {
        this.port = port;
    }

    public void run() {
        ServerSocket serverSocket;
        Executor executor = ServerThreadPool.getInstance();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("ReactorServer start error: %s\n", e.getMessage());
            return;
        }
        System.out.println("ReactorServer starting...");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("ReactorServer accept socket");
                executor.execute(new SocketTask(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ReactorServer close...");
        }
    }

    public static void main(String[] args) {
        ReactorServer rs = new ReactorServer(8080);
        rs.run();
    }
}
