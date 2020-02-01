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
     * 至权限页面
     * @return
     */
    @RequestMapping("toPermissionManager")
    public String toPermission(){
        return "rent/permission/permission";
    }

    /**
     * 至菜单页面
     * @return
     */
    @RequestMapping("toMenuManager")
    public String toMenu(){
        return "rent/menu/menu";
    }

    /**
     * 菜单管理左侧树
     */
    @RequestMapping("toMenuLeftTree")
    public String toMenuLeftTree(){
        return "rent/menu/menuLeftTree";
    }
    /**
     * 菜单管理右侧管理列表
     */
    @RequestMapping("toMenuRightPage")
    public String toMenuRightPage(){
        return "rent/menu/menuRightPage";
    }

    /**
     * 权限管理左侧树
     */
    @RequestMapping("toPermissionLeftTree")
    public String toPermissionLeftTree(){
        return "rent/permission/permissionLeftTree";
    }

    /**
     * 权限管理右侧管理列表
     */
    @RequestMapping("toPermissionRightPage")
    public String toPermissionRightPage(){
        return "rent/permission/permissionRightPage";
    }

    /**
     * 至角色管理页面
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "rent/role/roleManager";
    }

    /**
     * 至用户管理页面
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "rent/user/userManager";
    }

    /**
     * 至车辆管理页面
     */
    @RequestMapping("toCarManager")
    public String toCarManager(){
        return "rent/car/carManager";
    }

    /**
     * 至客户管理页面
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "rent/customer/customerManager";
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
