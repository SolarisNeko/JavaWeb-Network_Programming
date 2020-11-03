package com.neko.TCP.demo1_basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**     TCP - Server_Basic
 * @author SolarisNeko 11/2/2020
 */
public class Server_Basic {
    public static void main(String[] args) throws IOException {

        // 1、创建 ServerSocket, 指定【本服务器】开放的 Port
        ServerSocket serverSocket = new ServerSocket(6666);

        // 2、ServerSocket.接收 Client_Basic 的 Socket 连接
        Socket accept = serverSocket.accept();

        // 3、【应用层】用 InputStream 获取【网络传来的 data】
        InputStream inputStream = accept.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String data = dataInputStream.readUTF();

        System.out.println("Client_Basic : " + data);

        // 4、关闭【各个Stream】 + 关闭【Client_Basic/Server_Basic Socket】
        dataInputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }
}
