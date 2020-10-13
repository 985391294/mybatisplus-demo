package com.tqz.util;

import cn.hutool.core.convert.Convert;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Author: tian
 * @Date: 2020/9/18 16:46
 * @Desc:
 */
public class ResourceUtil {

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成ftp 文件上传路径
     * @return
     */
    public static String createFilePath() {
        Calendar ca = Calendar.getInstance();
        String year = Convert.toStr(ca.get(Calendar.YEAR));
        String mon = Convert.toStr(ca.get(Calendar.MONTH) + 1);
        String day = Convert.toStr(ca.get(Calendar.DAY_OF_MONTH));
        return "/" + year + "/" + mon + "/" + day + "/";
    }

    /**
     * 生成文件名
     * @param ext
     * @return
     */
    public static String createFileName(String ext) {
        Calendar ca = Calendar.getInstance();
        String year = Convert.toStr(ca.get(Calendar.YEAR));
        String mon = Convert.toStr(ca.get(Calendar.MONTH) + 1);
        String day = Convert.toStr(ca.get(Calendar.DAY_OF_MONTH));
        return "/" + year + "/" + mon + "/" + day + "/" + UUID.randomUUID().toString() + "." + ext;
    }


    /**
     * 获取文件真实类型
     * 缓存文件头信息-文件头信息
     */
   public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
        // images
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        //
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("D0CF11E0", "xls");//excel2003版本文件
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "docx");
        mFileTypes.put("504B0304", "xlsx");//excel2007以上版本文件
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
    }

    public static String getFileType(FileInputStream is) {
        String value = null;
        try {
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);

            StringBuilder builder = new StringBuilder();
            if (b == null || b.length <= 0) {
                return null;
            }
            String hv;
            for (int i = 0; i < b.length; i++) {
                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
                hv = Integer.toHexString(b[i] & 0xFF).toUpperCase();
                if (hv.length() < 2) {
                    builder.append(0);
                }
                builder.append(hv);
            }
            value = builder.toString();
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

}
