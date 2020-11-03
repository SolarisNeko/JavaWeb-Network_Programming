package com.neko.TCP.demo2_Bothway_IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**     TCP - Server_File has I/O
 * @author SolarisNeko 11/2/2020
 */
public class Server_IO {
    public static void main(String[] args) throws IOException {

        System.out.println("Server_File launches...  ");
        // 1、 Server_File
        ServerSocket serverSocket = new ServerSocket(6666);

        // 2、 get Client_File
        Socket accept = serverSocket.accept();

        // 3、Client_File - Input
        InputStream inputStream = accept.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String data = dataInputStream.readUTF();

        System.out.println("Client_Basic : " + data);

        // 4、Client_File - Output
        accept.shutdownInput();
        OutputStream outputStream = accept.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        data = "Server_File has received Client_File's data !";
        dataOutputStream.writeUTF(data);

        // 5、Close
        dataOutputStream.close();
        outputStream.close();
        dataInputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }
}
