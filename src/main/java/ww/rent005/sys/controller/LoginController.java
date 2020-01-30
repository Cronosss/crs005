package ww.rent005.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.sys.common.Result;
import ww.rent005.sys.common.WebUtils;
import ww.rent005.sys.entity.ActiverUser;

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
    public Result login(String userName, String passWord) {
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
    
}
