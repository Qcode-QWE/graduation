package cn.hwsoft.wisdom.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: court
 * @description:    (十位数)时间戳的转换
 * @author: QEcode
 * @create: 2019-07-11 20:13
 **/
public class TimeUtils {
    /**
     *  将时间戳转为date日期
     */
    public static String timeStampToDate(String seconds ){
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        for(int i=seconds.length();i<13;i++){
            seconds+="0";
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Long.valueOf(seconds));
    }

    /**
     * 将date类型转为10位数的时间戳
     * @param date
     * @return
     */
    public static String DateToTimeStamp(Date date){
        String seconds = String.valueOf(date.getTime());
        seconds = seconds.substring(0,10);
        return seconds;
    }
}
