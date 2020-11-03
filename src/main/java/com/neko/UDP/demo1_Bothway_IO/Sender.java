package com.neko.UDP.demo1_Bothway_IO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**     UDP - 发送方
 * @author SolarisNeko 11/2/2020
 */
public class Sender {
    public static void main(String[] args) throws IOException {

        // 1、创建 Socket - 此处 port:6666 是【发送方】的【端口】 == 【使用的端口】
        DatagramSocket datagramSocket = new DatagramSocket(9999);

        // 2、发送数据
        String data = "Hello, Are you here ?";
        byte[] dataBytes = data.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(
                dataBytes,
                0,
                dataBytes.length,
                InetAddress.getByName("10.10.227.252"),
                6666
        );

        // 3、发送
        datagramSocket.send(datagramPacket);

        // 4、获取反馈 == 构建缓存 +
        byte[] bufferedBytes = new byte[1024];
        DatagramPacket datagramPacket_receive = new DatagramPacket(bufferedBytes, bufferedBytes.length);
        datagramSocket.receive(datagramPacket_receive);

        String receiveData = new String(datagramPacket_receive.getData(), 0, datagramPacket_receive.getLength());
        System.out.println(receiveData);

        // Close
        datagramSocket.close();

    }
}
