package ww.rent005.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: PageController
 * @Author: cronos
 * @Date: 2020/1/30 12:11
 * @Version: 1.0
 **/
@Controller
@RequestMapping("sys")
public class PageController {

    /**
     * 至登录界面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "rent/index/login";
    }

    /**
     * 至登录界面
     * @return
     */
    @RequestMapping("toIndex")
    public String toIndex(){
        return "rent/index/index";
    }

    /**
     * 至工作台
     * @return
     */
    @RequestMapping("toConsole")
    public String toConsole(){
        return "rent/index/console";
    }


    /**
     * 获得验证码
     */
    @RequestMapping("getCode")
    public void getCdoe(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(115, 35);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }
}
