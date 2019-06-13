package com.fontys.kwetter.utils;

import com.fontys.kwetter.domain.User;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
public class EmailUtils {

  @Resource(name = "mail/kwetter")
  Session mailSession;

  public void sendWelcomeEmail(User user) throws MessagingException {
    Message message = new MimeMessage(mailSession);
    message.setSubject("Verify email");
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
    message.setText(String.format("Welcome %s to Kwetter!", user.getUsername()));
    Transport.send(message);
  }
}
