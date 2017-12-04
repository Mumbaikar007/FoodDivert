package com.example.abhishek.fooddivert;

/**
 * Created by ABHISHEK on 27-02-2017.
 */

public class Receiver_info_retrival_storage {

   private String name;
    private String phone_no;
  private   String address;
   private String food_name;
    private String veg;
    private String non_veg;
    private String quantity;
   private String best_before;
    private String available_time;
    private String latitude;
    private String longitude;
    private String uid;

    public Receiver_info_retrival_storage() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setVeg(String veg) {
        this.veg = veg;
    }

    public void setNon_veg(String non_veg) {
        this.non_veg = non_veg;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setBest_before(String best_before) {
        this.best_before = best_before;
    }

    public void setAvailable_time(String available_time) {
        this.available_time = available_time;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getAddress() {
        return address;
    }

    public String getAvailable_time() {
        return available_time;
    }

    public String getFood_name() {
        return food_name;
    }

    public String getBest_before() {
        return best_before;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    public String getNon_veg() {
        return non_veg;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getVeg() {
        return veg;
    }

}
