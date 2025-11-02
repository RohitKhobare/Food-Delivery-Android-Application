package com.example.myapplication.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Payment;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CartListAdapter;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.login.loginpage;
import com.example.myapplication.models.CartItem;
import com.example.myapplication.viewmodels.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    HomeViewModel homeViewModel;
    public static double value;





    FragmentCartBinding fragmentCartBinding;
    public CartFragment(){}



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCartBinding=FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView=view.findViewById(R.id.cartrecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CartListAdapter cartListAdapter=new CartListAdapter(this);
        recyclerView.setAdapter(cartListAdapter);
        homeViewModel=new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
            }
        });
        homeViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.totalprice.setText("Total: ₹"+aDouble.toString());
                value=aDouble;
            }
        });


            fragmentCartBinding.placeorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(value!=0) {
                        startActivity(new Intent(getActivity(), Payment.class));

                    }
                    else {
                        Toast.makeText(requireContext(), "Add something to cart", Toast.LENGTH_SHORT).show();

                    }
                }
            });


    }

    @Override
    public void deleteItem(CartItem cartItem) {
   homeViewModel.removeItemFromCart(cartItem);
        Snackbar.make(requireView(),cartItem.getProduct().getName()+" Deleted From Cart",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        homeViewModel.changeQuantity(cartItem,quantity);
    }


}