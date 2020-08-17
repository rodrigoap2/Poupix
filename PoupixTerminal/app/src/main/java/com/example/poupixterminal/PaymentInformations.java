package com.example.poupixterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentInformations implements Parcelable {
    private String value;
    private String paymentMethod;

    public PaymentInformations() {
    }

    protected PaymentInformations(Parcel in) {
        value = in.readString();
        paymentMethod = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(value);
        parcel.writeString(paymentMethod);
    }
}
