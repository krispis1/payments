package com.banking.payments.util;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class CurrencyUtil {

    public enum Currency {
        EUR, USD
    }

    //currently not used, but for future payment calculations might be useful
    public static double convert(Currency from, Currency to, Double amount) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(String.format("https://api.apilayer.com/exchangerates_data/convert?to=%s&from=%s&amount=%f", to.toString(), from.toString(), amount))
                .addHeader("apikey", "bU38NLcRRbg2zMBZoyHJHNnkLOczX0l1")
                .build();
        Response response = client.newCall(request).execute();

        try {
            Gson gson = new Gson();
            Map responseMap = gson.fromJson(response.body().string(), Map.class);
            return (double) responseMap.get("result");
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        }
    }
}
