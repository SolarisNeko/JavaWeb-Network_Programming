package com.neko.L2_TCP.demo6_Server_Listener;

import com.neko.L2_TCP.demo4_ObjectStream_tcp.pojo.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author SolarisNeko 11/2/2020
 */
public class Client_Once {
    public static void main(String[] args)  {

        System.out.println("Client_Once launched..");
        Socket socket = null;
        OutputStream outputStream = null;
        Scanner scanner = null;

        try {
            socket = new Socket("10.10.227.252", 6666);

            // 1、键盘输入: 帐号&密码
            scanner = new Scanner(System.in);

            System.out.println("帐号：");
            String username = scanner.nextLine();
            System.out.println("密码：");
            String password = scanner.nextLine();

            // 2、封装【输入内容】成为【一个对象 - User】
            User user = new User(username, password);

            // 3、将【封装对象】发送给 Server_DeadLoop
            outputStream = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(user);


            // 4、获取反馈
            socket.shutdownOutput();
            InputStream inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            boolean loginSuccess = dataInputStream.readBoolean();
            if (loginSuccess) {
                System.out.println("登陆成功");
            } else {
                System.out.println("登录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close
            try {
                outputStream.close();
                socket.close();
                scanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }
}
