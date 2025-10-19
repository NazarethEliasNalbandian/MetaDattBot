package org.example.bot.utils;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    public static String get(String url) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
    }

    public static String post(String url, String body) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(body));
            CloseableHttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
    }

    public static String patch(String url, String body) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPatch request = new HttpPatch(url);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(body));
            CloseableHttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
    }
}
