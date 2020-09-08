package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public View.OnClickListener goToTransactionScreen = new View.OnClickListener(){
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InsertValueActivity.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener goToCancelationScreen = new View.OnClickListener(){
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CancelationActivity.class);
            startActivity(intent);
        }
    };

    private void setButtonsOnClick(){
        Button transactionButton = (Button) findViewById(R.id.transactionButton);
        Button cancelationButton = (Button) findViewById(R.id.cancelationButton);
        transactionButton.setOnClickListener(goToTransactionScreen);
        cancelationButton.setOnClickListener(goToCancelationScreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setTitle("");
        setButtonsOnClick();
    }
}