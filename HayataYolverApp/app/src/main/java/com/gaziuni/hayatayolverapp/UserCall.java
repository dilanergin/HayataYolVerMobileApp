package com.gaziuni.hayatayolverapp;

public class UserCall {
    String namesurname,phone,adress,ulongitude,ulatitude;

    public UserCall(String namesurname, String phone, String adress, String ulongitude, String ulatitude) {
        this.namesurname = namesurname;
        this.phone = phone;
        this.adress = adress;
        this.ulongitude = ulongitude;
        this.ulatitude = ulatitude;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUlongitude() {
        return ulongitude;
    }

    public void setUlongitude(String ulongitude) {
        this.ulongitude = ulongitude;
    }

    public String getUlatitude() {
        return ulatitude;
    }

    public void setUlatitude(String ulatitude) {
        this.ulatitude = ulatitude;
    }
}
