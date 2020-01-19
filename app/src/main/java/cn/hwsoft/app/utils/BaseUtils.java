package cn.hwsoft.app.utils;

import cn.hwsoft.wisdom.core.domain.Inspect_type;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 基本的工具类
 * @author: QEcode
 * @create: 2019-08-08 10:56
 **/
public class BaseUtils {

    /**
     * 抽取出军检类中的属性传到前台,不需要传输全部数据
     *
     * @param inspects
     * @param clazz
     * @param <T>
     * @return
     */
    //需要传输的数据
    public static String[] InspectFieldsName = {"Id", "Title", "Process_status"};
    public static String[] processStatus = {"", "已提交", "已受理", "已完结"};
    public static String[] InspectTypeName = {"", "inform", "sue", "appeal"};

    public static <T> List<Map<String, Object>> InspectToJsonObject(List<T> inspects, Map<Integer, Inspect_type> types, Class<T> clazz, String[] fieldsName) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            for (T inspect : inspects) {
                Map<String, Object> map = new HashMap<>();
                for (String fieldName : fieldsName) {
                    Method method = clazz.getMethod("get" + fieldName);
                    Type t = method.getGenericReturnType();
                    if (t.getTypeName().equals("java.lang.String")) {
                        String str = (String) method.invoke(inspect);
                        map.put(fieldName.toLowerCase(), str);
                    } else if (t.getTypeName().equals("java.lang.Integer")) {
                        Integer integer = (Integer) method.invoke(inspect);
                        map.put(fieldName.toLowerCase(), integer);
                    } else if (t.getTypeName().equals("java.lang.Byte")) {
                        Byte b = (Byte) method.invoke(inspect);
                        map.put(fieldName.toLowerCase(), b);
                    }
                }
                //设置处理状态
                byte process_status = (byte) map.get("process_status");
                map.put("process_status", processStatus[process_status]);
                //获取id
                Integer id = (Integer) map.get("id");
                //获取类型
                Inspect_type type = types.get(id);
                String typeTitle = type.getTitle();
                map.put("type", typeTitle);
                int tag = type.getTag();
                map.put("tag", tag);
                //设置获取该军检的url
                String url = "/app/inspect/" + InspectTypeName[tag] + "/" + map.get("id");
                map.put("url", url);
                list.add(map);
            }

            return list;
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}