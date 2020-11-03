package com.neko.L1_net_address;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**     InetAddress
 *  InetAddress 只由 IP Address 构成，不带 port。
 *
 * IP地址是由IP使用的32位或128位无符号数字，构建UDP和TCP协议的低级协议。
 * @author SolarisNeko 11/2/2020
 */
public class Demo1_InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        // InetAddress 默认构造器 - default method() 只能同一个package下可以使用
//        错误示范： InetAddress inetAddress = new InetAddress();

        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
//        InetAddress inetAddress = InetAddress.getByName("localhost");

        System.out.println(inetAddress); // Output: /127.0.0.1
        System.out.println(inetAddress.getHostName()); // Output: www.sublimetext.com <-- 地址对应的域名
        System.out.println(inetAddress.getAddress()); // Output: [B@1b6d3586
        System.out.println(inetAddress.getHostAddress()); // Output: 127.0.0.1



        // 2
        System.out.println();
        InetAddress inetAddress2 = InetAddress.getByName("Kazuki");

        System.out.println(inetAddress2); // Output: Kazuki/169.254.215.109
        System.out.println(inetAddress2.getHostName()); // Output: Kazuki <-- 本计算机名
        System.out.println(inetAddress2.getAddress()); // Output: [B@4554617c
        System.out.println(inetAddress2.getHostAddress()); // Output: 169.254.215.109

        // 3
        System.out.println();
        InetAddress inetAddress3 = InetAddress.getByName("www.baidu.com");

        System.out.println(inetAddress3); // www.baidu.com/14.215.177.39
        System.out.println(inetAddress3.getHostName()); // www.baidu.com
        System.out.println(inetAddress3.getAddress()); //  [B@74a14482
        System.out.println(inetAddress3.getHostAddress()); // 14.215.177.39
    }
}
