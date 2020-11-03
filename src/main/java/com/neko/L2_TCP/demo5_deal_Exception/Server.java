package com.neko.L2_TCP.demo5_deal_Exception;

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
public class Server {
    public static void main(String[] args)   {

        System.out.println("Server_DeadLoop launches..");

        Socket accept = null;
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            serverSocket = new ServerSocket(6666);
            accept = serverSocket.accept();

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

                accept.close();
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
