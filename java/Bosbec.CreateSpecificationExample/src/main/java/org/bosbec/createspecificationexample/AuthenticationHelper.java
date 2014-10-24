package org.bosbec.createspecificationexample;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class AuthenticationHelper {
    public static String authenticate(String username, String password) {
        try {
            URL baseUri = new URL(ConfigurationHelper.getApiBaseUrl());

            URL url = new URL(baseUri, "api/v2/authentication-tokens");

            HttpPost request = new HttpPost(url.toString());

            ContentType contentType = ContentType.create("application/json", "UTF-8");

            String body = new JSONObject()
                    .put("username", username)
                    .put("password", password)
                    .toString();

            StringEntity content = new StringEntity(body, contentType);

            request.setEntity(content);

            HttpClient client = HttpClients.createDefault();

            HttpResponse response = client.execute(request);

            String json = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));

            JSONObject authenticationToken = new JSONObject(json);

            return authenticationToken.getString("id");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String authenticate() {
        String username = "kim@bosbec.se";
        String password = "kalle";

        return authenticate(username, password);
    }
}