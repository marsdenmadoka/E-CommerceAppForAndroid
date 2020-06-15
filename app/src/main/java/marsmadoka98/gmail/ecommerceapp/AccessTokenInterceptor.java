package marsmadoka98.gmail.ecommerceapp;

import android.util.Base64;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
//daraja we will use  confirmFinalOrder Activity in the Buyerspackage to work with our stk
public class AccessTokenInterceptor implements Interceptor {
    public AccessTokenInterceptor() {

    }

    @NotNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        String keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET;

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}

