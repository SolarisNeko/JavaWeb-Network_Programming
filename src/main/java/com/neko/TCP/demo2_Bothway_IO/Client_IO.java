package com.neko.TCP.demo2_Bothway_IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**     TCP - Client_File has I/O
 * @author SolarisNeko 11/2/2020
 */
public class Client_IO {
    public static void main(String[] args) throws IOException {

        System.out.println("Client_File launches...");

        Socket socket = new Socket("10.10.227.252", 6666);

        OutputStream outputStream = socket.getOutputStream();

        // 3、Client_File - Output
        String data = "Hello~";
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(data);

        // 4、 Client_File - Input
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        System.out.println(dataInputStream.readUTF());

        // 5、Close
        dataInputStream.close();
        inputStream.close();
        dataOutputStream.close();
        outputStream.close();
        socket.close();


    }
}
