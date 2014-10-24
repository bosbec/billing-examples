package org.bosbec.createspecificationexample;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SpecificationsHelper {
    public static String list() {
        return list(AuthenticationHelper.authenticate());
    }

    public static String list(String authenticationToken) {
        try {
            URL baseUrl = new URL(ConfigurationHelper.getApiBaseUrl());
            URL url = new URL(baseUrl, "api/v2/specifications");

            HttpGet request = new HttpGet(url.toString());

            request.addHeader("X-Auth-Token", authenticationToken);

            HttpClient client = HttpClients.createDefault();

            HttpResponse response = client.execute(request);

            InputStream stream = response.getEntity().getContent();
            InputStreamReader reader = new InputStreamReader(stream);
            String json = IOUtils.toString(reader);

            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void create(
            DateTime periodStart,
            DateTime periodEnd,
            String notifyUrl)
    {
        create(periodStart, periodEnd, notifyUrl, AuthenticationHelper.authenticate());
    }

    public static void create(
            DateTime periodStart,
            DateTime periodEnd,
            String notifyUrl,
            String authenticationToken) {
        try {
            URL baseUrl = new URL(ConfigurationHelper.getApiBaseUrl());
            URL url = new URL(baseUrl, "api/v2/specifications");

            HttpPost request = new HttpPost(url.toString());

            ContentType contentType = ContentType.create("application/json", "UTF-8");

            String body = new JSONObject()
                    .put("periodStart", periodStart.toString())
                    .put("periodEnd", periodEnd.toString())
                    .put("notifyUrl", notifyUrl)
                    .toString();

            System.out.println(body);

            StringEntity content = new StringEntity(body, contentType);

            request.setEntity(content);

            request.addHeader("X-Auth-Token", authenticationToken);

            HttpClient client = HttpClients.createDefault();

            client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}