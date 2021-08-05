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

@Api(value = "manage table structure", tags = {"The interface of the user operation table structure"})
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
    @ApiOperation(value = "query column", notes = "query all collumn")
    @ApiImplicitParam(name = "table", value = "table name", paramType = "query", required = true)
    @GetMapping("columns")
    public R getAllColumns(@RequestParam("table") String table) {
        List<String> columns = tableService.getColumns(table);
        return R.ok().data(columns);
    }

    /**
     * query all tables
     *
     * @return
     */
    @ApiOperation(value = "query table", notes = "query all table")
    @GetMapping("tables")
    public R getAllTables() {
        List<String> tables = tableService.getTables();
        return R.ok().data(tables);
    }


    /**
     * add column
     *
     * @param table
     * @param column
     * @return
     */
    @ApiOperation(value = "add column", notes = "add a new column")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "table name", paramType = "query", required = true),
            @ApiImplicitParam(name = "column", value = "column name", paramType = "query", required = true)})
    @PostMapping("column")
    public R addColumn(@RequestParam("table") String table, @RequestParam("column") String column) {
        tableService.addColunm(column, table);
        return this.getAllColumns(table);
    }

    /**
     * delete column
     *
     * @param table
     * @param column
     * @return
     */
    @ApiOperation(value = "delete column", notes = "delete a column")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "table name", paramType = "query", required = true),
            @ApiImplicitParam(name = "column", value = "column name", paramType = "query", required = true)})
    @DeleteMapping("column")
    public R delColumn(@RequestParam("table") String table, @RequestParam("column") String column) {
        tableService.delColunm(column, table);
        return this.getAllColumns(table);
    }

    /**
     * modify cloumn
     *
     * @param table
     * @param column1
     * @param column2
     * @return
     */
    @ApiOperation(value = "modify column", notes = "modify column")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "table name", paramType = "query", required = true),
            @ApiImplicitParam(name = "column1", value = "old column name ", paramType = "query", required = true),
            @ApiImplicitParam(name = "column2", value = "new column name", paramType = "query", required = true)
    })
    @PutMapping("column")
    public R renameColumn(@RequestParam("table") String table, @RequestParam("column1") String column1, @RequestParam("column2") String column2) {
        tableService.renameColunm(column1, column2, table);
        return this.getAllColumns(table);
    }
}
