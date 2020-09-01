package com.example.poupixterminal;

import java.text.NumberFormat;
import java.util.Locale;

public class BonusValueCalculator {
    public String totalValue;
    public String cashbackValue;
    public String microInvestingValue;
    public double storeCashback;
    public UserInfos userInfos;

    public String getTotalValue() {
        return totalValue;
    }

    public String getCashbackValue() {
        return cashbackValue;
    }

    public String getMicroInvestingValue() {
        return microInvestingValue;
    }

    public BonusValueCalculator(String totalValue, double storeCashback, UserInfos userInfos) {
        this.totalValue = totalValue;
        this.cashbackValue = "0";
        this.microInvestingValue = "R$ 0,00";
        this.storeCashback = storeCashback;
        this.userInfos = userInfos;
    }

    public void calculateBonus(){
        calculateAndUpdateCashback();
        updateMicroInvestingValue();
    }

    private void calculateAndUpdateCashback(){
        double storeCashback = this.storeCashback;
        double transactionValue = Double.parseDouble(this.totalValue.substring(3).replace(".","").replace(",","."));
        if(userInfos.getCpf().equals("naotemcpf")){//If he jumped the CPF Screen
            this.cashbackValue = "R$ 0,00";
        }else {
            double cashback = transactionValue*(storeCashback*0.01);
            this.cashbackValue = formatCashback(cashback);
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
        if(userInfos.isUsesMicroInvesting()){
            double transactionValue = Double.parseDouble(this.totalValue.substring(3).replace(".","").replace(",","."));;
            double microInvestingValueCalculate = 0;
            if(userInfos.isRoundsMicroInvesting()){
                double oldTransactionValue = transactionValue;
                transactionValue = Math.ceil(transactionValue);
                microInvestingValueCalculate = transactionValue - oldTransactionValue;
            }else{
                double oldTransactionValue = transactionValue;
                microInvestingValueCalculate = userInfos.getMicroInvestingValue();
                transactionValue += microInvestingValueCalculate;
            }
            this.totalValue = valueFormat(transactionValue);
            this.microInvestingValue = valueFormat(microInvestingValueCalculate);
        }
    }

    public String valueFormat(double transactionValue){
        Locale localeBR = new Locale("pt","BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localeBR);
        String textValue = "R$ " + formatter.format(transactionValue).substring(2);
        return textValue;
    }
}
