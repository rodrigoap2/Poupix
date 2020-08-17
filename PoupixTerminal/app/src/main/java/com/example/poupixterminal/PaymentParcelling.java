package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentParcelling extends AppCompatActivity {
    public PaymentInformations paymentInformations;

    private View.OnClickListener directPaymentMethod = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button buttonChosen = (Button) view;
            buttonChosen.setOnClickListener(this);
            boolean direct = true;
            paymentInformations.setDirectPayment(true);
            paymentInformations.setParcells(1);
            Intent intent = new Intent(PaymentParcelling.this, CpfDefine.class);
            intent.putExtra("PaymentInfo",paymentInformations);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_parcelling);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        textView.setText(this.paymentInformations.getValue()); //Setting the text to show the value chosen in the value assign screen
        Button directPayment = (Button) findViewById(R.id.directPayment);
        directPayment.setOnClickListener(this.directPaymentMethod);

    }
}