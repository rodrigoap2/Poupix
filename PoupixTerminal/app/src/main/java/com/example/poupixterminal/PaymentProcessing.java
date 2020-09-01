package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PaymentProcessing extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private double storeCashback;

    public void processPayment(){
        //processPaymentStub
        //TODO Implement paymentApi connection method
        new CountDownTimer(2000, 1000) {
            public void onTick(long milissecondsUntilDone) {
            }

            public void onFinish() {
                showPaymentStatus("approved");
            }
        }.start();
        new CountDownTimer(5000, 1000) {
            public void onTick(long milissecondsUntilDone) {
            }

            public void onFinish() {
                goToStartPage();
            }
        }.start();
    }
    @Override//Disabling back button
    public void onBackPressed() { }

    public void goToStartPage(){
        Intent intent = new Intent(PaymentProcessing.this, MainActivity.class);
        startActivity(intent);
    }

    public void showPaymentStatus(String status){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_loader);
        progressBar.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.processingText);
        textView.setVisibility(View.INVISIBLE);
        if(status.equals("approved")){
            ConstraintLayout approvedLayout = (ConstraintLayout) findViewById(R.id.approvedLayout);
            approvedLayout.animate().setDuration(2000).alpha(1);
        }else if(status.equals("rejected")){
            //TODO Add rejected layout
        }
    }

    public void getInfos(Intent intent){
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        this.storeCashback = intent.getDoubleExtra("StoreCashback",0);//Getting the cashback info
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_processing);
        setTitle("");
        Intent intent = getIntent();
        getInfos(intent);
    }
}