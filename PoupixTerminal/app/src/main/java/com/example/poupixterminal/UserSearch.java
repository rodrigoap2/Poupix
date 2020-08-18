package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
        this.userInfos = new UserInfos("Rodrigo",this.userCpf,true,false,2.50);
        return true; //return true if found, false if not
    }
    private double findStoreCashback(){
        //connect to API to find store cashback;
        this.storeCashback = 10;
        return 10;
    }

    public void pagePassStub2(){
        Intent intent = new Intent(UserSearch.this,CardInsert.class);
        intent.putExtra("PaymentInfo", this.paymentInformations);
        intent.putExtra("UserInfo", this.userInfos);
        intent.putExtra("StoreCashback", this.storeCashback);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        setTitle("");
        this.storeCashback = 0;
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");
        this.userCpf = intent.getStringExtra("Cpf");
        if(findUserCpf() && findStoreCashback() != 0) {
            new CountDownTimer(3000, 1000) {
                public void onTick(long milissecondsUntilDone) {
                }

                public void onFinish() {
                    pagePassStub2();
                }
            }.start();
        }
    }
}