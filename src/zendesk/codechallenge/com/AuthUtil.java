package zendesk.codechallenge.com;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

/**
 * @author Xinlei Bian
 * @version 1.0
 * @Date Nov 26, 2021
 * @Description Class to Store Authorization Information
 */
public class AuthUtil {

  /**
   * Email address for authorization
   */
  private static String email = "xinleibian@yahoo.com";
  /**
   * Token for authorization
   */
  private static String token = "zq6afHATeyiuwVpAzyk64Tf2VHaz6ZSCeIPhAsCo";
  private static String url;
  private static String subdomain;

  static {
    String f = "information.properties";
    Properties props = new Properties();
    try {
      props.load(new java.io.FileInputStream(f));
    } catch (IOException e) {
      System.out.println("[Failure] Fail to load properties file");
      e.getMessage();
    }
    email = props.getProperty("email");
    token = props.getProperty("token");
    subdomain = props.getProperty("subdomain");
    if (email == null || email.equals("")) {
      System.out.println("[Failure] Empty email address!");
    }
    if (token == null || token.equals("")) {
      System.out.println("[Failure] Empty token!");
    }
    if (subdomain == null || subdomain.equals("")) {
      System.out.println("[Failure] Empty subdomain!");
    }
    url = "https://" + subdomain + ".zendesk.com/api/v2/tickets.json";
  }

  /**
   * Encode email and token to Base64
   */
  public static String getTokenBase64() {
    StringBuilder originInput = new StringBuilder(email);
    originInput.append("/token:");
    originInput.append(token);
    return Base64.getEncoder()
        .encodeToString(originInput.toString().getBytes(StandardCharsets.UTF_8));
  }

  public static String getUrl() {
    return url;
  }
}
