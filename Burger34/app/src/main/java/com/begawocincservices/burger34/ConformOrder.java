package com.begawocincservices.burger34;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConformOrder extends AppCompatActivity {

    TextView totalAmount, productQuantity_01, productQuantity_02, productQuantity_03, productQuantity_04, productQuantity_05,
            productPrice_01, productPrice_02, productPrice_03, productPrice_04, productPrice_05,
            productName01, productName02, productName03, productName04, productName05;
    TextView userName, userAddress, userNumber;
    EditText coupon_code_input;
    android.app.AlertDialog.Builder builder;
//    TextInputEditText coupon_code_input;
    CheckBox codCheckBox;
    Button placeOrder, cancelOrder, applyCouponCode;

    Firebase url;

    static int deliveryPrice = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_order);

//      firebase
        Firebase.setAndroidContext(this);
        url = new Firebase("https://burger-34.firebaseio.com/orders");


//      find view by id
        builder = new android.app.AlertDialog.Builder(ConformOrder.this);
        totalAmount = findViewById(R.id.priceTotalPaid);
        userName = findViewById(R.id.name);
        userAddress = findViewById(R.id.address);
        userNumber = findViewById(R.id.number);
        placeOrder = findViewById(R.id.conformBtn);
        cancelOrder = findViewById(R.id.cancelBtn);
        coupon_code_input = findViewById(R.id.coupon_code_input);
        applyCouponCode = findViewById(R.id.applyCouponCode);
        codCheckBox = findViewById(R.id.codCheckBox);

//        product
        productName01 = findViewById(R.id.productName01);
        productName02 = findViewById(R.id.productName02);
        productName03 = findViewById(R.id.productName03);
        productName04 = findViewById(R.id.productName04);
        productName05 = findViewById(R.id.productName05);

        productQuantity_01 = findViewById(R.id.productQuantity_01);
        productQuantity_02 = findViewById(R.id.productQuantity_02);
        productQuantity_03 = findViewById(R.id.productQuantity_03);
        productQuantity_04 = findViewById(R.id.productQuantity_04);
        productQuantity_05 = findViewById(R.id.productQuantity_05);

        productPrice_01 = findViewById(R.id.productTotalPrice_01);
        productPrice_02 = findViewById(R.id.productTotalPrice_02);
        productPrice_03 = findViewById(R.id.productTotalPrice_03);
        productPrice_04 = findViewById(R.id.productTotalPrice_04);
        productPrice_05 = findViewById(R.id.productTotalPrice_05);

//      setting user details
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userName_SP = sharedPreferences.getString("userName", "");
        userName.setText(userName_SP);

        String userNumber_SP = sharedPreferences.getString("userNumber", "");
        userNumber.setText(userNumber_SP);

        String userAddress_SP = sharedPreferences.getString("userAddress", "");
        userAddress.setText(userAddress_SP);

//      Setting product details
//      for product 01
        final SharedPreferences sharedPreferences_01 = getSharedPreferences("data_product_01", Context.MODE_PRIVATE);
        int qtyEntered_01 = sharedPreferences_01.getInt("quantity_01", 0);
        String quantity_01 = String.valueOf(qtyEntered_01);
        productQuantity_01.setText(quantity_01);

        final int prd_01_amt = sharedPreferences_01.getInt("price_01", 0);
        String ProductAmount_01 = String.valueOf(prd_01_amt);
        productPrice_01.setText(ProductAmount_01);

//      for product 02
        final SharedPreferences sharedPreferences_02 = getSharedPreferences("data_product_02", Context.MODE_PRIVATE);
        int qtyEntered_02 = sharedPreferences_02.getInt("quantity_02", 0);
        String quantity_02 = String.valueOf(qtyEntered_02);
        productQuantity_02.setText(quantity_02);

        final int prd_02_amt = sharedPreferences_02.getInt("price_02", 0);
        String ProductAmount_02 = String.valueOf(prd_02_amt);
        productPrice_02.setText(ProductAmount_02);

//      for product 03
        final SharedPreferences sharedPreferences_03 = getSharedPreferences("data_product_03", Context.MODE_PRIVATE);
        int qtyEntered_03 = sharedPreferences_03.getInt("quantity_03", 0);
        String quantity_03 = String.valueOf(qtyEntered_03);
        productQuantity_03.setText(quantity_03);

        final int prd_03_amt = sharedPreferences_03.getInt("price_03", 0);
        String ProductAmount_03 = String.valueOf(prd_03_amt);
        productPrice_03.setText(ProductAmount_03);

