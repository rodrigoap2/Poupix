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
    private double storeCashback;
    private boolean hasReturned = false;

    public void setHasReturned(boolean hasReturned) {
        this.hasReturned = hasReturned;
    }

    public PaymentInformations getPaymentInformations() {
        return paymentInformations;
    }

    public CreditCardInfos getCreditCardInfos(){
        return new CreditCardInfos();
    }

    public void showMethodInfo(){
        hideCardInfo();
        new CountDownTimer(3900,100){
            public void onTick(long milissecondsUntilDone){
            }
            public void onFinish(){
                if(!hasReturned)
                    showTextView();
            }
        }.start();
        new CountDownTimer(7000,1000){
            public void onTick(long milissecondsUntilDone){
            }
            public void onFinish(){
                if(!hasReturned)
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
    }

    @Override//Disabling back button
    public void onBackPressed() { }

    public void pagePassStub(){
        Intent intent = new Intent(CardInsert.this,PasswordDeclaration.class);
        intent.putExtra("PaymentInfo", this.paymentInformations);
        intent.putExtra("UserInfo", this.userInfos);
        intent.putExtra("StoreCashback", this.storeCashback);
        intent.putExtra("CreditCardInfo", this.creditCardInfos);
        startActivity(intent);
    }

    private View.OnClickListener backButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CardInsert.this, CpfDefine.class);
            intent.putExtra("PaymentInfo",getPaymentInformations());
            setHasReturned(true);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_insert);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        this.storeCashback = intent.getDoubleExtra("StoreCashback",0);//Getting the store cashback info
        String userName = userInfos.getName();
        TextView greetingText = (TextView) findViewById(R.id.greetingText);
        greetingText.setText("Olá, " + userName);
        this.creditCardInfos = getCreditCardInfos();
        showMethodInfo();
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonOnClick);
    }
}