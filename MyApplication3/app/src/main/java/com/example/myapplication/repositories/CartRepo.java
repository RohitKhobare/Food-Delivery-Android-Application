package com.example.myapplication.repositories;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.R;
import com.example.myapplication.models.CartItem;
import com.example.myapplication.models.Product;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private  MutableLiveData<List<CartItem>> mutableCart=new MutableLiveData<>();
    private  MutableLiveData<Double> mutableTotalPrice=new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart()
    {
        if(mutableCart.getValue()==null)
        {
            initcart();
        }
        return mutableCart;
    }

    private void initcart() {
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }
    public boolean addItemToCart(Product product)
    {
        if(mutableCart.getValue()==null)
        {
            initcart();
        }
        List<CartItem>cartItemList=new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem: cartItemList)
        {
            if(cartItem.getProduct().getId().equals(product.getId()))
            {
                if(cartItem.getQuantity()==8)
                {
                    return false;
                }
                int index=cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemList.set(index,cartItem);

                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return true;
            }
        }
        CartItem cartItem=new CartItem(product,1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;

    }
public void removeItemFromCart(CartItem cartItem)
{
    if(mutableCart.getValue()==null)
    {
        return;
    }
    List<CartItem> cartItemList=new ArrayList<>(mutableCart.getValue());
    cartItemList.remove(cartItem);
    mutableCart.setValue(cartItemList);
    calculateCartTotal();

}

    public void changeQuantity(CartItem cartItem, int quantity) {
        if(mutableCart.getValue()==null)return;
        List<CartItem> cartItemList=new ArrayList<>(mutableCart.getValue());
        CartItem updatedItem=new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem),updatedItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();

    }
    public LiveData<Double> getTotalPrice()
    {
        if(mutableTotalPrice.getValue()==null)
        {
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }


public  void calculateCartTotal()
{
    if(mutableCart.getValue()==null) return;
    double  total=0.0;
    List<CartItem> cartItemList =mutableCart.getValue();
    for(CartItem cartItem:cartItemList)
    {
        total+=cartItem.getProduct().getPrice()*cartItem.getQuantity();
    }
     mutableTotalPrice.setValue(total);

}

}
