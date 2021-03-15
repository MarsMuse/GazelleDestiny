package com.artisan.common.net.reactor.server.executor;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * 套接字的任务
 *
 * @author zou yao
 * @since 2021/3/15 16:32
 */
public class SocketTask implements Runnable {

    private static final String CLOSE_TAG = "bye";
    private Socket socket;

    public SocketTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String message;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            while (true) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()), 1024);
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 1024);
                message = reader.readLine();
                if (CLOSE_TAG.equals(message)) {
                    break;
                }
                System.out.printf("获取到客户端的消息%s\n", message);
                writer.write(String.format("成功获取到消息，当前时间：%s", LocalDateTime.now()));
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("SocketTask connect close");
        }

    }
}
