package com.example.mybatis.entity;

public class Student {

    public int id;

    public String tel;

    public String name;

    public String address;

    public String sto_no;

    public int money;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSto_no() {
        return sto_no;
    }
    public void setSto_no(String sto_no) {
        this.sto_no = sto_no;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
