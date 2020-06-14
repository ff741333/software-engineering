package com.softwork.freshmarket.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Car implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("idfood")
    private Integer idfood;

    private Integer numfood;

    @TableField("idUser")
    private String idUser;

    private LocalDateTime date;

    @TableField(exist = false)
    private String name;

}
