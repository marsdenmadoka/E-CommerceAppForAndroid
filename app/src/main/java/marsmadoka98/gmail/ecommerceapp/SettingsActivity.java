package marsmadoka98.gmail.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    private CircleImageView profileImageView;
    private EditText fullNameEditText, userPhoneEditText, addressEditText;
    private TextView profileChangeTextBtn,  closeTextBtn, saveTextButton;
    private Button securityQuestionBtn;

    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePrictureRef;
    private String checker = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");
         profileImageView = (CircleImageView) findViewById(R.id.settings_profile_image);
         fullNameEditText = (EditText) findViewById(R.id.settings_full_name);
         userPhoneEditText = (EditText) findViewById(R.id.settings_phone_number);
         addressEditText = (EditText) findViewById(R.id.settings_address);
         profileChangeTextBtn = (TextView) findViewById(R.id.profile_image_change_btn);
        closeTextBtn = (TextView) findViewById(R.id.close_settings_btn);

     

        }
        }