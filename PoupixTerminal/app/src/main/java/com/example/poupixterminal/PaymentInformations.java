package com.example.poupixterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentInformations implements Parcelable {
    private String value;
    private String paymentMethod;
    private boolean directPayment;
    private int parcells;
    private double cashbackValue;
    private double microInvestimentValue;

    public PaymentInformations() {
    }

    protected PaymentInformations(Parcel in) {
        value = in.readString();
        paymentMethod = in.readString();
        directPayment = in.readByte() != 0;
        parcells = in.readInt();
        cashbackValue = in.readDouble();
        microInvestimentValue = in.readDouble();
    }

    public static final Creator<PaymentInformations> CREATOR = new Creator<PaymentInformations>() {
        @Override
        public PaymentInformations createFromParcel(Parcel in) {
            return new PaymentInformations(in);
        }

        @Override
        public PaymentInformations[] newArray(int size) {
            return new PaymentInformations[size];
        }
    };

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isDirectPayment() {
        return directPayment;
    }

    public void setDirectPayment(boolean directPayment) {
        this.directPayment = directPayment;
    }

    public int getParcells() {
        return parcells;
    }

    public void setParcells(int parcells) {
        this.parcells = parcells;
    }

    public double getCashbackValue() {
        return cashbackValue;
    }

    public void setCashbackValue(double cashbackValue) {
        this.cashbackValue = cashbackValue;
    }

    public double getMicroInvestimentValue() {
        return microInvestimentValue;
    }

    public void setMicroInvestimentValue(double microInvestimentValue) {
        this.microInvestimentValue = microInvestimentValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(value);
        parcel.writeString(paymentMethod);
        parcel.writeByte((byte) (directPayment ? 1 : 0));
        parcel.writeInt(parcells);
        parcel.writeDouble(cashbackValue);
        parcel.writeDouble(microInvestimentValue);
    }
}
