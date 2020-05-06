package marsmadoka98.gmail.ecommerceapp;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText InputName,InputPhoneNumber,InputPassword;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);

        createAccountButton=findViewById(R.id.register_btn);
        InputName=findViewById(R.id.register_username_input);
        InputPhoneNumber=findViewById(R.id.register_phone_number_input);
        InputPassword=findViewById(R.id.register_password_input);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }
public void  Register(){
String name = InputName.getText().toString();
String phoneNo = InputPhoneNumber.getText().toString();
String password= InputPassword.getText().toString();

if(TextUtils.isEmpty(name)){
    Toast.makeText(this, "name can;t be empty", Toast.LENGTH_SHORT).show();

}
else if(TextUtils.isEmpty(phoneNo)){
        Toast.makeText(this, "phoneNo can;t be empty", Toast.LENGTH_SHORT).show();
    }

   else if(TextUtils.isEmpty(password)){
        Toast.makeText(this, "password can;t be empty", Toast.LENGTH_SHORT).show();
    }else{
progressDialog.setMessage("signing you in ...");
progressDialog.setCanceledOnTouchOutside(false);
progressDialog.show();

 validatePhoneNo(name,phoneNo,password);//we want to chect if the phone no number used to signup is already taken


}
}
   public void  validatePhoneNo(final String name, final String phoneNo, final String password){
       final DatabaseReference RootRef;
       RootRef = FirebaseDatabase.getInstance().getReference();

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
           {
               if (!(dataSnapshot.child("Users").child(phoneNo).exists())) //if phone no has not been used by any account
               {
                   HashMap<String, Object> userdataMap = new HashMap<>();
                   userdataMap.put("phone", phoneNo);
                   userdataMap.put("password", password);
                   userdataMap.put("name", name);

                   RootRef.child("Users").child(phoneNo).updateChildren(userdataMap)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task)
                               {
                                   if (task.isSuccessful())
                                   {
                                       Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                       progressDialog.dismiss();

                                       Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                       startActivity(intent);
                                   }
                                   else
                                   {
                                       progressDialog.dismiss();
                                       Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }
               else
               {
                   progressDialog.dismiss();
                   Toast.makeText(RegisterActivity.this, "This " + phoneNo + " already exists.", Toast.LENGTH_LONG).show();
                   Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_LONG).show();

                   Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                   startActivity(intent);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
   }

}
