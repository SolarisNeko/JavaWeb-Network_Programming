package com.neko.L3_UDP.demo2_deal_Exception;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**     L3_UDP - 接收方
 * @author SolarisNeko 11/2/2020
 */
public class Receiver {
    public static void main(String[] args)  {

        System.out.println("Host_Dog logined..");

        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket_receive = null;
        DatagramPacket datagramPacket_send = null;

        try {
            // 1、创建 Sokcet, 并且指定 【使用的 port】
            datagramSocket = new DatagramSocket(6666);

            // 2、接收 datagramPacket
            byte[] bufferedBytes = new byte[1024];
            datagramPacket_receive = new DatagramPacket(bufferedBytes, bufferedBytes.length);
            datagramSocket.receive(datagramPacket_receive); // 将接收的数据, 放到 L3_UDP DatagramPacket

            // 3、处理数据
            String data = new String(datagramPacket_receive.getData(), 0, datagramPacket_receive.getLength());
            // 如果在后2个参数 - 记录 offset, length， 则会填满该 datagrampacket
            System.out.println("Host_Cat: " + data);

            // 4、发送反馈 Host_Cat(Client)
            data = "I have received it.";
            byte[] receivedData = data.getBytes();
            datagramPacket_send = new DatagramPacket(
                    receivedData,
                    receivedData.length,
                    InetAddress.getByName("10.10.227.252"),
                    9999
            );
            datagramSocket.send(datagramPacket_send);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close
            datagramSocket.close();
        }



    }
}
