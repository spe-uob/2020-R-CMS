package com.leon.dynamiccolumn.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.RandomUtil;
import com.leon.dynamiccolumn.dao.DataDao;
import com.leon.dynamiccolumn.eexception.ConflictTitleException;
import com.leon.dynamiccolumn.eexception.UnExistColumnException;
import com.leon.dynamiccolumn.eexception.UnExistTabelException;
import com.leon.dynamiccolumn.service.DataService;
import com.leon.dynamiccolumn.service.TableService;
import com.leon.dynamiccolumn.util.ColumnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private TableService tableService;

    @Autowired
    private DataDao dataDao;

    private final String primaryColumn = "oo1i9we4d3";


    /**
     * 查询所有数据
     *
     * @param table
     * @return
     */
    @Override
    public List<Map> getAllData(String table) {
        List<Map> data = this.dataDao.queryAll(table, null);
        return data;
    }

    /**
     * 修改单挑数据
     *
     * @param table
     * @param data
     * @return
     */
    @Override
    public int updateById(String table, Map<String, Object> data) {
        Map<String, Object> tmp = new HashMap(data);
        data.forEach((k, v) -> {
            String validKey = ColumnUtil.formatColumn(k);
            if (!this.tableService.isExiseColumn(table, validKey)) {
                throw new UnExistColumnException();
            }
            tmp.put(validKey, v);
        });
        return this.dataDao.updateById(table, this.primaryColumn, tmp);
    }

    /**
     * 保存数据
     *
     * @param data
     * @param table
     */
    @Override
    public void saveData(List<Map<String, Object>> data, String table) {
        if (!tableService.isExistTable(table)) {
            throw new UnExistTabelException();
        }
        //没有主键就新建主键，数据导入的时候会默认填充
        if (!tableService.isExiseColumn(table, primaryColumn)) {
            tableService.addColunm(primaryColumn, table);
        }
        data = clearColumn(data, table);
        dataDao.insertBatch(data, table);
    }

    /**
     * 导出数据
     *
     * @param table
     * @param columns
     * @return
     */
    @Override
    public List<Map> exportExcel(String table, List<String> columns) {
        if (!this.tableService.isExistTable(table)) {
            throw new UnExistTabelException();
        }
        columns.forEach(column -> {
            if (!this.tableService.isExiseColumn(table, column)) throw new UnExistColumnException();
        });
        return this.dataDao.queryAll(table, columns);
    }

    /**
     * 清洗列
     *
     * @param data
     * @param table
     * @return
     */
    private List<Map<String, Object>> clearColumn(List<Map<String, Object>> data, String table) {
        List<String> column = ListUtil.toCopyOnWriteArrayList(new ArrayList<>());
        data = ListUtil.toCopyOnWriteArrayList(data);
        //格式化标题  清洗数据
        data = data.parallelStream().map(map -> {
            Map<String, Object> finalMap = new HashMap(map);
            map.forEach((k, v) -> {
                String validKey = ColumnUtil.formatColumn(k);
                finalMap.remove(k); //即使格式化前后是一样的，也不会异常
                if (finalMap.containsKey(validKey)) {
                    System.out.println(validKey);
                    throw new ConflictTitleException();
                }
                finalMap.put(validKey, v);
                if (!column.contains(validKey)) {
                    column.add(validKey);
                }
            });
            finalMap.put(primaryColumn, RandomUtil.randomNumbers(32));//设置主键
            return finalMap;
        }).collect(Collectors.toList());
        // 数据库中已存在的列
        List<String> dbCloumns = this.tableService.getColumns(table);
        dbCloumns.remove(primaryColumn);
        dbCloumns = ListUtil.toCopyOnWriteArrayList(dbCloumns);
        List<String> needDelColumns = ListUtil.toCopyOnWriteArrayList(new ArrayList<>());
        List<String> finalDbCloumns = dbCloumns;
        dbCloumns.parallelStream().forEach(dbCloumn -> {
            if (!column.contains(dbCloumn)) {//新数据不包含旧数据列  删除旧数据列
                needDelColumns.add(dbCloumn);
                finalDbCloumns.remove(dbCloumn);//dbCloumns只保留旧数据中最终需要的列
            } else {
                //交集删除
                column.remove(dbCloumn);//剩下的 全部是需要新增的列
            }
        });
        //操作表结构
        column.parallelStream().forEach(item -> this.tableService.addColunm(item, table));
        needDelColumns.parallelStream().forEach(item -> this.tableService.delColunm(item, table));
        //最新数据的完整列
        column.addAll(finalDbCloumns);
        return data;
    }


}
