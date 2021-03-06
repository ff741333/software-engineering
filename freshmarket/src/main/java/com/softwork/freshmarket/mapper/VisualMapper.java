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
    @Select("SELECT a.name, SUM(a.num) AS num FROM (SELECT DATE_FORMAT(`consumption`.`date`,'%m-%d') AS name, `consumption`.`numFood`  AS `num`FROM (`consumption` JOIN `food`) WHERE (`consumption`.`idFood` = `food`.`idfood`) AND food.idFood LIKE #{Id} AND `consumption`.`date` BETWEEN #{date1} AND #{date2} ) a GROUP BY name")
    public List<totalcol> findFoodFromTotalcol(String Id,String date1,String date2);
    @Select("SELECT a.name, SUM(a.num) AS num FROM (SELECT DATE_FORMAT(`consumption`.`date`,'%m-%d') AS name, `consumption`.`numFood`  AS `num`FROM (`consumption` JOIN `food`) WHERE (`consumption`.`idFood` = `food`.`idfood`) AND food.idFood LIKE #{Id}) a GROUP BY name")
    public List<totalcol> findFromTotalcol(String Id);
}
