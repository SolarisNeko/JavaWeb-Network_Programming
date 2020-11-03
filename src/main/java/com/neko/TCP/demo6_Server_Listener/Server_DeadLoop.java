package com.neko.TCP.demo6_Server_Listener;

import com.neko.TCP.demo6_Server_Listener.thread.ServerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author SolarisNeko 11/2/2020
 */
public class Server_DeadLoop {
    public static void main(String[] args)   {

        System.out.println("Server_DeadLoop launches..");

        Socket accept = null;
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        // 记录【第n个 登陆者】
        AtomicInteger atomicInteger = new AtomicInteger(1);

        try {
            serverSocket = new ServerSocket(6666);

            while (true) {
                accept = serverSocket.accept();

                ServerThread serverThread = new ServerThread(accept);
                serverThread.start();

                System.out.println("这是访问的第" + atomicInteger.getAndIncrement() + " 个用户, IP Address = " + accept.getRemoteSocketAddress());

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close
            try {
                accept.close();
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
