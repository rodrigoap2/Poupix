package com.example.poupixterminal;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import br.com.paxbr.easypaymentpos.POSConfig;
import br.com.paxbr.easypaymentpos.domain.Product;

public class ScreenStatus {
    private Activity activity;
    private boolean showingCardScreen;
    private boolean showingProcessingScreen;
    private boolean showingRandomMessage;
    private boolean showingPaymentsMethods;
    private boolean showingApprovedScreen;
    private boolean showingRejectedScreen;
    private boolean showingPasswordScreen;
    private boolean showingApplicationsScreen;

    public ScreenStatus(Activity activity) {
        this.activity = activity;
        this.showingCardScreen = false;
        this.showingProcessingScreen = false;
        this.showingRandomMessage = false;
        this.showingPaymentsMethods = false;
        this.showingApprovedScreen = false;
        this.showingRejectedScreen = false;
        this.showingPasswordScreen = false;
        this.showingApplicationsScreen = false;
    }

    public void hideAllScreens(){
        if(showingCardScreen){
            hideCardInfo();
        }
        if(showingProcessingScreen){
            hideProcessingText();
        }
        if(showingRandomMessage){
            hideRandomMessage();
        }
        if(showingPaymentsMethods){
            hideMethodsScreen();
        }
        if(showingApprovedScreen){
            hideApprovedScreen();
        }
        if(showingRejectedScreen){
            hideRejectedScreen();
        }
        if(showingPasswordScreen){
            hidePasswordScreen();
        }
        if(showingApplicationsScreen){
            hideApplicationsScreen();
        }
    }

    public void hideCardInfo(){
        ImageView cardImage = (ImageView) this.activity.findViewById(R.id.payCardImage);
        cardImage.setVisibility(View.INVISIBLE);
        TextView insertText = (TextView) this.activity.findViewById(R.id.MethodChoose);
        insertText.setVisibility(View.INVISIBLE);
        this.showingCardScreen = false;
    }
    public void showCardInfo(){
        ImageView cardImage = (ImageView) this.activity.findViewById(R.id.payCardImage);
        cardImage.setVisibility(View.VISIBLE);
        TextView insertText = (TextView) this.activity.findViewById(R.id.MethodChoose);
        insertText.setVisibility(View.VISIBLE);
        this.showingCardScreen = true;
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
    public void showMethodsScreen(){
        this.showingPaymentsMethods = true;
    }
    public void hideMethodsScreen(){
        ListView l = (ListView) activity.findViewById(R.id.cardPaymentMethods);
        l.setVisibility(View.INVISIBLE);
        TextView methodText = activity.findViewById(R.id.MethodChoose);
        methodText.setVisibility(View.INVISIBLE);
    }
    public void showApprovedScreen(){
        ConstraintLayout approvedScreen = (ConstraintLayout) this.activity.findViewById(R.id.approvedLayout);
        approvedScreen.setVisibility(View.VISIBLE);
        this.showingApprovedScreen = true;
    }
    public void hideApprovedScreen(){
        ConstraintLayout approvedScreen = (ConstraintLayout) this.activity.findViewById(R.id.approvedLayout);
        approvedScreen.setVisibility(View.INVISIBLE);
        this.showingApprovedScreen = false;
    }
    public void showRejectedScreen(){
        ConstraintLayout reprovedScreen = (ConstraintLayout) this.activity.findViewById(R.id.reprovedLayout);
        reprovedScreen.setVisibility(View.VISIBLE);
        this.showingRejectedScreen = true;
    }
    public void hideRejectedScreen(){
        ConstraintLayout reprovedScreen = (ConstraintLayout) this.activity.findViewById(R.id.reprovedLayout);
        reprovedScreen.setVisibility(View.INVISIBLE);
        this.showingRejectedScreen = false;
    }
    public void showPasswordScreen(String totalValue, String cashbackValue, String microInvestimentValue){
        TextView totalValueText = (TextView) activity.findViewById(R.id.transactionValueText2);
        totalValueText.setText(totalValue);
        totalValueText.setVisibility(View.VISIBLE);
        TextView MethodText = (TextView) activity.findViewById(R.id.MethodChoose);
        MethodText.setText("Valor da transação");
        MethodText.setVisibility(View.VISIBLE);
        TextView cashbackValueText = (TextView) activity.findViewById(R.id.cashbackValue);
        cashbackValueText.setText("Valor do cashback: " + cashbackValue);
        cashbackValueText.setVisibility(View.VISIBLE);
        TextView microInvestimentValueText = (TextView) activity.findViewById(R.id.microInvestValue);
        microInvestimentValueText.setText("Valor do micro-investimento: " + microInvestimentValue);
        microInvestimentValueText.setVisibility(View.VISIBLE);
        ConstraintLayout totalValueConstraint = (ConstraintLayout) activity.findViewById(R.id.passwordValueLayout);
        totalValueConstraint.setVisibility(View.VISIBLE);
        showingPasswordScreen = true;
    }
    public void hidePasswordScreen(){
        TextView totalValueText = (TextView) activity.findViewById(R.id.transactionValueText2);
        totalValueText.setVisibility(View.INVISIBLE);
        TextView MethodText = (TextView) activity.findViewById(R.id.MethodChoose);
        MethodText.setVisibility(View.INVISIBLE);
        TextView cashbackValueText = (TextView) activity.findViewById(R.id.cashbackValue);
        cashbackValueText.setVisibility(View.INVISIBLE);
        TextView microInvestimentValueText = (TextView) activity.findViewById(R.id.microInvestValue);
        microInvestimentValueText.setVisibility(View.VISIBLE);
        ConstraintLayout totalValueConstraint = (ConstraintLayout) activity.findViewById(R.id.passwordValueLayout);
        totalValueConstraint.setVisibility(View.INVISIBLE);
    }
    public void showApplicationsScreen(List <String> applications, POSConfig config){
        TextView methodText = activity.findViewById(R.id.MethodChoose);
        methodText.setText("Escolha sua forma de pagamento");
        methodText.setVisibility(View.VISIBLE);
        ListView l = (ListView) activity.findViewById(R.id.cardPaymentMethods);
        CustomizedApplicationAdapter customizedApplicationAdapter = new CustomizedApplicationAdapter(activity, R.layout.custom_list, applications, config);
        l.setAdapter(customizedApplicationAdapter);
        l.setVisibility(View.VISIBLE);
        this.showingApplicationsScreen = true;
    }
    public void hideApplicationsScreen(){
        TextView methodText = activity.findViewById(R.id.MethodChoose);
        methodText.setVisibility(View.INVISIBLE);
        ListView l = (ListView) activity.findViewById(R.id.cardPaymentMethods);
        l.setVisibility(View.INVISIBLE);
        this.showingApplicationsScreen = false;
    }
}
