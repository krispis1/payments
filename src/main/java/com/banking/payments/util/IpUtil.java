package com.banking.payments.util;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class IpUtil {
    public static String extractCountry(String ip) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(String.format("http://ip-api.com/json/%s", ip))
                .build();
        Response response = client.newCall(request).execute();

        try {
            Gson gson = new Gson();
            Map responseMap = gson.fromJson(response.body().string(), Map.class);
            return (String) responseMap.get("country");
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        }
    }
}
