package cn.hwsoft.wisdom.core.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @program: parent
 * @description:一些基本的工具方法
 * @author: QEcode
 * @create: 2019-07-12 11:05
 **/
public class BaseUtils {
    /**
     * 生成8位的唯一标识符
     */
    public static String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static Integer createUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 10]);
        }
        if(shortBuffer.charAt(0)=='0'){
            int random = (new Random().nextInt(8))+1;
            char ch = chars[random].charAt(0);
            shortBuffer.setCharAt(0,ch);
        }
        Integer id = Integer.valueOf(shortBuffer.toString());
        return id;
    }

}
