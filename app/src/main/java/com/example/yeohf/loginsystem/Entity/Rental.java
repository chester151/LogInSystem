package com.example.yeohf.loginsystem.Entity;

public class Rental {

    public String rentalid;
    public String address;
    public String price;
    public double lat;
    public double lng;
    public String desc;
    public String title;
    public String zone;
    public String storey;
    public String type;

    public Rental(){
    }

    public Rental(String id, String title, String address, String zone, String type, String storey, String price, double lat, double lng) {
        this.address = address;
        this.price = price;
        this.rentalid= id;
        this.title= title;
        this.zone = zone;
        this.type = type;
        this.storey = storey;
        this.lat = lat;
        this.lng = lng;

    }

    public String getRentalid() {
        return rentalid;
    }

    public void setRentalid(String rentalid) {
        this.rentalid = rentalid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
