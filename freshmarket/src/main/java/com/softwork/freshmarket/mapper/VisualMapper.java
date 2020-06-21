package com.softwork.freshmarket.mapper;

import com.softwork.freshmarket.entity.DayTotal;
import com.softwork.freshmarket.entity.totalcol;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface VisualMapper {
    @Select("SELECT `food`.`name` AS `name`, sum(`consumption`.`numFood`)  AS `num`FROM (`consumption` JOIN `food`) WHERE (`consumption`.`idFood` = `food`.`idfood`)   GROUP BY NAME Order by num")
    public List<totalcol> findAllFromTotalcol();
    @Select("SELECT `food`.`name` AS `name`, sum(`consumption`.`numFood`)  AS `num`FROM (`consumption` JOIN `food`) WHERE (`consumption`.`idFood` = `food`.`idfood`) AND `consumption`.`date` BETWEEN #{date1} AND #{date2} GROUP BY NAME Order by num")
    public List<totalcol> findDateFromTotalcol(String date1,String date2);
}
