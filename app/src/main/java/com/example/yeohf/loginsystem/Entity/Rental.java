package com.example.yeohf.loginsystem.Entity;

public class Rental {
    public String rentalid;
    public String address;
    public String price;
    public String desc;
    public String title;

    public Rental(){
    }

    public Rental(String id,String add, String price,String desc,String title){
        this.address=add;
        this.price= price;
        this.rentalid= id;
        this.desc= desc;
        this.title= title;
    }

    public String getRentalid() {
        return rentalid;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }
}
