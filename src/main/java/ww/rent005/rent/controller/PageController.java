package ww.rent005.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PageController
 * @Author: cronos
 * @Date: 2020/1/30 12:11
 * @Version: 1.0
 **/
@Controller
@RequestMapping("rent")
public class PageController {

    /**
     * 至登录界面
     * @return
     */
    @RequestMapping("login")
    public String toLogin(){
        return "rent/index/login";
    }

    /**
     * 至注册界面
     * @return
     */
    @RequestMapping("register")
    public String toRegister(){
        return "rent/index/register";
    }

    /**
     * 至忘记密码界面
     * @return
     */
    @RequestMapping("forget")
    public String toForget(){
        return "rent/index/forget";
    }


    /**
     * 至首页界面
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "rent/index/index";
    }

    /**
     * 至主页工作台
     * @return
     */
    @RequestMapping("toConsole")
    public String toConsole(){
        return "rent/index/console";
    }

    /**
     * 至权限管理页面
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
     * 至密码修改页面
     */
    @RequestMapping("toUserPwd")
    public String toUserPwd(){
        return "rent/user/userPwd";
    }

    /**
     * 至用户信息页面
     */
    @RequestMapping("toUserInfo")
    public String toUserInfo(){
        return "rent/user/userInfo";
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
     * 至订单管理页面
     * @return
     */
    @RequestMapping("toOrderManager")
    public String toOrderManager(){
        return "rent/order/orderManager";
    }

    /**
     * 至回执单管理页面
     * @return
     */
    @RequestMapping("toReturnManager")
    public String toReturnManager(){
        return "rent/return/returnManager";
    }

    /**
     * 至用户建议管理页面
     * @return
     */
    @RequestMapping("toUserBackManager")
    public String toUserBackManager(){
        return "rent/back/userBackManager";
    }

    /**
     * 至车辆故障管理页面
     * @return
     */
    @RequestMapping("toCarBackManager")
    public String toCarBackManager(){
        return "rent/back/carBackManager";
    }

    /**
     * 至我的反馈页面
     * @return
     */
    @RequestMapping("toMyBackManager")
    public String toMyBackManager(){
        return "rent/user/myBackManager";
    }

    /**
     * 至租车页面
     * @return
     */
    @RequestMapping("toCarRentManager")
    public String toCarRentManager(){
        return "rent/work/carRentManager";
    }

    /**
     * 至还车页面
     * @return
     */
    @RequestMapping("toCarReturnManager")
    public String toCarReturnManager(){
        return "rent/work/carReturnManager";
    }

    /**
     * 至类型管理页面
     * @return
     */
    @RequestMapping("toTypeManager")
    public String toTypeManager(){
        return "rent/type/typeManager";
    }

    /**
     * 至日志管理页面
     * @return
     */
    @RequestMapping("toLogManager")
    public String toLogManager(){
        return "rent/log/logManager";
    }

    /**
     * 至信息页面
     * @return
     */
    @RequestMapping("toMessageManager")
    public String toMessageManager(){
        return "rent/mes/messageManager";
    }

    /**
     * 至公告管理页面
     * @return
     */
    @RequestMapping("toArticleManager")
    public String toArticleManager(){
        return "rent/article/articleManager";
    }

}
