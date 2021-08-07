package com.spe.cms.service;

import java.util.List;

public interface TableService {

    List<String> getColumns(String tableName);

    List<String> getTables();

    boolean isExistTable(String tableName);


    boolean isExiseColumn(String tableName, String column);

    void addColunm(String column, String tableName);

    void delColunm(String column, String tableName);

    void renameColunm(String column1, String column2, String tableName);
}
