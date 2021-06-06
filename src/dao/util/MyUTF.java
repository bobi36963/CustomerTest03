package dao.util;

import java.io.UnsupportedEncodingException;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/31
 */

public class MyUTF {

    //封装成工具类
    public static String getNewString(String str) throws UnsupportedEncodingException
    {
        return new String(str.getBytes("ISO-8859-1"),"UTF-8");
    }
}
