package com.neko.L2_TCP.demo3_file_tcp.Buffered;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * L2_TCP - Client_Once 上传文件（缓冲版）
 * <p>
 * ps: Server_DeadLoop、Client_Once 都 I/O 同向
 *
 * @author SolarisNeko 11/2/2020
 */
public class Client_File_Buffered {
    public static void main(String[] args) throws IOException {

        System.out.println("Client_File launches....");

        Socket socket = new Socket("10.10.227.252", 6666);

        OutputStream outputStream = socket.getOutputStream();

        // 1、将【本地图片】先抽取到【代码】中，然后通过 Socket 提供的 OutputStream 写出
        File image = new File("C:\\Users\\14170\\OneDrive\\图片\\壁纸\\01.jpg");
        FileInputStream fileInputStream = new FileInputStream(image);
        // 2-1、建立【缓冲读入流】 - 输入文件快
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        // 2-2、建立【读取 缓冲池】
        byte[] bytes = new byte[1024];

        // 3、缓冲式读取
        int length = bufferedInputStream.read(bytes); // 一次性读入填满 bytes 的空间, return 读取长度
        while (length != -1) {
            outputStream.write(bytes, 0, length);
            length = bufferedInputStream.read(bytes);
        }

        // 操作完毕, 关闭 Output, 打开 Input
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String s = dataInputStream.readUTF();

        System.out.println(s);


        // Close
        dataInputStream.close();
        inputStream.close();
        bufferedInputStream.close();

        fileInputStream.close();
        outputStream.close();

        socket.close();

    }
}
