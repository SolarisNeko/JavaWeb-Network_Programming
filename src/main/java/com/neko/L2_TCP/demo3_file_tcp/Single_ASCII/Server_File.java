package com.neko.L2_TCP.demo3_file_tcp.Single_ASCII;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**     L2_TCP - Server_DeadLoop 接收文件
 * @author SolarisNeko 11/2/2020
 */
public class Server_File {
    public static void main(String[] args) throws IOException {

        System.out.println("Server_File luanches..  ");
        // 1、 Server_File
        ServerSocket serverSocket = new ServerSocket(6666);

        // 2、 get Client_File
        Socket accept = serverSocket.accept();

        // 3、 get InputStream
        InputStream inputStream = accept.getInputStream();

        // 4、 规定输出 File + 进行输出（每次/byte）
        File image = new File("H:\\Image_Output\\output.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(image);

        int read = inputStream.read(); // read = int ASCII
        while (read != -1) {
            fileOutputStream.write(read);
            read = inputStream.read();
        }

        // 下载完毕, 回应对方
        accept.shutdownInput();
        OutputStream outputStream = accept.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("你上传的图片成功！ bye~");



        fileOutputStream.close();
        inputStream.close();
        dataOutputStream.close();
        outputStream.close();
        accept.close();
        serverSocket.close();

    }
}
