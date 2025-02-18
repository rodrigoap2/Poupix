package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String transactionValue = "000";
    private String formattedTransactionValue = "R$ 0,00";

    public void valueFormat(){
        String textValue = this.transactionValue.substring(0,this.transactionValue.length()-2) + "." + this.transactionValue.substring(this.transactionValue.length()-2,this.transactionValue.length());
        Locale localeBR = new Locale("pt","BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localeBR);
        textValue = "R$ " + formatter.format(Double.parseDouble(textValue)).substring(2);
        this.formattedTransactionValue = textValue;
        TextView transactionValueText = (TextView) findViewById(R.id.transactionValueText);
        transactionValueText.setText(textValue);
    }

    @Override
    public void onBackPressed() { }

    public void valueEditor(View view){
        Button buttonChosen = (Button) view;
        this.transactionValue += buttonChosen.getTag().toString();
        if(this.transactionValue.charAt(0) == '0'){
            if(this.transactionValue.length() == 5 && this.transactionValue.charAt(3) == 0 && this.transactionValue.charAt(4) == 0 ){
                this.transactionValue = this.transactionValue.replaceFirst("00","");
            }else if(this.transactionValue.length() == 4){
                this.transactionValue = this.transactionValue.replaceFirst("0","");
            }
        }
        valueFormat();
    }

    public void removeLastNumber(View view){
        if(this.transactionValue.length() > 3){
            this.transactionValue = this.transactionValue.substring(0,this.transactionValue.length()-1);
        }else{
            this.transactionValue = "0" + this.transactionValue.substring(0,this.transactionValue.length()-1);
        }
        valueFormat();
    }

    public void confirmValue(View view){
        PaymentInformations paymentInformations = new PaymentInformations();
        paymentInformations.setValue(formattedTransactionValue);//Passing to the info object the value of the transaction
        Intent intent = new Intent(MainActivity.this,PaymentMethodChoice.class);
        intent.putExtra("PaymentInfo",paymentInformations); //Putting the object into the intent
        startActivity(intent); //Passing to the paymentMethodActivity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_assign_activity);
        setTitle("");
    }
}