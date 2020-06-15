package marsmadoka98.gmail.ecommerceapp;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
//Handling API calls
//daraja we will use  confirmFinalOrder Activity in the Buyerspackage to work with our stk
public interface STKPushService {
    @POST("mpesa/stkpush/v1/processrequest")
    Call<STKPush> sendPush(@Body STKPush stkPush);

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();


}
