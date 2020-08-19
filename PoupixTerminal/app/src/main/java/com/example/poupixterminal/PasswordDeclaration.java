package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class PasswordDeclaration extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private CreditCardInfos creditCardInfos;
    private double storeCashback;
    private String passwordText = "";

    @Override//Disabling back button
    public void onBackPressed() { }

    public PaymentInformations getPaymentInformations() {
        return paymentInformations;
    }

    public UserInfos getUserInfos() {
        return userInfos;
    }

    public CreditCardInfos getCreditCardInfos() {
        return creditCardInfos;
    }

    public double getStoreCashback() {
        return storeCashback;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_declaration);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        this.creditCardInfos = intent.getParcelableExtra("CreditCardInfo");//Getting the creditCard info
        this.storeCashback = intent.getDoubleExtra("StoreCashback",0);//Getting the cashback info
        updateOperationValue();
        setNumberButtonsOnClick();
    }

    private void updateOperationValue(){
        TextView textView = (TextView) findViewById(R.id.transactionValueText);
        textView.setText(this.paymentInformations.getValue());
        calculateAndUpdateCashback();
        updateMicroInvestingValue();
    }

    private void calculateAndUpdateCashback(){
        double storeCashback = this.storeCashback;
        double transactionValue = Double.parseDouble(this.paymentInformations.getValue().substring(3).replace(".","").replace(",","."));
        TextView textView = (TextView) findViewById(R.id.cashbackValue);
        if(userInfos.getCpf().equals("naotemcpf")){//If he jumped the CPF Screen
            textView.setVisibility(View.INVISIBLE);//Dont show cashback
            paymentInformations.setCashbackValue(0);//Set cashback to 0
        }else {
            double cashbackValue = transactionValue*(storeCashback*0.01);
            paymentInformations.setCashbackValue(cashbackValue);
            String cashbackText = formatCashback(cashbackValue);
            textView.setText("Valor do cashback:" + cashbackText);
        }
    }

    private String formatCashback(double value){
        String cashback = value + "";
        String cashbackX[] = cashback.split("[.]");
        if(cashbackX[1].length() == 1){
            cashbackX[1] += "0";

        }else if(cashbackX[1].length() >= 2){
            cashbackX[1] = cashbackX[1].substring(0,2);
        }
        cashback = "R$ " + cashbackX[0] + "," + cashbackX[1];
        return cashback;
    }

    private void updateMicroInvestingValue(){
        TextView microInvestingText = (TextView) findViewById(R.id.microInvestValue);
        if(userInfos.isUsesMicroInvesting()){
            TextView TotalValue = (TextView) findViewById(R.id.transactionValueText);
            double transactionValue = Double.parseDouble(this.paymentInformations.getValue().substring(3).replace(".","").replace(",","."));;
            double microInvestingValue = 0;
            if(userInfos.isRoundsMicroInvesting()){
                double oldTransactionValue = transactionValue;
                transactionValue = Math.ceil(transactionValue);
                microInvestingValue = transactionValue - oldTransactionValue;
                this.paymentInformations.setMicroInvestimentValue(microInvestingValue);
            }else{
                double oldTransactionValue = transactionValue;
                microInvestingValue = userInfos.getMicroInvestingValue();
                transactionValue += microInvestingValue;
            }
            String newTransactionValue = valueFormat(transactionValue);
            this.paymentInformations.setValue(newTransactionValue);
            TotalValue.setText(newTransactionValue);
            microInvestingText.setText("Valor do micro-investimento:" + valueFormat(microInvestingValue));
        }else{
            microInvestingText = (TextView) findViewById(R.id.microInvestValue);
            microInvestingText.setVisibility(View.INVISIBLE);
        }
    }

    public String valueFormat(double transactionValue){
        Locale localeBR = new Locale("pt","BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localeBR);
        String textValue = "R$ " + formatter.format(transactionValue).substring(2);
        return textValue;
    }

    private View.OnClickListener passwordTextEditor = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String actualPassword = creditCardInfos.getPassword();
            String value = button.getTag().toString();
            actualPassword += value;
            creditCardInfos.setPassword(actualPassword);
            setPasswordText(getPasswordText() + "*");
            TextView passwordText = (TextView) findViewById(R.id.passwordText);
            passwordText.setText(getPasswordText());
        }
    };

    private View.OnClickListener passwordTextErase = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String actualPassword = creditCardInfos.getPassword();
            String actualPasswordText = getPasswordText();
            if(actualPassword.length() == 1){
                actualPassword = "";
                actualPasswordText = "";
            }else if(actualPassword.length() > 1){
                actualPassword = actualPassword.substring(0,actualPassword.length()-1);
                actualPasswordText = actualPasswordText.substring(0,actualPasswordText.length()-1);
            }
            creditCardInfos.setPassword(actualPassword);
            setPasswordText(actualPasswordText);
            TextView passwordText = (TextView) findViewById(R.id.passwordText);
            passwordText.setText(getPasswordText());
        }
    };
    private View.OnClickListener cancelButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PasswordDeclaration.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener confirmOperation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PasswordDeclaration.this, PaymentProcessing.class);
            intent.putExtra("PaymentInfo", getPaymentInformations());
            intent.putExtra("UserInfo", getUserInfos());
            intent.putExtra("StoreCashback", getStoreCashback());
            intent.putExtra("CreditCardInfo", getCreditCardInfos());
            startActivity(intent);
        }
    };

    public void setNumberButtonsOnClick(){
        ConstraintLayout buttonsLayout = (ConstraintLayout) findViewById(R.id.buttonsLayout);
        for(int i = 0; i < buttonsLayout.getChildCount(); i++){
            if(buttonsLayout.getChildAt(i).getTag().toString().equals("c")){//If it is a constraint layout
                ConstraintLayout c = (ConstraintLayout) buttonsLayout.getChildAt(i);
                for(int j = 0; j < c.getChildCount(); j++){
                    Button button = (Button) c.getChildAt(j);
                    if(button.getTag().toString().equals("erase")){//If it's a number
                        button.setOnClickListener(passwordTextErase);
                    }else if(button.getTag().toString().equals("cancel")){//If it's the erase button
                        button.setOnClickListener(cancelButton);
                    }else{
                        button.setOnClickListener(passwordTextEditor);
                    }
                }
            }else if(buttonsLayout.getChildAt(i).getTag().toString().equals("confirm")){
                ConstraintLayout c = (ConstraintLayout)  buttonsLayout.getChildAt(i);
                Button button = (Button) c.getChildAt(0);
                button.setOnClickListener(confirmOperation);
            }
        }
    }

}