package com.leon.dynamiccolumn.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.leon.dynamiccolumn.response.R;
import com.leon.dynamiccolumn.service.DataService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "数据管理", tags = {"用户操作表数据接口"})
@RestController
@RequestMapping("data")
public class DataController {

    @Value("${file.tmp}")
    private String fileTmpPath;

    @Autowired
    private DataService dataService;

    /**
     * 查询数据
     *
     * @param table
     * @return
     */
    @ApiOperation(value = "查询表所有数据", notes = "查询表所有数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true)})
    @GetMapping("all")
    public R queryDatas(@RequestParam("table") String table) {
        return R.ok().data(dataService.getAllData(table));
    }

    /**
     * 修改数据
     *
     * @param table
     * @return
     */
    @ApiOperation(value = "修改数据", notes = "修改单挑数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true)})
    @PostMapping("modify")
    public R queryDatas(@RequestParam("table") String table, @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Map<String, Object> data) {
        dataService.updateById(table, data);
        return R.ok().data(dataService.getAllData(table));
    }


    /**
     * 导入数据
     *
     * @param file
     * @param table
     * @return
     */
    @ApiOperation(value = "导入数据", notes = "导入excel")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true)})
    @PostMapping("import")
    public R importExcel(@ApiParam(hidden = true) MultipartFile file, @RequestParam("table") String table) throws IOException {
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        File tmpFile = new File(fileTmpPath, RandomUtil.randomNumbers(32) + "." + suffix);
        file.transferTo(tmpFile);

        ExcelReader reader = ExcelUtil.getReader(tmpFile);
        List<Map<String, Object>> allData = reader.readAll();
        dataService.saveData(allData, table);
        return R.ok();
    }

    /**
     * 导出数据
     *
     * @param response
     */
    @ApiOperation(value = "导出数据", notes = "导出excel")
    @ApiImplicitParams({@ApiImplicitParam(name = "table", value = "表名", paramType = "query", required = true),
            @ApiImplicitParam(name = "columns", value = "列名(多列以,分割)", paramType = "query", required = true)})
    @GetMapping("export")
    public void exportExcel(HttpServletResponse response, @RequestParam("columns") String columnsListStr, @RequestParam("table") String table) throws IOException {
        List<String> columns = new ArrayList<>();
        if (!StrUtil.isEmpty(columnsListStr)) {
            String[] columnArr = columnsListStr.split(",");
            columns = CollectionUtil.newArrayList(columnArr);
        }
        OutputStream out = response.getOutputStream();
        List<Map> maps = this.dataService.exportExcel(table, columns);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(maps, true);
        String fileName = RandomUtil.randomNumbers(10);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
        out.flush();
    }
}
