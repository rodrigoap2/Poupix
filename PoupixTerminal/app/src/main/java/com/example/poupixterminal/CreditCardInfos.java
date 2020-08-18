package com.example.poupixterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCardInfos implements Parcelable {
    private String password;
    private String number;

    protected CreditCardInfos(Parcel in) {
        password = in.readString();
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(password);
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreditCardInfos> CREATOR = new Creator<CreditCardInfos>() {
        @Override
        public CreditCardInfos createFromParcel(Parcel in) {
            return new CreditCardInfos(in);
        }

        @Override
        public CreditCardInfos[] newArray(int size) {
            return new CreditCardInfos[size];
        }
    };

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CreditCardInfos() {
        this.password = "";
        this.number = "";
    }
}
