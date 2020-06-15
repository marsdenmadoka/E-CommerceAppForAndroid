package marsmadoka98.gmail.ecommerceapp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
//Interceptors are mechanisms of the OkHttp library that can add, remove, or replace request headers.
//daraja we will use  confirmFinalOrder Activity in the Buyerspackage to work with our stk
public class AuthInterceptor implements Interceptor {
    private String mAuthToken;

    public AuthInterceptor(String authToken) {
        mAuthToken = authToken;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request  = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + mAuthToken)
                .build();
        return chain.proceed(request);
    }


}
