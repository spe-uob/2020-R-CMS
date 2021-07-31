package com.leon.dynamiccolumn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataDao {

    int insertBatch(@Param("data") List<Map<String, Object>> data, @Param("table") String table);

    List<Map> queryAll(@Param("tableName") String table, @Param("columns") List<String> columns);

    int updateById(@Param("tableName") String tableName, @Param("primaryKey") String primaryKey, @Param("data") Map<String, Object> data);

}
