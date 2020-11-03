package com.neko.L4_SMTP;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
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

/**     利用 SMTP 协议 - 发送【带附件 邮件】
 * @author SolarisNeko 11/3/2020
 */
public class demo2_Send_MultiPart_Mail {
    public static void main(String[] args) throws MessagingException, IOException {

        // 1、设定【邮箱服务器 地址】
        String smtp = "smtp.qq.com";

        // 2、设定【帐号】 + 【密码 / 授权码】
        String username = "1417015340@qq.com";
        String password = "mbdadhfpkplkhhdf"; // qq邮箱, 需要打开 SMTP 服务, 获取【授权码】; 此处, 填写【SMTP 授权码】

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

        // 5、设置【带附件的内容】
        MimeMultipart mimeMultipart = new MimeMultipart();
        // 5-1、添加 text - 文本内容
        MimeBodyPart text_Part = new MimeBodyPart();
        text_Part.setContent("文本内容", "UTF-8");
        mimeMultipart.addBodyPart(text_Part);
        // 5-2、添加 image
        File file = new File("C:\\Users\\14170\\OneDrive\\图片\\壁纸\\1.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MimeBodyPart image_Part = new MimeBodyPart();
        image_Part.setFileName("01.jpg");
        image_Part.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/octet-stream")));
        mimeMultipart.addBodyPart(image_Part);
        // 5-3、设置邮件内容
        mimeMessage.setContent(mimeMultipart);

        // 6、发送【邮件】
        Transport.send(mimeMessage); // Transport n. 运输

    }
}
