package ww.rent005.sys.controller;


import cn.hutool.core.util.IdUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.sys.common.Constast;
import ww.rent005.sys.common.Result;
import ww.rent005.sys.service.UserService;
import ww.rent005.sys.vo.UserVo;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public Result addUser(UserVo userVo) {
        try {
            //设置用户类型
            //userVo.setType(Constast.USER_TYPE_NORMAL);
            userVo.setCreateTime(new Date());
            String salt = IdUtil.simpleUUID().toUpperCase();
            //设置盐
            userVo.setSalt(salt);
            //加密3次
            //保存密码 密码拥有默认值
            userVo.setPassWord(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 3).toString());
            this.userService.save(userVo);
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }
    
}

