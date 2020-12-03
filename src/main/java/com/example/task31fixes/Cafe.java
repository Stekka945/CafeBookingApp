package com.example.task31fixes;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;

public class Cafe implements Parcelable {
    private String cafe_name;
    private String cafe_address;
    private String cafe_opening_hrs;
    private String cafe_like;
    /*private String book_date;
    private String book_time;
    private String no_of_pax;*/
    private int img;

    public Cafe(String cafe_name,String cafe_address, String cafe_opening_hrs, String cafe_like, Integer img){
        this.cafe_name=cafe_name;
        this.cafe_address=cafe_address;
        this.cafe_opening_hrs=cafe_opening_hrs;
        this.cafe_like=cafe_like;
        this.img=img;
    }

    protected Cafe(Parcel in) {
        cafe_name=in.readString();
        cafe_address=in.readString();
        cafe_opening_hrs=in.readString();
        cafe_like=in.readString();
        img=in.readInt();
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public int getImg() {
        return img;
    }

    public String getCafe_address() {
        return cafe_address;
    }

    public String getCafe_opening_hrs() {
        return cafe_opening_hrs;
    }

    public String getCafe_like() {
        return cafe_like;
    }

    public static ArrayList<Cafe> createCafeList(){
        ArrayList<Cafe> cafeList = new ArrayList<Cafe>();
        cafeList.add(new Cafe("Serendipity Cafe","3415 Lobortis. Avenue", "10:00 am - 10:00 pm",
                "75", R.drawable.cafe1));
        cafeList.add(new Cafe("Old Asian Cafe", "430-985 Eleifend St.", "11:00 am - 9:30 pm",
                "120",R.drawable.cafe2));
        cafeList.add(new Cafe("International Taste Restaurant", "481-8762 Nulla Street","9:00 am - 11:00 pm",
                "30",R.drawable.cafe3 ));
        cafeList.add(new Cafe("Gardenhouse Cafe", "2136 Adipiscing Av.", "10:30 am - 10:00 pm",
                "85", R.drawable.cafe4));
        cafeList.add(new Cafe("Spice Cafe", "343-6527 Purus. Avenue", "5:00 pm - 10:00 pm",
                "65", R.drawable.cafe5));

        return cafeList;

    }


    public static final Creator<Cafe> CREATOR = new Creator<Cafe>() {
        @Override
        public Cafe createFromParcel(Parcel in) {
            return new Cafe(in);
        }

        @Override
        public Cafe[] newArray(int size) {
            return new Cafe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cafe_name);
        dest.writeString(cafe_address);
        dest.writeString(cafe_opening_hrs);
        dest.writeString(cafe_like);
        dest.writeInt(img);
    }
}

