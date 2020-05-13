package marsmadoka98.gmail.ecommerceapp.Sellers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import marsmadoka98.gmail.ecommerceapp.Admin.ViewAndApproveSellersProducts;
import marsmadoka98.gmail.ecommerceapp.Buyers.MainActivity;
import marsmadoka98.gmail.ecommerceapp.Model.Products;
import marsmadoka98.gmail.ecommerceapp.R;
import marsmadoka98.gmail.ecommerceapp.ViewHolder.ProductViewHolder;

public class SellerHomeActivity extends AppCompatActivity {

    private  TextView mTextMessage;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;
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



        unverifiedProductsRef= FirebaseDatabase.getInstance().getReference("SellersProducts");

        recyclerView=findViewById(R.id.recycler_seller_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(unverifiedProductsRef.orderByChild("sid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()),Products.class)   //display not Approved orders to te seller so that he has the option to deleted it
                        .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, final int position, @NonNull final Products model) {
                        holder.txtProductState.setVisibility(View.VISIBLE);
                        holder.txtProductState.setText("state"+model.getProductState());
                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price = " + model.getPrice() + "$");

                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                final String productID =model.getPid();
                                CharSequence options [] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };
                                AlertDialog.Builder bulder = new AlertDialog.Builder(SellerHomeActivity.this);
                                bulder.setTitle("DO you want to delete this Product ");
                                bulder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if(position == 0){

                                           DeleteProduct(productID);

                                        }

                                        if(position == 1){

                                        }
                                    }
                                });
                                bulder.show();

                            }
                        });



                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    private void DeleteProduct(String productID) {

        unverifiedProductsRef.child(productID)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SellerHomeActivity.this, "item deleted successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
