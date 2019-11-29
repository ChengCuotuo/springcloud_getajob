package cn.nianzuochen.getajob.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件的工具类
 * 使用 springboot 提供的 JavaMailSender 类发送邮件
 */
public class EmailUtil {

    public static boolean SendEmail(String address, String content) {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");

        // 发送服务器需要身份验证
//        props.setProperty("mail.smtp.auth", "true");

        // 设置邮件服务器主机名 使用126邮箱发送
        props.setProperty("mail.host", "smtp.126.com");

        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 使用 ssl 连接
        props.setProperty("mail.smtp.ssl.enable", "true");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        try {
            // 设置邮件主题
            msg.setSubject("\"职准准\"验证邮件");

            // 设置消息头
            msg.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");

            // 设置邮件内容
            msg.setText(content);
            // 设置发件人
            msg.setFrom(new InternetAddress("shubiaowoliquan@126.com"));

            Transport transport = session.getTransport();
            // 连接邮件服务器，第一个是邮箱，第二个是密码（是客户端授权码，不是邮箱登录密码）
            transport.connect("shubiaowoliquan@126.com", "shubiao123");

            // 发送邮件
            transport.sendMessage(msg, new Address[] { new InternetAddress(address) });

            // 关闭连接
            transport.close();

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
