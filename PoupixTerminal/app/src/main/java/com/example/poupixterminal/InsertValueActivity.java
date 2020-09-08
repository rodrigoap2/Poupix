package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class InsertValueActivity extends AppCompatActivity {
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

    public View.OnClickListener valueEditor = new View.OnClickListener(){
        public void onClick(View view) {
            Button buttonChosen = (Button) view;
            transactionValue += buttonChosen.getTag().toString();
            if (transactionValue.charAt(0) == '0') {
                if (transactionValue.length() == 5 && transactionValue.charAt(3) == 0 && transactionValue.charAt(4) == 0) {
                    transactionValue = transactionValue.replaceFirst("00", "");
                } else if (transactionValue.length() == 4) {
                    transactionValue = transactionValue.replaceFirst("0", "");
                }
            }
            valueFormat();
        }
    };

    public View.OnClickListener eraseLastNumber = new View.OnClickListener(){
        public void onClick(View view) {
            if (transactionValue.length() > 3) {
                transactionValue = transactionValue.substring(0, transactionValue.length() - 1);
            } else {
                transactionValue = "0" + transactionValue.substring(0, transactionValue.length() - 1);
            }
            valueFormat();
        }
    };

    public View.OnClickListener confirmValue = new View.OnClickListener(){
        public void onClick(View view) {
            PaymentInformations paymentInformations = new PaymentInformations();
            paymentInformations.setValue(formattedTransactionValue);//Passing to the info object the value of the transaction
            Intent intent = new Intent(InsertValueActivity.this, CpfDefine.class);
            intent.putExtra("PaymentInfo", paymentInformations); //Putting the object into the intent
            startActivity(intent); //Passing to the paymentMethodActivity
        }
    };

    public void setButtonsOnClick(){
        ConstraintLayout buttonsLayout = (ConstraintLayout) findViewById(R.id.buttonsLayout);
        for(int i = 0; i < buttonsLayout.getChildCount(); i++){
            if(buttonsLayout.getChildAt(i).getTag().toString().equals("c")){//If it is a constraint layout
                ConstraintLayout c = (ConstraintLayout) buttonsLayout.getChildAt(i);
                for(int j = 0; j < c.getChildCount(); j++){
                    Button button = (Button) c.getChildAt(j);
                    if(button.getTag().toString().equals("erase")){//If it's a number
                        button.setOnClickListener(eraseLastNumber);
                    }else{
                        button.setOnClickListener(valueEditor);
                    }
                }
            }else if(buttonsLayout.getChildAt(i).getTag().toString().equals("confirm")){
                ConstraintLayout c = (ConstraintLayout)  buttonsLayout.getChildAt(i);
                Button button = (Button) c.getChildAt(0);
                button.setOnClickListener(confirmValue);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_assign);
        setTitle("");
        setButtonsOnClick();
    }
}