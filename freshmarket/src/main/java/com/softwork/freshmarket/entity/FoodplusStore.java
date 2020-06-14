package com.softwork.freshmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class FoodplusStore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "idfood", type = IdType.AUTO)
    private Integer idfood;

    @TableField(value = "name")
    private String FoodsName;

    @TableField(value = "price")
    private Float Xprice;

    @TableField(value = "type")
    private String type;

    @TableField(value = "imgurl")
    private String FoodsImg;

    @TableField(value = "num")
    private Integer Xnum;

    private LocalDate laststoredate;

   /* private Integer FoodsId;

    public void doFoodID(){
        this.FoodsId=this.idfood;
    }
*/



}
