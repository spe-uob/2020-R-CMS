package com.leon.dynamiccolumn.service.impl;

import com.leon.dynamiccolumn.dao.TableDao;
import com.leon.dynamiccolumn.exception.DuplicateColumnException;
import com.leon.dynamiccolumn.exception.UnExistColumnException;
import com.leon.dynamiccolumn.exception.UnExistTabelException;
import com.leon.dynamiccolumn.finals.Constant;
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
     * 数据库名称
     */
    private String store;

    @Value("${spring.datasource.url}")
    public void initStore(String url) {
        String host = url.substring(0, url.lastIndexOf("?"));
        this.store = host.substring(host.lastIndexOf("/") + 1);
    }

    /**
     * 获取所有列
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
     * 获取所有表
     *
     * @return
     */
    @Override
    public List<String> getTables() {
        return this.tableDao.getTables(this.store);
    }

    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean isExistTable(String tableName) {
        return this.tableDao.isExistTable(tableName, this.store) == 0 ? false : true;
    }


    /**
     * 判断列是否存在
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
     * 增加列
     *
     * @param column
     * @param tableName
     */
    @Override
    public void addColunm(String column, String tableName) {
        if (this.tableDao.isExistTable(tableName, this.store) == 0) {
            throw new UnExistTabelException();
        }
        if (this.tableDao.isExiseColumn(tableName, column, this.store) != 0) {
            //列已经存在
            throw new DuplicateColumnException();

        }
        this.tableDao.addColunm(column, tableName);
    }

    /**
     * 删除列
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
        //主键列不允许删除
        if (column.equalsIgnoreCase(Constant.PRIMARY_KEY)) {
            return;
        }
        this.tableDao.delColunm(column, tableName);
    }

    /**
     * 重命名列
     *
     * @param column1   旧
     * @param column2   新
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
        //先修改类型
        this.tableDao.initColunm(column1, tableName);
        this.tableDao.renameColunm(column1, column2, tableName);
    }
}
