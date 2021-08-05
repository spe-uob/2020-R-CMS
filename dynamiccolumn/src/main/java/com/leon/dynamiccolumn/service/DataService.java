package com.leon.dynamiccolumn.service;

import java.util.List;
import java.util.Map;

public interface DataService {


    List<Map> getAllData(String table);

    int updateById(String table,Map<String, Object> data);

    void saveData(List<Map<String, Object>> data, String table);

    List<Map> exportExcel(String table, List<String> columns);
}
