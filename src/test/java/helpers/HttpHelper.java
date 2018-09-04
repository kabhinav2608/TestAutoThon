package helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpHelper {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  public static Object doGetRequest(String url, Class className) throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = okHttpClient.newCall(request).execute();
    String responseObject = response.body().string();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper.readValue(responseObject, className);
  }

  public static String doGetRequest(String url) throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .build();
    Response response = okHttpClient.newCall(request).execute();
    return response.body().string();
  }

}
