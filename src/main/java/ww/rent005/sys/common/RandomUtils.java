package ww.rent005.sys.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成随机数值
 * @ClassName: RandomUtils
 * @Author: cronos
 * @Date: 2020/1/31 22:08
 * @Version: 1.0
 **/
public class RandomUtils {

    private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static String chars = "abcdefghijklmnopqrstuvwxyz";

    public static String getRandomId(){
        String randomId = Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase();
        return randomId+sdf1.format(new Date());
    }
}
