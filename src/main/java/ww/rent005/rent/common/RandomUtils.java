package ww.rent005.rent.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成随机数值
 * @ClassName: RandomUtils
 * @Author: cronos
 * @Date: 2020/1/31 22:08
 * @Version: 1.0
 **/
public class RandomUtils {

    private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMM");

    private static String chars = "abcdefghijklmnopqrstuvwxyz";

    private static Random random=new Random();

    //生成随机ID值 -用于UserId
    public static String getRandomId(){
        String randomId = Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase();
        return randomId+sdf1.format(new Date());
    }

    //获取随机名称 -用于昵称
    public static String getRandomNickName(){
        StringBuffer sb = new StringBuffer(10);
        for(int i=0;i<5;i++){
            sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        }
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取当前年月 -用于图片文件夹
    public static String getCurrentDateString() {
        return sdf2.format(new Date());
    }

    /**
     * 使用时间+4位随机数+文件后缀+"-temp"
     * @param fileName 文件名称临时设定
     */
    public static String createFileNameUseTime(String fileName,String suffix) {
        //获取当前时间
        String timeNumber = sdf1.format(new Date());
        //获取随机数
        Integer num = random.nextInt(9000)+1000;
        //获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return timeNumber+num+fileSuffix+suffix;
    }

}
