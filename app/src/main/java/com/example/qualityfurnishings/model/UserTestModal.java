package com.example.qualityfurnishings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserTestModal implements Parcelable {
    public UserTestModal(){

    }
    public UserTestModal(String fullName, String email, String phoneNumber, String password, String address, String postalcode, String province) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.postalcode = postalcode;
        this.province = province;
    }

    protected UserTestModal(Parcel in) {
        fullName = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
        address = in.readString();
        postalcode = in.readString();
        province = in.readString();
    }

    public static final Creator<UserTestModal> CREATOR = new Creator<UserTestModal>() {
        @Override
        public UserTestModal createFromParcel(Parcel in) {
            return new UserTestModal(in);
        }

        @Override
        public UserTestModal[] newArray(int size) {
            return new UserTestModal[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String fullName, email,phoneNumber,password,address,postalcode,province;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeString(address);
        dest.writeString(postalcode);
        dest.writeString(province);
    }
}
