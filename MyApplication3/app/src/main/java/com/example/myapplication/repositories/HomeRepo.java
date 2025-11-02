package com.example.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.models.Information;
import com.example.myapplication.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeRepo {
    private MutableLiveData<List<Product>> mutableProductList;
    public LiveData<List<Product>> getProducts()
    {
        if(mutableProductList== null)
        {
            mutableProductList=new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList =new ArrayList<>();

        productList.add(new Product(UUID.randomUUID().toString(),"Peperoni Pizza","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/pizza.png?alt=media&token=fb88fbda-733a-46a8-a1fa-94c30cdee5d5",349,"A classic American taste! Relish the delectable flavor of Chicken Pepperoni, topped with extra cheese"));

        productList.add(new Product(UUID.randomUUID().toString(),"Veg Burger","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/pop_2.png?alt=media&token=a9d6dbd6-f334-45f0-92bd-ecc559fffd6a",99,"A plant-powered delight featuring a savory veggie patty crafted with black beans, quinoa, and mushrooms. Nestled in a toasted whole grain bun, it's topped with crisp lettuce, ripe tomatoes, and creamy avocado."));

        productList.add(new Product(UUID.randomUUID().toString(),"Veg Loaded Pizza","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/pop_3.png?alt=media&token=e93f2d83-8dd4-4593-977c-edeba5390edb",449,"Savor the goodness of our Veg Loaded Pizza, a tantalizing blend of fresh, colorful vegetables atop a perfectly baked crust. Bursting with flavor and topped with melty cheese, it's a delightful celebration of plant-based goodness in every bite!"));

        productList.add(new Product(UUID.randomUUID().toString(),"Ice Tea","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/Coffee%20Black.png?alt=media&token=6ba17b0a-9382-4daa-9083-b8f628dd3e05",149,"Quench your thirst with our Iced Tea. A harmonious blend of premium tea leaves brewed to perfection and chilled to icy coolness. Served over a cascade of refreshing ice, this classic beverage is the epitome of relaxation. "));

        productList.add(new Product(UUID.randomUUID().toString(),"Black Coffee","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/Coffee%20Black.png?alt=media&token=6ba17b0a-9382-4daa-9083-b8f628dd3e05",119,"Embrace the rich simplicity of our Black Coffee. A robust and aromatic brew that captivates with its deep, full-bodied flavor. Served black to highlight the pure essence of the coffee bean, each sip is a journey into the heart of true coffee bliss. "));

        productList.add(new Product(UUID.randomUUID().toString(),"Milk Coffee","https://firebasestorage.googleapis.com/v0/b/ak2101340158.appspot.com/o/Ice%20Tea.png?alt=media&token=389eeecf-8079-40c0-9dab-f55997bb72e6",189,"Experience the warmth of our Creamy Comfort Milk Coffee, where the bold character of premium coffee meets the velvety embrace of smooth, steamed milk. This harmonious blend creates a delightful balance of robust flavors and comforting creaminess."));


        mutableProductList.setValue(productList);
    }


}
