package com.softwork.freshmarket.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CarVo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("idfood")
    private Integer idfood;

    private Integer num;


    @TableField(exist = false)
    private Float XPrice;


    @TableField(exist = false)
    private String FoodsName;

    public void setcar(Car car){
        this.idfood=car.getIdfood();
        this.num=car.getNumfood();
    }

    public void setFood(Food food){
        this.XPrice=food.getPrice();
        this.FoodsName=food.getName();
    }

}
