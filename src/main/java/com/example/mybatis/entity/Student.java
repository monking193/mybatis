package com.example.mybatis.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class Student {

    @NotNull(message = "id不能为空")
    public int id;

    public String tel;

    @NotEmpty(message = "姓名不能为空")
    @Length(min = 6, max = 16, message = "The length of the password must be between 6 and 16 b")
    public String name;

    @Email(message = "邮箱格式错误!")
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sto_no='" + sto_no + '\'' +
                ", money=" + money +
                '}';
    }
}
