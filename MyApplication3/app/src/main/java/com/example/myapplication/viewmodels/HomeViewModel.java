package com.example.myapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.CartItem;
import com.example.myapplication.models.Product;
import com.example.myapplication.repositories.CartRepo;
import com.example.myapplication.repositories.HomeRepo;

import java.util.List;

public class HomeViewModel extends ViewModel  {
    HomeRepo homeRepo=new HomeRepo();
      CartRepo cartRepo=new CartRepo();


    MutableLiveData<Product>mutableProduct=new MutableLiveData<>();
    public LiveData<List<Product>> getProducts(){return homeRepo.getProducts();}
    public void setProduct(Product product)
    {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct()
    {
        return mutableProduct;
    }
public LiveData<List<CartItem>> getCart()
{
    return cartRepo.getCart();
}
public boolean addItemToCart(Product product)
{
    return cartRepo.addItemToCart(product);
}
public void removeItemFromCart(CartItem cartItem)
{
    cartRepo.removeItemFromCart(cartItem);
}
public void changeQuantity(CartItem cartItem,int quantity)
{
    cartRepo.changeQuantity(cartItem,quantity);
}
public LiveData<Double>getTotalPrice()
{
    return cartRepo.getTotalPrice();
}


}
