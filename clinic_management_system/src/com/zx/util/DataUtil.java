package com.zx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author biao boss
 *
 */
public class DataUtil {

    /**
     * 获取订单号编号
     * @return
     */
    public static String getOrderId() {
        Date date =new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYMMddHHmmss");
        String id ="ZX"+ String.valueOf(date.getTime()).substring(3);
        return id;
    }
    
    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        return time;
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(getCurrentTime());
        

    }
}
