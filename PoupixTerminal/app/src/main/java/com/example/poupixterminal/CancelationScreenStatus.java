package com.example.poupixterminal;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CancelationScreenStatus {
    private Activity activity;
    private boolean showingProcessingScreen;
    private boolean showingRandomMessage;

    public CancelationScreenStatus(Activity activity){
        this.activity = activity;
        this.showingProcessingScreen = false;
        this.showingRandomMessage = false;
    }

    public void hideAllScreens(){
        if(showingProcessingScreen){
            hideProcessingText();
        }
        if(showingRandomMessage){
            hideRandomMessage();
        }
    }

    public void hideProcessingText(){
        ConstraintLayout processingLayout = (ConstraintLayout) this.activity.findViewById(R.id.processingLayout);
        processingLayout.setVisibility(View.INVISIBLE);
        this.showingProcessingScreen = false;
    }
    public void showProcessingText(){
        ConstraintLayout processingLayout = (ConstraintLayout) this.activity.findViewById(R.id.processingLayout);
        processingLayout.setVisibility(View.VISIBLE);
        this.showingProcessingScreen = true;
    }
    public void hideRandomMessage(){
        ConstraintLayout showMessageLayout = (ConstraintLayout) this.activity.findViewById(R.id.showMessageLayout);
        showMessageLayout.setVisibility(View.INVISIBLE);
        TextView randomText = (TextView) this.activity.findViewById(R.id.randomText);
        randomText.setText("");
        this.showingRandomMessage = false;
    }
    public void showRandomText(String text){
        ConstraintLayout showMessageLayout = (ConstraintLayout) this.activity.findViewById(R.id.showMessageLayout);
        showMessageLayout.setVisibility(View.VISIBLE);
        TextView randomText = (TextView) this.activity.findViewById(R.id.randomText);
        randomText.setText(text);
        this.showingRandomMessage = true;
    }
}
