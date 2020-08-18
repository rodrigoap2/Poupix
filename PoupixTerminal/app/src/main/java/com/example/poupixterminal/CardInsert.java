package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CardInsert extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private CreditCardInfos creditCardInfos;
    public CreditCardInfos getCreditCardInfos(){
        return new CreditCardInfos();
    }

    public void pagePassStub(){
        //Intent intent = new Intent(UserSearch.this,);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_insert);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        String userName = userInfos.getName();
        TextView greetingText = (TextView) findViewById(R.id.greetingText);
        greetingText.setText("Olá, " + userName);
        this.creditCardInfos = getCreditCardInfos();
        new CountDownTimer(3000,1000){
            public void onTick(long milissecondsUntilDone){
            }
            public void onFinish(){
                pagePassStub();
            }
        }.start();
    }
}