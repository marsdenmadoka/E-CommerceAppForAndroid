package marsmadoka98.gmail.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import marsmadoka98.gmail.ecommerceapp.Interface.ItemClickListener;
import marsmadoka98.gmail.ecommerceapp.R;

public class CartViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{ //view holder for CartActivity

    public TextView txtProductName, txtProductPrice, txtProductQuantity,txtTotalAmount;
    private ItemClickListener itemClickListner; //refere this interface to the interface package


    public CartViewHolder(View itemView)
    {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
    }

    @Override
    public void onClick(View view)
    {
        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListener itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }

}
