package com.spe.cms.util;

import cn.hutool.core.util.StrUtil;

public class ColumnUtil {

    public static String formatColumn(String column) {
        return StrUtil.cleanBlank(column).replaceAll("\\(", "_").replaceAll("\\)", "_");
    }
}
