package com.zzq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "t_order")
public class Order implements Serializable {


    @Serial
    private static final long serialVersionUID = 8347055723013141158L;

    public Order() {
    }

    public Order(String content, Integer totalPrice) {
        this.content = content;
        this.totalPrice = totalPrice;
    }

    public Order(Integer id, String content, Integer totalPrice) {
        this.id = id;
        this.content = content;
        this.totalPrice = totalPrice;
    }

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String content;

    private Integer totalPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
