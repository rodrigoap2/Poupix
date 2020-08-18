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

import java.text.ParseException;

public class CpfDefine extends AppCompatActivity {
    private String cpf = "00000000000";
    private PaymentInformations paymentInformations;

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public PaymentInformations getPaymentInformations() {
        return paymentInformations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpf_define);
        setTitle("");
        setNumberButtonsOnClick();
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");
    }

    private String cpfFormat(){
        String newCpf = "";
        String oldCpf = getCpf();
        for(int i = 0; i < 11; i++){
            if(i % 3 == 0 && i != 0){
                if(i == 9){
                    newCpf += "-";
                }else{
                    newCpf += ".";
                }
            }
            newCpf += oldCpf.charAt(i);
        }
        return newCpf;
    }

    public void editTextField(int textToBeEdited, String text){
        TextView editText = (TextView) findViewById(textToBeEdited);
        editText.setText(text);//Updating the parcel number text;
    }

    private View.OnClickListener valueEditor = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String valueToBeAdd = button.getTag().toString();
            String cpf = getCpf();
            if(cpf.charAt(0) == '0'){
                String newCpf = cpf.substring(1) + valueToBeAdd;
                setCpf(newCpf);
            }
            editTextField(R.id.CpfText,cpfFormat());
        }
    };

    private View.OnClickListener eraseLastNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String actualCpf = getCpf();
            String newCpf = "0" + actualCpf.substring(0, actualCpf.length()-1);
            setCpf(newCpf);
            editTextField(R.id.CpfText,cpfFormat());
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
                        button.setOnClickListener(eraseLastNumber);
                    }else if(button.getTag().toString().equals("jump")){//If it's the erase button
                        button.setOnClickListener(jumpButton);
                    }else{
                        button.setOnClickListener(valueEditor);
                    }
                }
            }else if(buttonsLayout.getChildAt(i).getTag().toString().equals("confirm")){
                ConstraintLayout c = (ConstraintLayout)  buttonsLayout.getChildAt(i);
                Button button = (Button) c.getChildAt(0);
                button.setOnClickListener(confirmCpf);
            }
        }
    }

    private View.OnClickListener jumpButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           UserInfos userInfos = new UserInfos("Usuário","naotemcpf",false);
           PaymentInformations paymentInformations = getPaymentInformations();
           Intent intent = new Intent(CpfDefine.this, CardInsert.class);
           intent.putExtra("PaymentInfo",paymentInformations);
           intent.putExtra("UserInfo", userInfos);
           startActivity(intent);
        }
    };

    private View.OnClickListener confirmCpf = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PaymentInformations paymentInformations = getPaymentInformations();
            String userCpf = getCpf();
            Intent intent = new Intent(CpfDefine.this,UserSearch.class);
            intent.putExtra("PaymentInfo",paymentInformations);
            intent.putExtra("Cpf", userCpf);
            startActivity(intent);
        }
    };
}