package tbo.ynov.com.projetandroidtbo.Models;

import android.widget.TextView;

import tbo.ynov.com.projetandroidtbo.R;

/**
 * Created by Trax6 on 06/06/2018.
 */

public class School {

    private int Id;
    private String name;
    private String address;
    private String zip_code;
    private String city;
    private String opennings;
    private String phone;
    private String email;
    private String latitude;
    private String longitude;
    private String nbStudent;
    private String status;

    public School(int id, String name, String address, String zipCode, String city, String openingHours, String phone, String email, String latitude, String longitude, String studentsCount, String status){
        this.Id = id;
        this.name = name;
        this.address = address;
        this.zip_code = zipCode;
        this.city = city;
        this.opennings = openingHours;
        this.phone = phone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbStudent = studentsCount;
        this.status = status;
    }

    public School(String name, String address, String zipCode, String city, String openingHours, String phone, String email, String latitude, String longitude, String studentsCount, String status){
        this.name = name;
        this.address = address;
        this.zip_code = zipCode;
        this.city = city;
        this.opennings = openingHours;
        this.phone = phone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbStudent = studentsCount;
        this.status = status;
    }

    public int getStudentsCount(){
        return Integer.parseInt(this.nbStudent);
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCity() {
        return city;
    }

    public String getOpennings() {
        return opennings;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNbStudent() {
        return nbStudent;
    }
}
