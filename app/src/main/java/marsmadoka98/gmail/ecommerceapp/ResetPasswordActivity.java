package marsmadoka98.gmail.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPasswordActivity extends AppCompatActivity {

    private  String check= "";
    private TextView pageTitle,titleQuestions;
    private EditText phoneNumber,question1,question2;
    private Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);



        check=getIntent().getStringExtra("check");//the extra from settingsActivity amd mainActivity

    }

    protected void onStart()
    {
        super.onStart();


        if (check.equals("settings")) //if it comes from setting value in settingsActivity
        {

        }
        else if (check.equals("login")) //if it comes form login value in the logActivity
        {

        }
    }

}
