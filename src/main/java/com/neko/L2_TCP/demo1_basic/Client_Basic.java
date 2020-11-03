package com.neko.L2_TCP.demo1_basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**     L2_TCP - Client_Basic
 * @author SolarisNeko 11/2/2020
 */
public class Client_Basic {
    public static void main(String[] args) throws IOException {
        // 1、创建 Socket
        Socket socket = new Socket("10.10.227.252", 6666);

        // 2、我们感受到的是【应用层】，向外【发送数据】 = 获取【输出流】
        OutputStream outputStream = socket.getOutputStream();

        // 3、构建data + 修饰[输出流] + 往[修饰流]装入data
        // 3.1 构建 data
        String data = "Hello~";
        // 3.2 DataOutputStream.writeUTF(String str) 帮我们封装好了【数据输出】, 只需要传入 String 即可
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        // 3.3 写出data
        dataOutputStream.writeUTF(data);

        // 4、关闭【各个流】 + 关闭 Socket & ServerSocket
        dataOutputStream.close();
        outputStream.close();
        socket.close();


    }
}
