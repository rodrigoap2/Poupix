package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentMethodChoice extends AppCompatActivity {
    public PaymentInformations paymentInformations;

    private View.OnClickListener choosePaymentMethod = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button buttonChosen = (Button) view;
            buttonChosen.setOnClickListener(this);
            String methodChosen = buttonChosen.getTag().toString();
            paymentInformations.setPaymentMethod(methodChosen);
            Intent intent = new Intent(PaymentMethodChoice.this, PaymentParcelling.class);
            intent.putExtra("PaymentInfo",paymentInformations);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_choice);
        setTitle("");
        Intent intent = getIntent();
        paymentInformations = intent.getParcelableExtra("PaymentInfo");
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        textView.setText(paymentInformations.getValue()); //Setting the text to show the value chosen in the value assign screen
        ConstraintLayout methodLayout = (ConstraintLayout) findViewById(R.id.methodConstraint);
        for(int i = 0; i < methodLayout.getChildCount(); i++){
            Button button = (Button) methodLayout.getChildAt(i);
            button.setOnClickListener(choosePaymentMethod); //Setting buttons onclickMethod
        }
    }
}