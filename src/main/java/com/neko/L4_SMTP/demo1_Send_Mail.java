package com.neko.L4_SMTP;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**     利用 SMTP 协议 - 发送【纯文本 邮件】
 * @author SolarisNeko 11/3/2020
 */
public class demo1_Send_Mail {
    public static void main(String[] args) throws MessagingException {

        // 1、设定【邮箱服务器 地址】
        String smtp = "smtp.qq.com";

        // 2、设定【帐号】 + 【密码 / 授权码】
        String username = "1417015340@qq.com";
        String password = "************"; // qq邮箱, 需要打开 SMTP 服务, 获取【授权码】; 此处, 填写【SMTP 授权码】

        // 3、连接 SMTP 服务器 的 465/587(qq) 端口
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        props.put("mail.user", username);
        // 此处填写16位STMP口令
        props.put("mail.password", password);

        // 4、构建【授权信息】，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // 5、获取【Session 会话实例】
        Session session = Session.getInstance(props, authenticator);

        // 6、设置【debug 模式】, 便于【调试】
        session.setDebug(true);


        /**
         * 【发送邮件】 功能
         *  需要封装【邮件信息】 - MimeMessage
         * */
        // 1、构建【模仿消息】
        MimeMessage mimeMessage = new MimeMessage(session);

        // 2、设置【发送方】 - 发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        mimeMessage.setFrom(form);
        // 3、设置【接收方】 - 收件人
        InternetAddress to = new InternetAddress("3231576185@qq.com");
        mimeMessage.setRecipient(Message.RecipientType.TO, to);
        // 4、设置【邮件主题】 - 主题
        mimeMessage.setSubject("Test JavaMail", "UTF-8");
        // 5、设置【邮件正文】 - 内容
        mimeMessage.setText("Hello~ JavaMail Testing...", "UTF-8");

        // 6、发送【邮件】
        Transport.send(mimeMessage); // Transport n. 运输

    }
}
