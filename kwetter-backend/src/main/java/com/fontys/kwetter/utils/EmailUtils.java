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
    message.setText(String.format("Welcome to Kwetter! Please press the following link to verify your account! %s", this.generateVerifyUrl(user.getUuid())));
    Transport.send(message);
  }

  private String generateVerifyUrl(String uuid) {
    return String.format("http://localhost:4200/verify/%s", uuid);
  }
}
