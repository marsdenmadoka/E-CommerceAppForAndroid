package marsmadoka98.gmail.ecommerceapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//daraja we will use  confirmfinalorder in the buyers to work with our stk push
public class AccessToken {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
