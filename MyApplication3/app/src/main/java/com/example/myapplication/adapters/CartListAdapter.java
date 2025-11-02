package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.CartRowBinding;
import com.example.myapplication.models.CartItem;
import com.example.myapplication.views.CartFragment;

public class CartListAdapter extends ListAdapter<CartItem,CartListAdapter.CartVH> {

private CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface=cartInterface;
    }

    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding=CartRowBinding.inflate(layoutInflater,parent,false);
        return new CartVH((cartRowBinding));
    }


    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();
    }

    class CartVH extends RecyclerView.ViewHolder{
        CartRowBinding cartRowBinding;

        public CartVH(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding=cartRowBinding;
            cartRowBinding.deleteproduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });
            cartRowBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    int quantity=position+1;
                    if(quantity==getItem(getAdapterPosition()).getQuantity())
                    {
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getAdapterPosition()),quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


    }
    public interface CartInterface
    {
       public void deleteItem(CartItem cartItem);
       void changeQuantity(CartItem cartItem,int quantity);
    }
}

