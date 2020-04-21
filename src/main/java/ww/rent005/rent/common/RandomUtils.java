package ww.rent005.rent.common;

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
    private static SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMdd");

    private static String chars = "abcdefghijklmnopqrstuvwxyz";

    private static Random random=new Random();

    //生成随机ID值 -用于UserId
    public static String getRandomId(){
        String randomId = Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase();
        return "UU_"+sdf1.format(new Date())+randomId;
    }

    //获取随机名称 -用于昵称
    public static String getRandomNickName(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<5;i++){
            sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        }
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取随机Id -用于汽车Id
    public static String getRandomCarId(){
        StringBuffer sb = new StringBuffer(10);
        sb.append("CC_"+sdf3.format(new Date()));
        sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取随机Id -用于订单Id
    public static String getRandomOrderId(){
        StringBuffer sb = new StringBuffer(10);
        sb.append("OO_"+sdf3.format(new Date()));
        sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取随机Id -用于回执Id
    public static String getRandomReturnId(){
        StringBuffer sb = new StringBuffer(10);
        sb.append("RE_"+sdf3.format(new Date()));
        sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取随机Id -用于反馈Id
    public static String getRandomBackId(Integer type){
        StringBuffer sb = new StringBuffer(10);
        if(type.equals(0)){
            //0是车辆故障 即车辆反馈
            sb.append("AD_CAR_"+sdf3.format(new Date()));
        }else {
            //1为建议 即用户反馈
            sb.append("AD_USER_"+sdf3.format(new Date()));
        }
        sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
        sb.append(random.nextInt(9000)+1000);
        return sb.toString();
    }

    //获取日志Id -用于日志Id
    public static String getRandomLogId(String type){
        StringBuffer sb = new StringBuffer(10);
        sb.append("LOG_"+type+"_"+sdf3.format(new Date()));
        sb.append(Character.toString(chars.charAt((int)(Math.random() * 26))).toUpperCase());
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
