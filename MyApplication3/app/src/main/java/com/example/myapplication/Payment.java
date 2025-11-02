package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.login.loginpage;
import com.example.myapplication.models.CartItem;
import com.example.myapplication.repositories.CartRepo;
import com.example.myapplication.viewmodels.HomeViewModel;
import com.example.myapplication.views.CartFragment;
import com.example.myapplication.views.HomeFragment;
import com.example.myapplication.views.MainActivity;
import com.google.android.gms.wallet.WalletConstants;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Payment extends AppCompatActivity  implements PaymentResultListener {
RadioButton razor,cod;

Button pay;
HomeViewModel homeViewModel;
CartRepo cartRepo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
razor=findViewById(R.id.razor);
cod=findViewById(R.id.cod);
pay=findViewById(R.id.pay);
pay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(razor.isChecked())
        {
            String samount=String.valueOf(CartFragment.value);
            double amount=Math.round(Double.parseDouble(samount)*100);
            Checkout checkout=new Checkout();
            checkout.setKeyID("rzp_test_ToZY9f4m8utNCU");
            checkout.setImage(R.drawable.foodie);

            JSONObject obj= new JSONObject();
            try {
                obj.put("Name","Foodie");
                obj.put("Description","Order Payment");
                obj.put("Theme.color","");
                obj.put("Amount",amount);
                obj.put("prefill.contact","9890170576");
                obj.put("prefill.email", "abc@gmail.com");
                checkout.open(Payment.this,obj);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        if(cod.isChecked())
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(Payment.this);
            builder.setMessage("Are you Sure You Want to Proceed?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Payment.this,Order.class));
                    finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    startActivity(new Intent(Payment.this, MainActivity.class));
                    finish();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
});

    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(Payment.this,"Payment Successful",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Payment.this,Order.class));
        finish();

    }


    public void onPaymentError(int i, String s) {

        Toast.makeText(Payment.this,"Payment Failed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Payment.this,failed.class));
        finish();
    }


}
