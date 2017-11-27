/**
 *
 */
package com.myy.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhaorui.cao
 */
public class FileUtil {

    public final static Logger log = LoggerFactory.getLogger(FileUtil.class);

    private final static String CHARSET = "UTF-8";

    public static synchronized boolean writeFile(String filePath, String data, String charset, boolean append) {
        try {
            FileUtils.writeStringToFile(new File(filePath), data, StringUtils.isBlank(charset) ? CHARSET : charset,
                    append);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static synchronized boolean writeLines(String filePath, String charset, List<String> list, boolean append) {
        try {
            FileUtils.writeLines(new File(filePath), StringUtils.isBlank(charset) ? CHARSET : charset, list, append);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static synchronized List<String> readFile(String filePath, String charset) {
        try {
            return FileUtils.readLines(new File(filePath), StringUtils.isBlank(charset) ? CHARSET : charset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ArrayList<String>();
    }

    public static synchronized String readFileToStr(String filePath, String charset) {
        try {
            return FileUtils.readFileToString(new File(filePath), StringUtils.isBlank(charset) ? CHARSET : charset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return StringUtils.EMPTY;
    }
}
