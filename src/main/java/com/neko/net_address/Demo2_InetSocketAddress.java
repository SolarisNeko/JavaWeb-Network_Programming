package com.neko.net_address;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**     InetSocketAddress
 *  InetSocketAddress 由 InetAddress（IP 地址） + port（端口） 构成。
 *
 * 该类实现IP套接字地址（IP地址+端口号）它也可以是一对（主机名+端口号），在这种情况下将尝试解析主机名。
 * 如果解决方案失败，那么该地址被认为是未解决的，但在某些情况下仍可以使用，例如通过代理连接。
 *
 * @author SolarisNeko 11/2/2020
 */
public class Demo2_InetSocketAddress {
    public static void main(String[] args) {

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        System.out.println(inetSocketAddress); //  /127.0.0.1:6666
        System.out.println(inetSocketAddress.getHostName()); // Kazuki
        System.out.println(inetSocketAddress.getPort()); // 6666
        InetAddress address = inetSocketAddress.getAddress();
        System.out.println(address); // Kazuki/127.0.0.1

    }
}
