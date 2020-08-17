package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentMethodChoice extends AppCompatActivity {
    PaymentInformations paymentInformations;
    public void definePaymentMethod(View view){
        Button buttonChosen = (Button) view;
        String methodChosen = buttonChosen.getTag().toString();
        paymentInformations.setPaymentMethod(methodChosen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_choice);
        Intent intent = getIntent();
        paymentInformations = intent.getParcelableExtra("PaymentInfo");
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        textView.setText(paymentInformations.getValue()); //Setting the text to show the value chosen in the value assign screen
    }
}