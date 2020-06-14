package com.softwork.freshmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Store implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "idstore", type = IdType.AUTO)
    private Integer idstore;

    private String storename;

    private Float storeprice;

    private LocalDateTime submitdate;

    private Integer num;

    private Integer idfood;

    private LocalDate instoredate;

    private String supplier;

    @TableField(exist = false)
    private String name;


}
