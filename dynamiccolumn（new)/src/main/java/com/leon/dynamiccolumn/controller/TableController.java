package com.leon.dynamiccolumn.controller;

import com.leon.dynamiccolumn.response.R;
import com.leon.dynamiccolumn.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "表结构管理", tags = {"用户操作表结构接口"})
@RestController
@RequestMapping("table")
public class TableController {

    @Autowired
    private TableService tableService;


    /**
     * 查询所有列
     *
     * @param table
     * @return
     */
    @ApiOperation(value = "查询列", notes = "查询所有列")
    @ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true)
    @GetMapping("columns")
    public R getAllColumns(@RequestParam("table") String table) {
        List<String> columns = tableService.getColumns(table);
        return R.ok().data(columns);
    }

    /**
     * 查询所有表
     *
     * @return
     */
    @ApiOperation(value = "查询表", notes = "查询所有表")
    @GetMapping("tables")
    public R getAllTables() {
        List<String> tables = tableService.getTables();
        return R.ok().data(tables);
    }


    /**
     * 添加列
     *
     * @param table
     * @param column
     * @return
     */
    @ApiOperation(value = "增加列", notes = "新增一列")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true),
            @ApiImplicitParam(name = "column", value = "列名", paramType = "query", required = true)})
    @PostMapping("column")
    public R addColumn(@RequestParam("table") String table, @RequestParam("column") String column) {
        tableService.addColunm(column, table);
        return this.getAllColumns(table);
    }

    /**
     * 删除列
     *
     * @param table
     * @param column
     * @return
     */
    @ApiOperation(value = "删除列", notes = "删除一列")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true),
            @ApiImplicitParam(name = "column", value = "列名", paramType = "query", required = true)})
    @DeleteMapping("column")
    public R delColumn(@RequestParam("table") String table, @RequestParam("column") String column) {
        tableService.delColunm(column, table);
        return this.getAllColumns(table);
    }

    /**
     * 修改列
     *
     * @param table
     * @param column1
     * @param column2
     * @return
     */
    @ApiOperation(value = "修改列", notes = "修改列名")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true),
            @ApiImplicitParam(name = "column1", value = "旧列名", paramType = "query", required = true),
            @ApiImplicitParam(name = "column2", value = "新列名", paramType = "query", required = true)
    })
    @PutMapping("column")
    public R renameColumn(@RequestParam("table") String table, @RequestParam("column1") String column1, @RequestParam("column2") String column2) {
        tableService.renameColunm(column1, column2, table);
        return this.getAllColumns(table);
    }
}
