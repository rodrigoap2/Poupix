package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentParcelling extends AppCompatActivity {
    private String parcelNumber = "0";
    private int maxParcels = 12;
    private int minParcels = 0;

    public int getMaxParcels(){
        return this.maxParcels;
    }

    public String getParcelNumber(){
        return this.parcelNumber;
    }

    public void setParcelNumber(String parcelNumber){
        this.parcelNumber = parcelNumber;
    }

    private View.OnClickListener valueEditor = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            int value = Integer.parseInt(getParcelNumber() + button.getTag().toString());
            if(value > maxParcels){
                value = maxParcels;
                Toast.makeText(PaymentParcelling.this, "O valor de parcelas ultrapassa o máximo, que é " + getMaxParcels(), Toast.LENGTH_SHORT).show();
            }else if(value < minParcels){
                value = minParcels;
                Toast.makeText(PaymentParcelling.this, "O valor de parcelas é menor que o mínimo, que é: " + getMaxParcels(), Toast.LENGTH_SHORT).show();
            }
            setParcelNumber("" + value); //Updating the parcel number
            editTextField(R.id.passwordText,getParcelNumber());
        }
    };

    private View.OnClickListener eraseLastNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String actualValue = getParcelNumber();
            if(actualValue.length() == 1){
                actualValue = "0";
            }else{
                actualValue = actualValue.substring(0,actualValue.length()-1);
            }
            setParcelNumber(actualValue); //Updating the parcel number
            editTextField(R.id.passwordText,getParcelNumber());
        }
    };

    private View.OnClickListener confirmParcel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("Parcells",getParcelNumber());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

    public void editTextField(int textToBeEdited, String text){
        TextView editText = (TextView) findViewById(textToBeEdited);
        editText.setText(text);//Updating the parcel number text;
    }

    public void setNumberButtonsOnClick(){
        ConstraintLayout buttonsLayout = (ConstraintLayout) findViewById(R.id.buttonsLayout);
        for(int i = 0; i < buttonsLayout.getChildCount(); i++){
            if(buttonsLayout.getChildAt(i).getTag().toString().equals("c")){//If it is a constraint layout
                ConstraintLayout c = (ConstraintLayout) buttonsLayout.getChildAt(i);
                for(int j = 0; j < c.getChildCount(); j++){
                    Button button = (Button) c.getChildAt(j);
                    if(!button.getTag().toString().equals("erase")){//If it's a number
                        button.setOnClickListener(valueEditor);
                    }else{//If it's the erase button
                        button.setOnClickListener(eraseLastNumber);
                    }
                }
            }else if(buttonsLayout.getChildAt(i).getTag().toString().equals("confirm")){
                ConstraintLayout c = (ConstraintLayout)  buttonsLayout.getChildAt(i);
                Button button = (Button) c.getChildAt(0);
                button.setOnClickListener(confirmParcel);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_parcelling);
        setTitle("");
        editTextField(R.id.passwordText,getParcelNumber());
        Intent intent = getIntent();
        String paymentValue = intent.getStringExtra("PaymentValue");
        this.maxParcels = intent.getIntExtra("maxParcel",0);
        this.maxParcels = intent.getIntExtra("minParcel",0);
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        setNumberButtonsOnClick();
        textView.setText(paymentValue); //Setting the text to show the value chosen in the value assign screen
    }
}