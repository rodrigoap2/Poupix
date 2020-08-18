package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserSearch extends AppCompatActivity {
    private PaymentInformations paymentInformations;
    private String userCpf;
    private double storeCashback;
    private UserInfos userInfos;
    private boolean findUserCpf(){
        //connect to API to find user cpf in DB
        //userInfos = new UserInfos("Rodrigo",userCpf,false);
        //userInfos = new UserInfos("Rodrigo",userCpf,true,true);
        userInfos = new UserInfos("Rodrigo",userCpf,true,false,2);
        return true; //return true if found, false if not
    }
    private double findStoreCashback(){
        //connect to API to find store cashback;
        return 10;
    }

    private View.OnClickListener pagePassStub = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(UserSearch.this,);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");
        this.userCpf = intent.getStringExtra("Cpf");
        findUserCpf();
        findStoreCashback();
    }
}