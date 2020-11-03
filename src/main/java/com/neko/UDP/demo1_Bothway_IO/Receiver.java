package com.neko.UDP.demo1_Bothway_IO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**     UDP - 接收方
 * @author SolarisNeko 11/2/2020
 */
public class Receiver {
    public static void main(String[] args) throws IOException {

        System.out.println("Host_Dog logined..");

        // 1、创建 Sokcet, 并且指定 【使用的 port】
        DatagramSocket datagramSocket = new DatagramSocket(6666);

        // 2、接收 datagramPacket
        byte[] bufferedBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bufferedBytes, bufferedBytes.length);
        datagramSocket.receive(datagramPacket); // 将接收的数据, 放到 UDP DatagramPacket

        // 3、处理数据
        String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            // 如果在后2个参数 - 记录 offset, length， 则会填满该 datagrampacket
        System.out.println("Host_Cat: " + data);

        // 4、发送反馈 Host_Cat(Client)
        data = "I have received it.";
        byte[] dataBytes = data.getBytes();
        DatagramPacket datagramPacket_send = new DatagramPacket(
                dataBytes,
                dataBytes.length,
                InetAddress.getByName("10.10.227.252"),
                9999
        );
        datagramSocket.send(datagramPacket_send);


        // Close
        datagramSocket.close();

    }
}
