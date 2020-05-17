package ww.rent005.rent.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.ActiverUser;
import ww.rent005.rent.entity.History;
import ww.rent005.rent.entity.Log;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.HistoryService;
import ww.rent005.rent.service.LogService;
import ww.rent005.rent.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: LoginController
 * @Author: cronos
 * @Date: 2020/1/30 12:18
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;
    @Autowired
    LogService logService;

    @ResponseBody
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
            //设置登录状态
            User user = activerUser.getUser();
            user.setLoginStatus(1);
            this.userService.updateById(user);
            //修改历史表 人数+1
            History his = this.historyService.getById(1);
            his.setVisitCount(his.getVisitCount()+1);
            this.historyService.updateById(his);

            //这里session重复了。。暂时无视。。
            WebUtils.getSession().setAttribute("user", user);

            //记录登陆日志
            Log log = new Log();
            log.setCreateTime(new Date());
            log.setLogType("登录");
            if(user.getType()==1){
                log.setLogContent("【管理员】 "+user.getNickName()+"("+user.getUserName()+") 在"+WebUtils.getRequest().getRemoteAddr()+"登录.");
            }else {
                log.setLogContent("【用户】 "+user.getNickName()+"("+user.getUserName()+") 在"+WebUtils.getRequest().getRemoteAddr()+"登录.");
            }
            log.setLogId(RandomUtils.getRandomLogId("IP"));
            this.logService.save(log);
            return Result.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return Result.LOGIN_ERROR_PASS;
        }
    }


    /**
     * 退出
     * 注：没有使用Shiro的退出 这里使用控制器完成一些操作
     * @return
     */
    @RequestMapping("logGo")
    public String logGo(){
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
        //修改用户登录状态
        activerUser.getUser().setLoginStatus(0);
        this.userService.updateById(activerUser.getUser());
        subject.logout();
        WebUtils.getSession().removeAttribute("user");
        return "redirect:/rent/index/login";
    }


    /**
     * 获得验证码
     */
    @ResponseBody
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
    @ResponseBody
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
