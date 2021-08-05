package com.leon.dynamiccolumn.service.impl;

import com.leon.dynamiccolumn.dao.TableDao;
import com.leon.dynamiccolumn.eexception.UnExistColumnException;
import com.leon.dynamiccolumn.eexception.UnExistTabelException;
import com.leon.dynamiccolumn.service.TableService;
import com.leon.dynamiccolumn.util.ColumnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;

    /**
     * Database name
     */
    private String store;

    @Value("${spring.datasource.url}")
    public void initStore(String url) {
        String host = url.substring(0, url.lastIndexOf("?"));
        this.store = host.substring(host.lastIndexOf("/") + 1);
    }

    /**
     * Get all columns
     *
     * @param tableName
     * @return
     */
    @Override
    public List<String> getColumns(String tableName) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        return this.tableDao.getColumns(tableName, this.store);
    }

    /**
     * Get all tables
     *
     * @return
     */
    @Override
    public List<String> getTables() {
        return this.tableDao.getTables(this.store);
    }

    /**
     * Determine whether the table exists
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean isExistTable(String tableName) {
        return this.tableDao.isExistTable(tableName, this.store) == 0 ? false : true;
    }


    /**
     * Determine whether the column exists
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean isExiseColumn(String tableName, String column) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        return this.tableDao.isExiseColumn(tableName, column, this.store) == 0 ? false : true;
    }

    /**
     * Increase the column
     *
     * @param column
     * @param tableName
     */
    @Override
    public void addColunm(String column, String tableName) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        this.tableDao.addColunm(column, tableName);
    }

    /**
     * Delete the column
     *
     * @param column
     * @param tableName
     */
    @Override
    public void delColunm(String column, String tableName) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        if (this.tableDao.isExiseColumn(tableName, column, this.store) == 0) {
            throw new UnExistColumnException();
        }
        this.tableDao.delColunm(column, tableName);
    }

    /**
     * Rename columns
     *
     * @param column1   old
     * @param column2   new
     * @param tableName
     */
    @Override
    public void renameColunm(String column1, String column2, String tableName) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        if (this.tableDao.isExiseColumn(tableName, column1, this.store) == 0) {
            throw new UnExistColumnException();
        }
        column2 = ColumnUtil.formatColumn(column2);
        //Change the type first
        this.tableDao.initColunm(column1, tableName);
        this.tableDao.renameColunm(column1, column2, tableName);
    }
}
