package test.zendesk.codechallenge.com;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zendesk.codechallenge.com.AuthUtil;

/**
 * AuthUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Nov 28, 2021</pre>
 */
public class AuthUtilTest {

  String tempEmail;
  String tempToken;
  String tempSubdomain;

  /**
   * Save data stored in information.properties Change properties to test
   */
  @Before
  public void before() {
    String f = "information.properties";
    Properties props = new Properties();
    try {
      props.load(new java.io.FileInputStream(f));
    } catch (IOException e) {
      System.out.println("[Failure] Fail to load properties file");
      e.getMessage();
    }
    tempEmail = props.getProperty("email");
    tempToken = props.getProperty("token");
    tempSubdomain = props.getProperty("subdomain");
    Properties properties = new Properties();
    properties.put("email", "abc@a.com");
    properties.put("token", "token_test");
    properties.put("subdomain", "domainTest");
    try {
      properties.store(new FileOutputStream(f), "info");
    } catch (IOException e) {
      e.getMessage();
    }
  }

  /**
   * Restore previous data in information.properties
   */
  @After
  public void after() throws Exception {
    String f = "information.properties";
    Properties properties = new Properties();
    properties.put("email", tempEmail);
    properties.put("token", tempToken);
    properties.put("subdomain", tempSubdomain);
    try {
      properties.store(new FileOutputStream(f), "info");
    } catch (IOException e) {
      e.getMessage();
    }
  }

  /**
   * Method: getTokenBase64()
   */
  @Test
  public void testGetTokenBase64() {
    assertEquals(AuthUtil.getTokenBase64(), "YWJjQGEuY29tL3Rva2VuOnRva2VuX3Rlc3Q=");
  }

  /**
   * Method: getUrl()
   */
  @Test
  public void testGetUrl() throws FileNotFoundException {
    assertEquals(AuthUtil.getUrl(), "https://domainTest.zendesk.com/api/v2/tickets.json");
  }


} 
