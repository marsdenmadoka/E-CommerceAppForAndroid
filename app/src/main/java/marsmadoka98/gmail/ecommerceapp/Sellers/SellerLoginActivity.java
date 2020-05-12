package marsmadoka98.gmail.ecommerceapp.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import marsmadoka98.gmail.ecommerceapp.R;

public class SellerLoginActivity extends AppCompatActivity {
    private TextView loginEmail,loginpassword;
    private Button loginButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        loginEmail=findViewById(R.id.seller_login_name);
        loginpassword=findViewById(R.id.seller_login_password);
        loginButton=findViewById(R.id.seller_login_btn);


        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowLogin();
            }
        });

    }


    private  void AllowLogin(){

        String email=loginEmail.getText().toString();
        String password=loginpassword.getText().toString();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(SellerLoginActivity.this, "Email required", Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SellerLoginActivity.this, "Password required", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setTitle("login you in");
            progressDialog.setMessage("Authenticating user please wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(SellerLoginActivity.this, "signed in success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);
                                startActivity(intent);

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(SellerLoginActivity.this, "Error" + message, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

}
