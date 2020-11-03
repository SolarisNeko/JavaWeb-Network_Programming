### 准备SMTP登录信息

假设我们准备使用自己的邮件地址`me@example.com`给小明发送邮件，已知小明的邮件地址是`xiaoming@somewhere.com`，发送邮件前，我们首先要确定作为MTA的邮件服务器地址和端口号。邮件服务器地址通常是`smtp.example.com`，端口号由邮件服务商确定使用25、465还是587。以下是一些常用邮件服务商的SMTP信息：

-   `QQ 邮箱`：SMTP服务器是 `smtp.qq.com` ，端口是`465 / 587`；
-   `163 邮箱`：SMTP服务器是 `smtp.163.com` ，端口是`465`；
-   `Gmail 邮箱`：SMTP服务器是 `smtp.gmail.com` ，端口是`465 / 587`。

>   通用 SMTP 端口 port = `465`

有了SMTP服务器的 域名 & 端口号，我们还需要 SMTP服务器 的登录信息，通常是使用`自己的邮件地址`作为`用户名`，`登录口令`是`用户口令 或者 一个独立设置的SMTP口令`。



## 1、引入依赖

首先，我们需要创建一个Maven工程，并把JavaMail相关的两个依赖加入进来：

```xml
<dependencies>
    <dependency>
        <groupId>javax.mail</groupId>
        <artifactId>javax.mail-api</artifactId>
        <version>1.6.2</version>
    </dependency>
    <dependency>
        <groupId>com.sun.mail</groupId>
        <artifactId>javax.mail</artifactId>
        <version>1.6.2</version>
    </dependency>
    ...
```



## 2、编写 Code

然后，我们通过JavaMail API连接到SMTP服务器上：

```java
// 服务器地址:
String smtp = "smtp.office365.com";
// 登录用户名:
String username = "jxsmtp101@outlook.com";
// 登录口令:
String password = "********";
// 连接到SMTP服务器587端口:
Properties props = new Properties();
props.put("mail.smtp.host", smtp); // SMTP主机名
props.put("mail.smtp.port", "587"); // 主机端口号
props.put("mail.smtp.auth", "true"); // 是否需要用户认证
props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
// 获取Session实例:
Session session = Session.getInstance(props, new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
});
// 设置debug模式便于调试:
session.setDebug(true);
```