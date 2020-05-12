package marsmadoka98.gmail.ecommerceapp.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import marsmadoka98.gmail.ecommerceapp.Buyers.MainActivity;
import marsmadoka98.gmail.ecommerceapp.R;

public class SellerRegistrationActivity extends AppCompatActivity {
    private Button alreadyhaveaccount,registerButton;
   private EditText nameInput,phoneInput,emailInput,passwordInput,addressInput;
    private FirebaseAuth mAuth;
private ProgressDialog progressDialog;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);
        alreadyhaveaccount=findViewById(R.id.seller_already_have_account);

       mAuth=FirebaseAuth.getInstance();
        nameInput=findViewById(R.id.seller_name);
        phoneInput=findViewById(R.id.seller_phone);
        emailInput=findViewById(R.id.seller_email);
        passwordInput=findViewById(R.id.seller_password);
        addressInput=findViewById(R.id.seller_address);
        registerButton=findViewById(R.id.seller_register_btn);
        progressDialog = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Register();
            }
        });
        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerRegistrationActivity.this,SellerLoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void Register() {
final String name=nameInput.getText().toString();
final String phone = phoneInput.getText().toString();
final String email=emailInput.getText().toString();
final String password=passwordInput.getText().toString();
final String address=addressInput.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"email field cant be empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SellerRegistrationActivity.this,"password field required",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(name)){
        Toast.makeText(SellerRegistrationActivity.this,"name field required",Toast.LENGTH_SHORT).show();
    }
        if(TextUtils.isEmpty(phone)){
        Toast.makeText(SellerRegistrationActivity.this,"phone field required",Toast.LENGTH_SHORT).show();
    }
        if(TextUtils.isEmpty(address)){
        Toast.makeText(SellerRegistrationActivity.this,"address field required",Toast.LENGTH_SHORT).show();
    }
        else{

            progressDialog.setTitle("seller registration");
progressDialog.setMessage("we are registering you please wait");
progressDialog.setCanceledOnTouchOutside(false);
progressDialog.show();
mAuth.createUserWithEmailAndPassword(email,password).
        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if(task.isSuccessful()){

            final DatabaseReference rootRef;
            rootRef= FirebaseDatabase.getInstance().getReference();
             String sid=mAuth.getCurrentUser().getUid();

            HashMap <String,Object> sellerMap = new HashMap<>();
            sellerMap.put("sid",sid);//make sure to use this
            sellerMap.put("name",name);
            sellerMap.put("email",email);
            sellerMap.put("password",password);
            sellerMap.put("phone",phone);
            sellerMap.put("address",address);

            rootRef.child("Sellers").child(sid).updateChildren(sellerMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    Toast.makeText(SellerRegistrationActivity.this, "registered successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SellerRegistrationActivity.this,SellerHomeActivity.class);
                    startActivity(intent);
                }
            });



        }else{
            progressDialog.dismiss();
            String Error = task.getException().toString();
            Toast.makeText(SellerRegistrationActivity.this,"Error :"+Error,Toast.LENGTH_LONG).show();
        }

    }
});

        }


    }
}
