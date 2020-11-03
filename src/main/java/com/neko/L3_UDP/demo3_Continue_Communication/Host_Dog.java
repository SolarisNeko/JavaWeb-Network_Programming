package com.neko.L3_UDP.demo3_Continue_Communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**     L3_UDP - 被连接方
 * @author SolarisNeko 11/2/2020
 */
public class Host_Dog {
    public static void main(String[] args)  {

        System.out.println("Host_Dog logined..");

        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket_receive = null;
        DatagramPacket datagramPacket_send = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // 1、创建 Sokcet, 并且指定 【使用的 port】
            datagramSocket = new DatagramSocket(6666);

            while (true) {
                /**
                 * 接收
                 * */
                System.out.println("Waiting opposite..."); // 等待对方

                // 2、持续 接收 datagramPacket（L3_UDP 包）
                byte[] bufferedBytes = new byte[1024];
                datagramPacket_receive = new DatagramPacket(bufferedBytes, bufferedBytes.length);
                datagramSocket.receive(datagramPacket_receive); // 将接收的数据, 放到 L3_UDP DatagramPacket
                // 3、处理数据
                String data = new String(datagramPacket_receive.getData(), 0, datagramPacket_receive.getLength());
                // 如果在后2个参数 - 记录 offset, length， 则会填满该 datagrampacket
                System.out.println("Host_Cat: " + data);

                if (data.equals("bye")) {
                    System.out.println(Host_Dog.class.getSimpleName() + " 随着 cat 一起下线啦~");
                    break;
                }


                /**
                 * 发送
                 * */
                // 4、获取【输入的反馈】转换成 byte[]
                System.out.print("你要说: ");
                data = scanner.nextLine();
                byte[] receivedData = data.getBytes();

                // 5、发送【封装后的 DatagramPacket】
                datagramPacket_send = new DatagramPacket(
                        receivedData,
                        receivedData.length,
                        InetAddress.getByName("10.10.227.252"),
                        9999
                );
                datagramSocket.send(datagramPacket_send);

                if ("bye".equals(data)) {
                    System.out.println(Host_Dog.class.getSimpleName() + " 下线啦~");
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
