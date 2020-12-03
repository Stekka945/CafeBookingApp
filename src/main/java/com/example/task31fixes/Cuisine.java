package com.example.task31fixes;

import android.os.Parcel;
import android.os.Parcelable;

public class Cuisine implements Parcelable {
    private String name;
    private int image;
    private boolean isSelected;

    public Cuisine(String name, Integer image){
        this.name=name;
        this.image=image;
    }

    protected Cuisine(Parcel in) {
        name = in.readString();
        image = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Cuisine> CREATOR = new Creator<Cuisine>() {
        @Override
        public Cuisine createFromParcel(Parcel in) {
            return new Cuisine(in);
        }

        @Override
        public Cuisine[] newArray(int size) {
            return new Cuisine[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
