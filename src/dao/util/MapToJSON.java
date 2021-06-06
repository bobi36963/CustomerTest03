package dao.util;

import org.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/18
 */
public class MapToJSON {

    public static void JsonWrite(List<Map> list) {

        int count = list.size();
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream("D:\\AAA_Cpp\\MyProject\\CustomerTest\\web\\api\\customer.json"), "UTF-8");

            JSONObject obj = new JSONObject(new LinkedHashMap());//创建JSONObject对象

            obj.put("code", "0");
            obj.put("msg","");
            obj.put("count",count);

            for (Integer i = 0; i <count; i++) {
                JSONObject subObj = new JSONObject(new LinkedHashMap());//创建对象数组里的子对象
                Map<String,String> recording = new LinkedHashMap<>();
                recording = list.get(i);

                Set keySet = recording.keySet();
                //遍历key的集合，根据key获取对应的值
                for(Object key : keySet){
                    String value = (String) recording.get(key);
                    subObj.put((String)key,value);
                }
                obj.accumulate("data", subObj);
            }

            osw.write(obj.toString());
            osw.flush();//清空缓冲区，强制输出数据
            osw.close();//关闭输出流

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
