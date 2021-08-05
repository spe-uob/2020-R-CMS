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
     * query all data
     *
     * @param table
     * @return
     */
    @Override
    public List<Map> getAllData(String table) {
        if (!this.tableService.isExistTable(table)) {
            throw  new UnExistTabelException();
        }
        List<Map> data = this.dataDao.queryAll(table, null);
        return data;
    }

    /**
     * modify single data
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
     * save data
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
     * export Excel
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
     * clear column
     *
     * @param data
     * @param table
     * @return
     */
    private List<Map<String, Object>> clearColumn(List<Map<String, Object>> data, String table) {
        List<String> column = ListUtil.toCopyOnWriteArrayList(new ArrayList<>());
        data = ListUtil.toCopyOnWriteArrayList(data);
        //Format headers, clean data
        data = data.parallelStream().map(map -> {
            Map<String, Object> finalMap = new HashMap(map);
            map.forEach((k, v) -> {
                String validKey = ColumnUtil.formatColumn(k);
                finalMap.remove(k); //Even if it is the same before and after formatting, there is no exception
                if (finalMap.containsKey(validKey)) {
                    System.out.println(validKey);
                    throw new ConflictTitleException();
                }
                finalMap.put(validKey, v);
                if (!column.contains(validKey)) {
                    column.add(validKey);
                }
            });
            finalMap.put(primaryColumn, RandomUtil.randomNumbers(32));//Set the primary key
            return finalMap;
        }).collect(Collectors.toList());
        // An existing column in the database
        List<String> dbCloumns = this.tableService.getColumns(table);
        dbCloumns.remove(primaryColumn);
        dbCloumns = ListUtil.toCopyOnWriteArrayList(dbCloumns);
        List<String> needDelColumns = ListUtil.toCopyOnWriteArrayList(new ArrayList<>());
        List<String> finalDbCloumns = dbCloumns;
        dbCloumns.parallelStream().forEach(dbCloumn -> {
            if (!column.contains(dbCloumn)) {//The new data does not contain the old data columns  delete the old column
                needDelColumns.add(dbCloumn);
                finalDbCloumns.remove(dbCloumn);//dbCloumns only keep the columns that are ultimately needed in the old data
            } else {
                //delete intersection
                column.remove(dbCloumn);//All that remains is the column that needs to be added
            }
        });
        //Operation table structure
        column.parallelStream().forEach(item -> this.tableService.addColunm(item, table));
        needDelColumns.parallelStream().forEach(item -> this.tableService.delColunm(item, table));
        //A complete column of the latest data
        column.addAll(finalDbCloumns);
        return data;
    }


}
