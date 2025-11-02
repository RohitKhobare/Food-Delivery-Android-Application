package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ShopRowBinding;
import com.example.myapplication.models.Product;

public class  HomeListAdapter extends ListAdapter<Product, HomeListAdapter.HomeViewHolder>
{
    HomeInterface homeInterface;

    public HomeListAdapter(HomeInterface homeInterface) {

        super(Product.itemCallback);
        this.homeInterface=homeInterface;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding= ShopRowBinding.inflate(layoutInflater,parent,false);
        shopRowBinding.setHomeInterface(homeInterface);
        return new HomeViewHolder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product=getItem(position);
        holder.shopRowBinding.setProduct(product);
        holder.shopRowBinding.setProduct(getItem(position));
        holder.shopRowBinding.executePendingBindings();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder
    {
        ShopRowBinding shopRowBinding;

        public HomeViewHolder(ShopRowBinding binding) {
            super(binding.getRoot());
            this.shopRowBinding=binding;


        }
    }
    public interface HomeInterface
    {
        void addItem(Product product);
        void onItemClick(Product product);

    }

}

