/**
 * @Title: FileUtils.java
 * @Package com.qfedu.common.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Feri
 * @date 2018年5月29日
 * @version V1.0
 */
package com.hualiang.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Title: FileUtils.java
 * @Package com.qfedu.common.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Feri
 * @date 2018年5月29日
 * @version V1.0
 * 文件工具类
 */
public class FileUtils {
    //创建文件夹 一个月一个文件夹
    public static File createDir(String dir) {
        //子文件名称：202101 202103
        //得到相应的月份
        String month = new SimpleDateFormat("yyyyMM").format(new Date());
        File dir1 = new File(new File(dir).getParent(), "fmwimages");
        File dir2 = new File(dir1, month);
        //不存在这个文件夹就创建
        if (!dir2.exists()) {
            dir2.mkdirs();
        }
        return dir2;
    }

    //创建唯一名称
    public static String createFileName(String fn) {
        if (fn.length() > 30) {
            fn = fn.substring(fn.length() - 30);
        }
        return UUID.randomUUID().toString() + "_" + fn;
    }
}
