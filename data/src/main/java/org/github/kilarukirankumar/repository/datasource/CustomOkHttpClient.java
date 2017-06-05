package org.github.kilarukirankumar.repository.datasource;

import android.content.Context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * CustomOkHttpClient.
 */

public class CustomOkHttpClient {

    public static OkHttpClient getOkHttpClient(Context context,
                                               Map<String, String> headers,
                                               Cache cache) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        NoNetworkInterceptor noNetworkInterceptor = new NoNetworkInterceptor(context);
        TLSSocketFactory tlsSocketFactory = TLSSocketFactory.getInstance();

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(noNetworkInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .sslSocketFactory(tlsSocketFactory)
                .addNetworkInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    if (headers != null) {
                        Set<String> keys = headers.keySet();
                        for (String key :
                                keys) {
                            String value = headers.get(key);
                            if (value != null) {
                                builder.addHeader(key, value);
                            }
                        }
                    }
                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpClient getOkHttpClient(Context context, Map<String, String> headers) {
        return getOkHttpClient(context, headers, null);
    }
}
