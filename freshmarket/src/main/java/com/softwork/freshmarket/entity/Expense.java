package com.softwork.freshmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Expense implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "idexpense", type = IdType.AUTO)
    private Integer idexpense;

    private Float cost;

    @TableField("idUser")
    private String idUser;

    @TableField("idAdmin")
    private String idAdmin;

    private LocalDateTime date;

    private String status;


}
