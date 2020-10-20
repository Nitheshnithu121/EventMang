package com.example.eventmang;

public class Userhelperforeventreg {
    String name,email,phone,event,location;

    public Userhelperforeventreg() {

    }

    public Userhelperforeventreg(String name, String email, String phone, String event, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.event = event;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
