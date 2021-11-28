package zendesk.codechallenge.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Xinlei Bian
 * @version 1.0
 * @Date Nov 26, 2021
 * @Description Http utility class to GET request
 */
public class HttpUtil {

  /**
   * GET Request Json data form url with authorization
   *
   * @param requestUrl the url to request
   */
  public static String httpRequest(String requestUrl) throws IOException {
    StringBuffer buffer = new StringBuffer();
    URL url = new URL(requestUrl);
    HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
    httpUrlConn.setRequestMethod("GET");
    httpUrlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
    httpUrlConn.setRequestProperty("Authorization", "Basic " + AuthUtil.getTokenBase64());
    httpUrlConn.connect();

    InputStream inputStream = httpUrlConn.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String str = null;
    while ((str = bufferedReader.readLine()) != null) {
      buffer.append(str);
    }
    bufferedReader.close();
    inputStreamReader.close();
    inputStream.close();
    httpUrlConn.disconnect();
    return buffer.toString();
  }
}
