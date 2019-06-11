package com.fontys.kwetter.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.ejb.Stateful;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arnoud
 * @project kwetter-backend
 */
public class RecaptchaUtils {

  private static final Logger LOGGER = Logger.getLogger(RecaptchaUtils.class.getName());

  public String validateCaptcha(String response) {
    try {
      return getValidation(response);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.toString(), e);
      return null;
    }
  }

  private String getValidation(String response) throws Exception {
    String url = "https://www.google.com/recaptcha/api/siteverify?secret=6LdQWqgUAAAAAFebh8ZhbrzmETnMiQhcCf_b310k&response=" + response;
    HttpClient client = HttpClientBuilder.create().build();
    HttpPost request = new HttpPost(url);
    HttpResponse apiResponse = client.execute(request);

    try (BufferedReader rd = new BufferedReader(new InputStreamReader(apiResponse.getEntity().getContent()))) {
      StringBuffer result = new StringBuffer();
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      return (result.toString());
    } catch (Exception e) {
//      LOGGER.log(Level.SEVERE, e.toString(), e);
      e.printStackTrace();
      return null;
    }
  }
}
