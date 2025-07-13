package com.zzq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName(value = "t_order")
public class Order implements Serializable {


    public Order() {
    }

    public Order(String customer, Integer totalPrice) {
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public Order(Integer id, String customer, Integer totalPrice) {
        this.id = id;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String customer;

    private Integer totalPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