//      for product 04
        final SharedPreferences sharedPreferences_04 = getSharedPreferences("data_product_04", Context.MODE_PRIVATE);
        int qtyEntered_04 = sharedPreferences_04.getInt("quantity_04", 0);
        String quantity_04 = String.valueOf(qtyEntered_04);
        productQuantity_04.setText(quantity_04);

        final int prd_04_amt = sharedPreferences_04.getInt("price_04", 0);
        String ProductAmount_04 = String.valueOf(prd_04_amt);
        productPrice_04.setText(ProductAmount_04);

//      for product 05
        final SharedPreferences sharedPreferences_05 = getSharedPreferences("data_product_05", Context.MODE_PRIVATE);
        int qtyEntered_05 = sharedPreferences_05.getInt("quantity_05", 0);
        String quantity_05 = String.valueOf(qtyEntered_05);
        productQuantity_05.setText(quantity_05);

        final int prd_05_amt = sharedPreferences_05.getInt("price_05", 0);
        String ProductAmount_05 = String.valueOf(prd_05_amt);
        productPrice_05.setText(ProductAmount_05);

//        for total amount section
        final int totalPriceCal = prd_01_amt + prd_02_amt + prd_03_amt + prd_04_amt + prd_05_amt + deliveryPrice ;
        String priceFinal = totalPriceCal + "/-";
        totalAmount.setText(priceFinal);

//        coupon code section
        final int totalQty = qtyEntered_01 + qtyEntered_02 + qtyEntered_03 + qtyEntered_04 + qtyEntered_05;
        final String coupon = "GRAB10";

        applyCouponCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQty >= 10){
                    if (coupon_code_input.getText().toString().equalsIgnoreCase(coupon)) {
                        int totalPriceCal = prd_01_amt + prd_02_amt + prd_03_amt + prd_04_amt + prd_05_amt;
                        int couponApplyPrice = totalPriceCal - (10*totalPriceCal/100);
                        int finalCouponPrice = couponApplyPrice + deliveryPrice;
                        String priceFinal = finalCouponPrice + "/-";
                        totalAmount.setText(priceFinal);
                        Toast.makeText(ConformOrder.this, "Coupon Code Applied", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConformOrder.this, "Invalid Coupon Code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ConformOrder.this, "Atleast 10 Quantities require to use coupon code", Toast.LENGTH_SHORT).show();
                }
            }
        });
        productempty();
