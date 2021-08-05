package com.leon.dynamiccolumn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TableDao {

    /**
     * 获取所有列名
     *
     * @param tableName
     * @param store
     * @return
     */
    @Select("select column_name from information_schema.COLUMNS where table_name=#{tableName} and table_schema=#{store}")
    List<String> getColumns(@Param("tableName") String tableName, @Param("store") String store);

    /**
     * 获取所有表
     *
     * @param store
     * @return
     */
    @Select("select table_name from information_schema.tables where table_schema=#{store}")
    List<String> getTables(@Param("store") String store);

    /**
     * 判断列是否存在
     *
     * @param tableName
     * @param store
     * @return
     */
    @Select("select count(0) from information_schema.COLUMNS where table_name=#{tableName} and column_name= #{column} and table_schema=#{store}")
    int isExiseColumn(@Param("tableName") String tableName, @Param("column") String column, @Param("store") String store);


    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     */
    @Select("select count(0) from information_schema.tables where table_name = #{tableName} and table_schema=#{store}")
    int isExistTable(@Param("tableName") String tableName, @Param("store") String store);

    /**
     * 增加列
     *
     * @param column
     * @param tableName
     */
    @Update("ALTER TABLE ${tableName} ADD column `${column}` varchar(255)")
    void addColunm(@Param("column") String column, @Param("tableName") String tableName);

    /**
     * 删除列
     *
     * @param column
     * @param tableName
     */
    @Update("alter table ${tableName} drop column `${column}`")
    void delColunm(@Param("column") String column, @Param("tableName") String tableName);


    /**
     * 修改列
     *
     * @param column1
     * @param column2
     * @param tableName
     */
    @Update("alter table ${tableName} change `${column1}` `${column2}` varchar(255)")
    void renameColunm(@Param("column1") String column1, @Param("column2") String column2, @Param("tableName") String tableName);

    /**
     * 初始化类型
     *
     * @param column
     * @param tableName
     */
    @Update("alter table ${tableName} modify `${column}` varchar(255)")
    void initColunm(@Param("column") String column, @Param("tableName") String tableName);


}
