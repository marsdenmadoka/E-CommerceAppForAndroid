package marsmadoka98.gmail.ecommerceapp.Sellers;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import marsmadoka98.gmail.ecommerceapp.Buyers.MainActivity;
import marsmadoka98.gmail.ecommerceapp.R;

public class SellerHomeActivity extends AppCompatActivity {

    private  TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:

                    mTextMessage.setText(R.string.title_home);
                return true;

                case R.id.navigation_add:
                    Intent intentCategory = new Intent(SellerHomeActivity.this, SellersCategoryActivity.class);
                    startActivity(intentCategory);

                    return true;



                case R.id.navigation_logout:
                    final FirebaseAuth mAuth;
                    mAuth=FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent intentMain = new Intent(SellerHomeActivity.this, MainActivity.class);
                    intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMain);
                    finish();
                    return true;

            }

            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
