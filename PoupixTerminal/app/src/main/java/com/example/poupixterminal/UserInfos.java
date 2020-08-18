package com.example.poupixterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfos implements Parcelable {
    private String name;
    private String cpf;
    private boolean usesMicroInvesting;
    private boolean roundsMicroInvesting;
    private double microInvestingValue;

    protected UserInfos(Parcel in) {
        name = in.readString();
        cpf = in.readString();
        usesMicroInvesting = in.readByte() != 0;
        roundsMicroInvesting = in.readByte() != 0;
        microInvestingValue = in.readDouble();
    }

    public static final Creator<UserInfos> CREATOR = new Creator<UserInfos>() {
        @Override
        public UserInfos createFromParcel(Parcel in) {
            return new UserInfos(in);
        }

        @Override
        public UserInfos[] newArray(int size) {
            return new UserInfos[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isUsesMicroInvesting() {
        return usesMicroInvesting;
    }

    public void setUsesMicroInvesting(boolean usesMicroInvesting) {
        this.usesMicroInvesting = usesMicroInvesting;
    }

    public boolean isRoundsMicroInvesting() {
        return roundsMicroInvesting;
    }

    public void setRoundsMicroInvesting(boolean roundsMicroInvesting) {
        this.roundsMicroInvesting = roundsMicroInvesting;
    }

    public double getMicroInvestingValue() {
        return microInvestingValue;
    }

    public void setMicroInvestingValue(double microInvestingValue) {
        this.microInvestingValue = microInvestingValue;
    }

    public UserInfos(String name, String cpf, boolean usesMicroInvesting) {
        this.name = name;
        this.cpf = cpf;
        this.usesMicroInvesting = usesMicroInvesting;
    }

    public UserInfos(String name, String cpf, boolean usesMicroInvesting, boolean roundsMicroInvesting) {
        this.name = name;
        this.cpf = cpf;
        this.usesMicroInvesting = usesMicroInvesting;
        this.roundsMicroInvesting = roundsMicroInvesting;
    }

    public UserInfos(String name, String cpf, boolean usesMicroInvesting, boolean roundsMicroInvesting, double microInvestingValue) {
        this.name = name;
        this.cpf = cpf;
        this.usesMicroInvesting = usesMicroInvesting;
        this.roundsMicroInvesting = roundsMicroInvesting;
        this.microInvestingValue = microInvestingValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(cpf);
        parcel.writeByte((byte) (usesMicroInvesting ? 1 : 0));
        parcel.writeByte((byte) (roundsMicroInvesting ? 1 : 0));
        parcel.writeDouble(microInvestingValue);
    }
}
