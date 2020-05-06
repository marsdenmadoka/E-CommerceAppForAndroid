package marsmadoka98.gmail.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button joinNowButton,loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNowButton=findViewById(R.id.main_join_now_btn);
        loginButton=findViewById(R.id.main_login_btn);

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToRegister();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToLogin();
            }
        });
    }

    public void SendToRegister() {
        Intent RegisterIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(RegisterIntent);
    }

    public  void SendToLogin(){
        Intent LoginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(LoginIntent);
    }
}
