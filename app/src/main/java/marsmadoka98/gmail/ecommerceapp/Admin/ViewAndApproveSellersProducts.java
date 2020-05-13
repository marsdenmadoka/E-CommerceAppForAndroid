package marsmadoka98.gmail.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import marsmadoka98.gmail.ecommerceapp.Interface.ItemClickListener;
import marsmadoka98.gmail.ecommerceapp.Model.Products;
import marsmadoka98.gmail.ecommerceapp.R;
import marsmadoka98.gmail.ecommerceapp.ViewHolder.ProductViewHolder;

public class ViewAndApproveSellersProducts extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_approve_sellers_products);

        unverifiedProductsRef= FirebaseDatabase.getInstance().getReference("SellersProducts");
        recyclerView=findViewById(R.id.approve_sellers_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(unverifiedProductsRef.orderByChild("productState").equalTo("Not Approved"),Products.class)   //display not Approved orders only in the admin so that he can  approve them make sure to use the same spellings
                .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, final int position, @NonNull final Products model) {

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
                                AlertDialog.Builder bulder = new AlertDialog.Builder(ViewAndApproveSellersProducts.this);
                                bulder.setTitle("DO you want to Approve this Product for purchase");
                                bulder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if(position == 0){

                                            ChangeProductState(productID);

                                        }

                                        if(position == 1){

                                        }
                                    }
                                });
                    bulder.show();

                            }
                        });

//                        final  Products itemClick = model;
//                        holder.setItemClickListner(new ItemClickListener() {
//                            @Override
//                            public void onClick(View view, final int position, boolean isLongClick) {
//                                final String productID =itemClick.getPid();
//                                 CharSequence options [] = new CharSequence[]
//                                         {
//                                                 "Yes",
//                                                 "No"
//                                         };
//                                AlertDialog.Builder bulder = new AlertDialog.Builder(ViewAndApproveSellersProducts.this);
//                                bulder.setTitle("DO you want to Approve this Product for purchase");
//                                bulder.setItems(options, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                        if(position == 0){
//
//                                            ChangeProductState(productID);
//
//                                        }
//
//                                        if(position == 1){
//
//                                        }
//                                    }
//                                });
//                    bulder.show();
//
//                            }
//                        });



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

    private void ChangeProductState(String productID) {
unverifiedProductsRef.child(productID).child("productState").setValue("Approved") //change the product state from Not Approved to Approved
        .addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            Toast.makeText(ViewAndApproveSellersProducts.this, "item approved successful", Toast.LENGTH_SHORT).show();
        }
    }
});


    }
}
