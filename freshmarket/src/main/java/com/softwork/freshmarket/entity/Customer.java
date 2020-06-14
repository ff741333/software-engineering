package com.softwork.freshmarket.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Customer implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("idUser")
    private String idUser;

    private String sex;

    @TableField("numPhone")
    private String numPhone;

    private String name;

    private LocalDate birthday;

    private String identity;


}
