package ww.rent005.rent.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: UploadController
 * @Author: cronos
 * @Date: 2020/3/29 20:46
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 保存头像
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadPhoto")
    public Result uploadPhoto(@RequestParam("mf") MultipartFile file) throws IllegalStateException, IOException {
        return uploadCommon("photo",file);
    }

    /**
     * 保存车辆图片
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadCarPhoto")
    public Result uploadCarPhoto(@RequestParam("mf") MultipartFile file) throws IllegalStateException, IOException {
        return uploadCommon("car",file);
    }

    private Result uploadCommon(String type,MultipartFile file) throws IOException {
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String urlPath = staticPath + "/resources/pictures/"+type;
        // 以当前年月作为文件夹名称
        String dirName = RandomUtils.getCurrentDateString();
        // 构造文件夹对象
        File dirFile = new File(urlPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        //获取文件名
        String oldFileName = file.getOriginalFilename();
        // 根据文件原名得到新名 以-temp结尾 做临时存储
        String newFileName = RandomUtils.createFileNameUseTime(oldFileName,"-temp");
        File file1 = new File(dirFile,newFileName);
        //将临时存储的文件移动到真实存储路径下
        file.transferTo(file1);
        //封装图片路径数据
        return new Result(200,"",dirName+"/"+newFileName);
    }


    /**
     * 更改文件名 -用于保存图片时更新文件名
     * @param img
     */
    public static String updateFileName(String img,String type,String suffix) {
        //找到文件
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String urlPath = staticPath + "/resources/pictures/"+type;
        //绝对路径+相对路径寻找文件对象
        try {
            File file=new File(urlPath,img);
            if(file.exists()) {
                //如果文件存在，则替换后缀，并返回相对路径
                file.renameTo(new File(urlPath,img.replace(suffix, "")));
                return img.replace(suffix, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除图片
     * @param path
     */
    public static void deleteFileByPath(String path,String type) {
        //找到文件
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String urlPath = staticPath + "/resources/pictures/" + type + "/" + path;
        //根据文件
        File file=new File(urlPath);
        if(file.exists()) {
            file.delete();
        }
    }

    /**
     * 下载图片
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("downloadPhoto")
    public ResponseEntity<Object> downloadFile(String path,String type, HttpServletResponse response) {
        String oldName = "";
        if(!path.startsWith("default")){
            oldName = path.substring(path.indexOf('/'),path.length());
        }else {
            oldName = path;
        }
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String urlPath = staticPath + "/resources/pictures/"+type;
        //绝对路径+相对路径寻找文件对象
        File file=new File(urlPath,path);
        //判断文件是否存在
        if(file.exists()) {
            try {
                //file转bytes
                byte [] bytes= FileUtils.readFileToByteArray(file);
                HttpHeaders header=new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的文件的名称
                header.setContentDispositionFormData("attachment", oldName);
                //创建ResponseEntity对象
                ResponseEntity<Object> entity=
                        new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
                return entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }else {
            PrintWriter out;
            try {
                out = response.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

}
