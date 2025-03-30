package com.zzq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_order")
public class Order {

    public Order() {
    }

    public Order(String customer, Integer total) {
        this.customer = customer;
        this.total = total;
    }

    public Order(Integer id, String customer, Integer total) {
        this.id = id;
        this.customer = customer;
        this.total = total;
    }

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String customer;

    private Integer total;


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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
