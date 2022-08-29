package com.ooweat.common;

import com.ooweat.entity.SmtpBody;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

public class SMTP {
    public static void gmail(SmtpBody smtpBody) {
        final String user = smtpBody.getSender();   //발신메일
        final String name = smtpBody.getNickName();                //전송 시 별칭으로 전송
        final String password = smtpBody.getPassword();   //Gmail 에서 암호화된 키
        final String port = "587";
        final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        StringBuffer sb = new StringBuffer();

        //메시징 예외처리
        try {
            sb.append(smtpBody.getContent());

            String html = sb.toString();


            //메시지 포맷을 담기위해 생성
            MimeMessage message = new MimeMessage(session);
            // 메일 콘텐츠 설정
            Multipart mParts = new MimeMultipart();
            MimeBodyPart mTextPart = new MimeBodyPart();
            //MimeBodyPart mFilePart = null;

            //이메일 헤더 설정
            message.setHeader("content-Type", "text/html");

            //발신자 설정
            message.setFrom(new InternetAddress(user, name, bodyEncoding));

            //운영
            //수신자메일
            InternetAddress[] toReveiveGroup = {
                    new InternetAddress("receive01@gmail.com"),
                    new InternetAddress("receive02@gmail.com"),
            };

            InternetAddress[] toTestGroup = {
                    new InternetAddress("test01@gmail.com"),
                    new InternetAddress("test02@gmail.com"),
            };

            //Parameter로 넘긴 mail
            InternetAddress[] toTarget = {new InternetAddress(smtpBody.getReceiver())};
            //고정된 참조
            InternetAddress[] toCCAddr = {new InternetAddress("ccEmail@gmail.com")};

            //고정된 값이 필요할 경우에 사용
            if(Util.stringUtil(smtpBody.getReceiver()).in("reveiveGroup", "test")){
                switch (smtpBody.getReceiver()) {
                    case "reveiveGroup":
                        message.addRecipients(Message.RecipientType.TO, toReveiveGroup);
                        break;
                    case "test":
                        message.addRecipients(Message.RecipientType.TO, toTestGroup);
                        break;
                    default:
                        message.addRecipients(Message.RecipientType.CC, toCCAddr); //참조
                        break;
                }
            }else{
                message.addRecipients(Message.RecipientType.TO, toTarget);
            }

            //제목
            message.setSubject(smtpBody.getTitle()); //메일 제목

            // 메일 콘텐츠 - 내용
            mTextPart.setText(html, bodyEncoding, "html");
            mParts.addBodyPart(mTextPart);

            // 메일 콘텐츠 설정
            message.setContent(mParts);

            // MIME 타입 설정
            MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(MailcapCmdMap);

            //send
            Transport.send(message); //javax.mail.Transport임

            //전송 후, Console 확인용
            System.out.println(Util.logUtil().now() + "전송완료");

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
