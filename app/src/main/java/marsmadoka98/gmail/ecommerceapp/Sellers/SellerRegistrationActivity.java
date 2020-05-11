package marsmadoka98.gmail.ecommerceapp.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import marsmadoka98.gmail.ecommerceapp.R;

public class SellerRegistrationActivity extends AppCompatActivity {
    private Button alreadyhaveaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);
        alreadyhaveaccount=findViewById(R.id.seller_already_have_account);

        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerRegistrationActivity.this,SellerLoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
