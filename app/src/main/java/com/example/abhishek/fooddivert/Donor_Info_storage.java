package com.example.abhishek.fooddivert;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ABHISHEK on 26-02-2017.
 */

public class Donor_Info_storage implements Parcelable {

    public String name;
    public String phone_no;
    public String address;

    public String station;
    public String food_name="";
    //public String veg="no";
    //public String non_veg="no";
    public String food_type = "";
    public String quantity="";
    public String best_before="";
    public String available_time="";
    String image_food;
    public String doner_uid ="";
    public String Get_All ="Get_All";
    public String latitude="",longitude="";
    String received="Not Yet";
    String book="0";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(phone_no);
        parcel.writeString(address);
        parcel.writeString(food_name);
        parcel.writeString(food_type);
        parcel.writeString(quantity);
        parcel.writeString(best_before);
        parcel.writeString(available_time);
        parcel.writeString(doner_uid);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(station);
        parcel.writeString(image_food);
       parcel.writeString(book);
        parcel.writeString(received);

    }


    public static final Parcelable.Creator<Donor_Info_storage> CREATOR
            = new Parcelable.Creator<Donor_Info_storage>() {
        public Donor_Info_storage createFromParcel(Parcel in) {
            return new Donor_Info_storage(in);
        }
        public Donor_Info_storage[] newArray(int size) {
            return new Donor_Info_storage[size];
        }
    };



    public Donor_Info_storage(Parcel in) {

        name = in.readString();
        phone_no = in.readString();
        address = in.readString();
        food_name = in.readString();
        food_type = in.readString();
        quantity = in.readString();
        best_before = in.readString();
        available_time = in.readString();
        doner_uid = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        station = in.readString();
        image_food = in.readString();
        book=in.readString();
        received=in.readString();
    }









    public Donor_Info_storage()
    {

    }
  /*  public Donor_Info_storage(String name,String phone_no,String address)
    {

        this.name=name;
        this.phone_no=phone_no;
        this.address=address;

        //this.latitude=latitude;
        //this.longitude=longitude;

    }*/
    public Donor_Info_storage(String receiver,String Doner_uid,String name,String phone_no,String address,String food_name,String food_type,String quantity,String best_before,String available_time,String latitude,String longitude,String station,String book,String image_food)
    {

        this.doner_uid=Doner_uid;
        this.name=name;
        this.phone_no=phone_no;
        this.address=address;

        this.food_name=food_name;
        //this.veg=veg;
        //this.non_veg=non_veg;
        this.food_type = food_type;
        this.quantity=quantity;
        this.best_before=best_before;
        this.available_time=available_time;
        this.latitude=latitude;
        this.longitude=longitude;
        this.station=station;
        this.image_food=image_food;
        this.received=receiver;

        this.book=book;
    }


}
