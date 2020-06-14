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
public class Consumption implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer idexpense;

    @TableField("idFood")
    private Integer idFood;

    @TableField("numFood")
    private Integer numFood;

    @TableField("idAdmin")
    private Integer idAdmin;

    @TableField("idUser")
    private String idUser;

    private LocalDateTime date;

    @TableField(exist = false)
    private String nameFood;


}
