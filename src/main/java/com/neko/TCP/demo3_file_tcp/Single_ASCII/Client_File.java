package com.neko.TCP.demo3_file_tcp.Single_ASCII;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**     TCP - Client_Once 上传文件
 * @author SolarisNeko 11/2/2020
 */
public class Client_File {
    public static void main(String[] args) throws IOException {

        System.out.println("Client_File luanches..");

        Socket socket = new Socket("10.10.227.252", 6666);

        OutputStream outputStream = socket.getOutputStream();

        // 将【本地图片】先抽取到【代码】中，然后通过 Socket 提供的 OutputStream 写出
        File image = new File("C:\\Users\\14170\\OneDrive\\图片\\壁纸\\01.jpg");
        FileInputStream fileInputStream = new FileInputStream(image);
        int read = fileInputStream.read();// 一个一个字节地读取, 返回 int 类型 ASCII
        while (read != -1) {
            outputStream.write(read);
            read = fileInputStream.read();
        }

        // 操作完毕, 关闭 Output, 打开 Input
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String s = dataInputStream.readUTF();

        System.out.println(s);

        outputStream.close();

    }
}