//        place order by message chat app feature
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codCheckBox.isChecked() ){
                    if (totalQty >= 5){
                        if (userName.getText().toString().equals("")) {
                            showDialogPopup();
                        } else if (userAddress.getText().toString().equals("")) {
                            showDialogPopup();
                        } else if (userNumber.getText().toString().equals("")) {
                            showDialogPopup();
                        } else {
                            sendMessageText();
                            clearData(sharedPreferences_01, sharedPreferences_02, sharedPreferences_03, sharedPreferences_04, sharedPreferences_05);
                        }
                    } else {
                        Toast.makeText(ConformOrder.this, "Atleast 5 Quantities require to place your order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ConformOrder.this, "Please select the delivery option as COD", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        cancel order
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConformOrder.this, cancledOrder.class);
                startActivity(intent);
                clearData(sharedPreferences_01, sharedPreferences_02, sharedPreferences_03, sharedPreferences_04, sharedPreferences_05);
                ConformOrder.this.finish();
            }
        });

    }

    private void productempty() {
        CharSequence t1= productQuantity_01.getText(),t2= productQuantity_02.getText(),t3= productQuantity_03.getText(),t4= productQuantity_04.getText(),t5= productQuantity_05.getText();
        int a=Integer.parseInt(t1.toString()),b=Integer.parseInt(t2.toString()),c=Integer.parseInt(t3.toString()),d=Integer.parseInt(t4.toString()),e=Integer.parseInt(t5.toString());

        if (a ==0) {
            productName01.setVisibility(View.GONE);
            productPrice_01.setVisibility(View.GONE);
            productQuantity_01.setVisibility(View.GONE);
        }
        if (b ==0) {
            productName02.setVisibility(View.GONE);
            productPrice_02.setVisibility(View.GONE);
            productQuantity_02.setVisibility(View.GONE);
        }if (c ==0) {
            productName03.setVisibility(View.GONE);
            productPrice_03.setVisibility(View.GONE);
            productQuantity_03.setVisibility(View.GONE);
        }if (d ==0) {
            productName04.setVisibility(View.GONE);
            productPrice_04.setVisibility(View.GONE);
            productQuantity_04.setVisibility(View.GONE);
        }if (e ==0) {
            productName05.setVisibility(View.GONE);
            productPrice_05.setVisibility(View.GONE);
            productQuantity_05.setVisibility(View.GONE);
        }
        if (a==0&&b==0&&c==0&&d==0&&e==0){
            Toast.makeText(this, "Please Add Some Item to your cart", Toast.LENGTH_SHORT).show();
        }


        }

    public boolean InternetConnection() throws InterruptedException,IOException{

        String Command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(Command).waitFor()==0;
    }





    //      use get text to send message and Mail
    private void sendMessageText() {
//        name
        String name = "\nName: " + userName.getText().toString().trim() + "\n";
//        address
        String address = "Address: " + userAddress.getText().toString().trim() + "\n";
//        number
        String number = "Number: " + userNumber.getText().toString().trim() + "\n\n";
//        product
        /*
        Padding setting
        Aloo Tikki Burger
        Mayonnaise Burger  .
        Schezwan Burger    .
        Paneer Burger      .
        Cheese Burger      .
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String datetime = "\n" + simpleDateFormat.format(date) + "\n";

        String product = "Product: ";
        String product_01 = "\n" + productName01.getText().toString().trim() + "        x " + productQuantity_01.getText().toString().trim();
        String product_02 = "\n" + productName02.getText().toString().trim() + "   x " + productQuantity_02.getText().toString().trim();
        String product_03 = "\n" + productName03.getText().toString().trim() + "       x " + productQuantity_03.getText().toString().trim();
        String product_04 = "\n" + productName04.getText().toString().trim() + "             x " + productQuantity_04.getText().toString().trim();
        String product_05 = "\n" + productName05.getText().toString().trim() + "            x " + productQuantity_05.getText().toString().trim();
//        total amount has to paid
        String totalAmountPaid = "\n" + "Total Amount: " + totalAmount.getText().toString().trim();
//====================================================================================================================================================================================
//        Final Order String String
        String userOrder = datetime + name + address + number + product + product_01 + product_02 + product_03 + product_04 + product_05 + totalAmountPaid;

        try {
            if (InternetConnection()) {
                orderPlace(userOrder);
            }
            else{
                builder.setMessage("Please check Internet Connection !")
                        .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });


                android.app.AlertDialog alert = builder.create();
                alert.setTitle("Error");
                alert.show();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//            open home activity



//====================================================================================================================================================================================
    }

    //method for order place via firebase
    private void orderPlace(String userOrder) {

            Firebase firebase = url.child("");
            firebase.push().setValue(userOrder);
            Toast.makeText(this, "Your Order is Placed", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ConformOrder.this, orderPlaced.class);
            startActivity(intent);
            ConformOrder.this.finish();


    }

    //  reset the sharedPreference
    public void clearData(SharedPreferences sharedPreferences_01, SharedPreferences sharedPreferences_02,
                          SharedPreferences sharedPreferences_03, SharedPreferences sharedPreferences_04, SharedPreferences sharedPreferences_05 ){
        sharedPreferences_01.edit().clear().apply();
        sharedPreferences_02.edit().clear().apply();
        sharedPreferences_03.edit().clear().apply();
        sharedPreferences_04.edit().clear().apply();
        sharedPreferences_05.edit().clear().apply();
    }

//    Alert Dialogue
    private void showDialogPopup() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Please Enter Your Details")
                .setPositiveButton("Enter Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ConformOrder.this, userInfo.class);
                        startActivity(intent);
                        ConformOrder.this.finish();
                    }
                })
                .create().show();
    }

//    open edit user info
    public void editInfo(View view) {
        Intent intent = new Intent(ConformOrder.this, userInfo.class);
        startActivity(intent);
    }

}