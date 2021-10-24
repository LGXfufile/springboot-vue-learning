package com.xin.service.impl;

/*
@DATE 2021/10/23 22:58
@PACKAGE_NAME com.xin.service.impl
@USER A
*/

import com.xin.service.EmailAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailActionImpl implements EmailAction {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailProperties mailProperties;

    /**
     * 发送简单邮件
     * @param mailContent 内容
     * @param targetEailAddress 收件人地址
     */
    @Override
    public void sendSimpleMsg(String mailContent, String targetEailAddress) {
        String subject = "File Download sucess";
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(mailProperties.getUsername());
            simpleMailMessage.setTo(targetEailAddress);
            simpleMailMessage.setSentDate(new Date());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(mailContent);
            mailSender.send(simpleMailMessage);
            System.out.println("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
        }

    }

    /**
     *
     * @param mailContent 邮件内容
     * @param targetEailAddress  收件人地址
     * @param imgSrc 图片地址
     */
    @Override
    public void sendHtmlWithImg(String mailContent, String targetEailAddress,String imgSrc) {
        String subject = "File Download Sucess";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(mailProperties.getUsername());
            //谁接收
            helper.setTo(targetEailAddress);
            //邮件主题
            helper.setSubject(subject);
            //邮件内容   true 表示带有附件或html
            //邮件内容拼接
            helper.setSentDate(new Date());
            String targetContent = "<html><body>" + mailContent + "<img src=\'cid:" + imgSrc + "\'></img>" + "</body></html>";

//            String targetContent =
//                    "<html><body><img width='250px' src="+imgSrc+">" + mailContent
//                            + "</body></html>";
            System.out.println("targetContent:"+targetContent);
            helper.setText(targetContent,true);
            File file = new File(imgSrc);
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            helper.addInline(imgSrc,fileSystemResource);
            mailSender.send(message);
            System.out.println("邮件发送成功");
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
        }

    }
}
