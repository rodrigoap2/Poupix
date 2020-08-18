package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CardInsert extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private CreditCardInfos creditCardInfos;

    public CreditCardInfos getCreditCardInfos(){
        return new CreditCardInfos();
    }

    public void showMethodInfo(){
        hideCardInfo();
        new CountDownTimer(3900,100){
            public void onTick(long milissecondsUntilDone){
            }
            public void onFinish(){
                showTextView();
            }
        }.start();
        new CountDownTimer(9000,1000){
            public void onTick(long milissecondsUntilDone){
            }
            public void onFinish(){
                pagePassStub();
            }
        }.start();
    }

    public void showTextView(){
        String paymentMethod = paymentInformations.getPaymentMethod();
        TextView textView;
        if(paymentMethod.equals("debit")){
            textView = (TextView) findViewById(R.id.debitImage);
        }else {
            textView = (TextView) findViewById(R.id.creditImage);
        }
        textView.animate().alpha(0);
        textView.setVisibility(View.VISIBLE);
        textView.animate().setDuration(4000).alpha(1);
    }

    public void hideCardInfo(){
        ImageView cardImage = (ImageView) findViewById(R.id.payCardImage);
        cardImage.animate().setDuration(4000).alpha(0);
        TextView insertText = (TextView) findViewById(R.id.insertText);
        insertText.animate().setDuration(4000).alpha(0);
        Button button = (Button) findViewById(R.id.backButton);
        button.animate().setDuration(4000).alpha(0);
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
        showMethodInfo();
    }
}