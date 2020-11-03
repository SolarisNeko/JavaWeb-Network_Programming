package com.neko.L4_SMTP;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 利用 SMTP 协议 - 发送【带附件 邮件】
 *
 * @author SolarisNeko 11/3/2020
 */
public class demo2_Send_MultiPart_Mail {
    public static void main(String[] args) throws MessagingException, IOException {

        // 1、设定【邮箱服务器 地址】
        String smtp = "smtp.qq.com";

        // 2、设定【帐号】 + 【密码 / 授权码】
        String username = "1417015340@qq.com";
        String password = "************"; // qq邮箱, 需要打开 SMTP 服务, 获取【授权码】; 此处, 填写【SMTP 授权码】

        // 3、构建【内容】
        Properties props = new Properties();
        // 3-1、表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        // 3-2、SMTP 服务器 域名
        props.put("mail.smtp.host", "smtp.qq.com");
        // 3-3、连接 SMTP 服务器的端口号 - port=465/587(qq)
        props.put("mail.smtp.port", "587");
        // 3-4、此处填写，写信人的账号
        props.put("mail.user", username);
        // 3-5、此处填写 16位 STMP口令
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
         *      Mime  n.  模拟
         * */
        // 1、构建【模拟消息】
        MimeMessage mimeMessage = new MimeMessage(session);

        // 2、设置【发送方】 - 发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        mimeMessage.setFrom(form);
        // 3、设置【接收方】 - 收件人
        InternetAddress to = new InternetAddress("3231576185@qq.com");
        mimeMessage.setRecipient(Message.RecipientType.TO, to);
        // 4、设置【邮件主题】 - 主题
        mimeMessage.setSubject("Test JavaMail", "UTF-8");

        // 5、设置【带附件的内容】
        // 5-1、初始化【MimeMultipart】
        Multipart multipart = new MimeMultipart();
        // 5-2、构建【正文部分】
        BodyPart text_Part = new MimeBodyPart();
        text_Part.setContent("hello", "text/html;charset=utf-8");
        multipart.addBodyPart(text_Part);
        // 5-3、构建【附件部分】
        BodyPart image_Part = new MimeBodyPart();
        // 5-3-1、获取 File 的 FileInputStream
        File file = new File("C:\\Users\\14170\\OneDrive\\图片\\壁纸\\1.jpg");
        String fileName = file.getName();
        FileInputStream fileInputStream = new FileInputStream(file);
        // 5-3-2、设置【附件名】 + 设置【附件-设置数据处理-数据处理对象-字节线性数据源(文件流, 传输格式)】
        image_Part.setFileName(fileName);
        image_Part.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/octet-stream")));
        multipart.addBodyPart(image_Part);
        // 5-4、设置【邮件内容】为【MimeMultiPart multipart（由多个部分构成）】
        mimeMessage.setContent(multipart);


        // 6、发送【邮件】
        Transport.send(mimeMessage); // Transport n. 运输

    }
}
