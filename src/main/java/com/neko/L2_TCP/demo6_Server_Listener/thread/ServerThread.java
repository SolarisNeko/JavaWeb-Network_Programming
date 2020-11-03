package com.neko.L2_TCP.demo6_Server_Listener.thread;

import com.neko.L2_TCP.demo4_ObjectStream_tcp.pojo.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SolarisNeko 11/2/2020
 */
public class ServerThread extends Thread {

    ServerSocket serverSocket = null;
    Socket accept = null;

    InputStream inputStream = null;
    OutputStream outputStream = null;

    public ServerThread(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {
        try {
            // 1、构建【ObjectInputStream】
            inputStream = accept.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);

            // 2、从【Stream】读取对象
            User user = (User) ois.readObject();
            System.out.println(user);

            // 3、进行反馈
            accept.shutdownInput();
            outputStream = accept.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            boolean flag = false;
            if ("admin".equals(user.getUsername()) && "123".equals(user.getPassword())) {
                flag = true;
            }

            dataOutputStream.writeBoolean(flag);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close
            try {
                inputStream.close();
                outputStream.close();

                // 【服务 thread】只需要关闭 Socket accept
                accept.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
