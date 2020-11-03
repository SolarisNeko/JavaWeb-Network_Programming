package com.neko.L2_TCP.demo3_file_tcp.Buffered;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * L2_TCP - Server_DeadLoop 接收文件（缓冲版）
 * <p>
 * ps: Server_DeadLoop、Client_Once 都 I/O 同向
 *
 * @author SolarisNeko 11/2/2020
 */
public class Server_File_Buffered {
    public static void main(String[] args) throws IOException {

        System.out.println("Server_File launches...  ");
        // 1、 Server_File
        ServerSocket serverSocket = new ServerSocket(6666);

        // 2、 get Client_File
        Socket accept = serverSocket.accept();

        // 3、 get InputStream
        InputStream inputStream = accept.getInputStream();

        // 4、 规定输出 File + 进行输出（每次/byte）
        File image = new File("H:\\Image_Output\\output.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(image);

        // 5-1、建立【缓冲输出流】 - 输出文件快
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        // 5-2、建立【读取 缓冲池】
        byte[] bytes = new byte[1024];

        int length = inputStream.read(bytes); // read = int ASCII
        while (length != -1) {
            bufferedOutputStream.write(bytes, 0, length);
            length = inputStream.read(bytes);
        }

        // 下载完毕, 回应对方
        accept.shutdownInput();
        OutputStream outputStream = accept.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("你上传的图片成功！ bye~");


        // Close
        dataOutputStream.close();
        outputStream.close();

        bufferedOutputStream.close();
        fileOutputStream.close();
        inputStream.close();

        accept.close();
        serverSocket.close();

    }
}
