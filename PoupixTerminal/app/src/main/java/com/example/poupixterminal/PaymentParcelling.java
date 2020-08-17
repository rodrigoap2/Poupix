package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PaymentParcelling extends AppCompatActivity {
    private PaymentInformations paymentInformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_parcelling);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        textView.setText(this.paymentInformations.getValue()); //Setting the text to show the value chosen in the value assign screen
    }
}