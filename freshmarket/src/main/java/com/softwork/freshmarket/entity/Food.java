package com.softwork.freshmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Food implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "idfood", type = IdType.AUTO)
    private Integer idfood;

    @TableField(value = "name")
    private String name;

    @TableField(value = "price")
    private Float price;

    private String type;

    private String imgurl;

    private Integer num;

    private String description;

}
