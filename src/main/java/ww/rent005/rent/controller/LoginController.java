package ww.rent005.rent.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.ActiverUser;
import ww.rent005.rent.entity.User;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: LoginController
 * @Author: cronos
 * @Date: 2020/1/30 12:18
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("login")
    public Result login(String userName, String passWord,String code,HttpSession session) {
        String codeSession = session.getAttribute("code").toString();
        //判断验证码
        if(codeSession!=null&&!code.equals(codeSession)){
            return new Result(-1,"验证码错误!",null);
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(userName, passWord);
        try {
            subject.login(token);
            ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
            //记录登陆日志
/*            Loginfo entity=new Loginfo();
            entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
            entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
            entity.setLogintime(new Date());
            loginfoService.save(entity);*/
            return Result.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return Result.LOGIN_ERROR_PASS;
        }
    }


    /**
     * 获得验证码
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        //CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(115, 35, 4, 20);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        //ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(115, 35, 4, 4);
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(125, 35,4,25);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }

    /**
     * 获取登录信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getLogin",method = RequestMethod.POST)
    public Result getLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return new Result(200,"获取用户信息成功",user);
        }else {
            return new Result(-1,"获取用户信息失败",null);
        }
    }
    
}
