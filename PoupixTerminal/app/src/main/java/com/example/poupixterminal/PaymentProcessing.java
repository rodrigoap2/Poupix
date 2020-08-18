package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PaymentProcessing extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private CreditCardInfos creditCardInfos;
    private double storeCashback;

    public void processPayment(){

    }
    public void showPaymentStatus(){

    }
    public void getInfos(Intent intent){
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        this.creditCardInfos = intent.getParcelableExtra("CreditCardInfo");//Getting the creditCard info
        this.storeCashback = intent.getDoubleExtra("StoreCashback",0);//Getting the cashback info
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_processing);
        Intent intent = getIntent();
        getInfos(intent);
    }
}