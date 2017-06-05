package org.github.kilarukirankumar.repository.datasource;

import android.content.Context;

import org.github.kilarukirankumar.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * NoNetworkInterceptor.
 */

public class NoNetworkInterceptor implements Interceptor {

    private Context mContext;

    public NoNetworkInterceptor(final Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            throw new IOException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
