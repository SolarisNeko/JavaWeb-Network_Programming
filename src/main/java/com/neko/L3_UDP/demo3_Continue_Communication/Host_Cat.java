package com.neko.L3_UDP.demo3_Continue_Communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**     L3_UDP - （先手）连接方
 * @author SolarisNeko 11/2/2020
 */
public class Host_Cat {
    public static void main(String[] args)  {

        System.out.println("Host_Cat logined.");

        DatagramSocket datagramSocket = null;

        try {
            // 1、创建 Socket - 此处 port:6666 是【发送方】的【端口】 == 【使用的端口】
            datagramSocket = new DatagramSocket(9999);
            Scanner scanner = new Scanner(System.in);

            while (true) {

                /**
                 * 发送
                 * */
                // 2、获取【输入的数据】的 byte[]
                System.out.print("你要说: ");
                String data = scanner.nextLine();
                byte[] dataBytes = data.getBytes();

                // 3、构建 UPD包 - DatagramPacket
                DatagramPacket datagramPacket = new DatagramPacket(
                        dataBytes,
                        0,
                        dataBytes.length,
                        InetAddress.getByName("10.10.227.252"),
                        6666
                );
                // 4、发送 DatagramPacket
                datagramSocket.send(datagramPacket);

                if ("bye".equals(data)) {
                    System.out.println(Host_Cat.class.getSimpleName() + " 下线啦~");
                    break;
                }

                /**
                 * 接收
                 * */
                // 5、构建缓存 -> 【获取反馈】
                System.out.println("Waiting opposite..."); // 等待对方

                byte[] bufferedBytes = new byte[1024];
                DatagramPacket datagramPacket_receive = new DatagramPacket(bufferedBytes, bufferedBytes.length);
                datagramSocket.receive(datagramPacket_receive);

                String receiveData = new String(datagramPacket_receive.getData(), 0, datagramPacket_receive.getLength());
                System.out.println("Dog: " + receiveData);

                if (receiveData.equals("bye")) {
                    System.out.println(Host_Cat.class.getSimpleName() + " 随着 dog 一起下线啦~");
                    break;
                }

            }


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
