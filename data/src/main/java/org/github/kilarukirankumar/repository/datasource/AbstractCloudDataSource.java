package org.github.kilarukirankumar.repository.datasource;

import android.content.Context;

import org.github.kilarukirankumar.ApiEndpointConstants;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * AbstractCloudDataSource
 */

public abstract class AbstractCloudDataSource {

    private Context mContext;

    public AbstractCloudDataSource(final Context context) {
        mContext = context;
    }

    /**
     * Build the Retrofit object to consume REST services.
     *
     * @return {@link Retrofit} object.
     */
    private Retrofit buildRetrofit(final String endPoint) {
        OkHttpClient okHttpClient = CustomOkHttpClient.getOkHttpClient(mContext, getHeaders());
        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected abstract Map<String, String> getHeaders();

    protected Retrofit buildRetrofit() {
        return buildRetrofit(ApiEndpointConstants.MOVIESDB_ENDPOINT);
    }

}
