package ww.rent005.rent.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.*;
import ww.rent005.rent.entity.Role;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.RoleService;
import ww.rent005.rent.service.UserService;
import ww.rent005.rent.vo.UserVo;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    RoleService roleService;
    
    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public Result addUser(UserVo userVo, HttpSession session) {
        //判断添加还是注册
        Boolean flag = false;
        String code = session.getAttribute("code").toString();
        try {
            //判断验证码
            if(userVo.getCode()!=null&&!userVo.getCode().equals(code)){
                return new Result(-1,"验证码错误!",null);
            }
            //用户名已存在
            if(userService.findUserByUserName(userVo)>0){
                return new Result(-1,"该用户名被占用!",null);
            }
            //该昵称已存在
            if(userService.findUserByNickName(userVo.getNickName())>0){
                return new Result(-1,"该昵称已被占用,请换个昵称吧!",null);
            }
            //这里区分用户管理和用户注册
            if(userVo.getType()==null){
                //设置用户类型
                userVo.setType(Constast.USER_TYPE_NORMAL);
                //默认设置类型 表示为注册信息
                flag = true;
            }
            //设置默认昵称
            if(userVo.getNickName()==null&&userVo.getNickName()!=""){
                userVo.setNickName(RandomUtils.getRandomNickName());
            }
            //设置性别 默认为男
            if(userVo.getSex()==null){
                userVo.setSex(1);
            }
            //设置使用状态
            if(userVo.getAvailable()==null){
                userVo.setAvailable(1);
            }
            if(userVo.getImg()==null){
                userVo.setImg("default.png");
            }
            userVo.setUserId(RandomUtils.getRandomId());
            userVo.setCreateTime(new Date());
            userVo.setUpdateTime(new Date());
            String salt = IdUtil.simpleUUID().toUpperCase();
            //设置盐
            userVo.setSalt(salt);
            //加密3次
            //保存密码 密码拥有默认值
            String pwd = null ;
            if(userVo.getPassWord()==null){
                //设置默认密码为123456
                pwd = Constast.USER_DEFAULT_PWD;
            }else {
                pwd = userVo.getPassWord();
            }
            userVo.setPassWord(new Md5Hash(pwd, salt, 3).toString());
            this.userService.save(userVo);
            if(flag){
                return Result.REG_SUCCESS;
            }else {
                return Result.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(flag){
                return Result.REG_ERROR;
            }else {
                return Result.ADD_ERROR;
            }
        }
    }

    /**
     * 查询所有
     * @param userVo
     * @return
     */
    @RequestMapping("loadUsers")
    public DataGrid loadUsers(UserVo userVo) {
        IPage<User> page=new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(userVo.getUserName()), "user_name", userVo.getUserName());
        qw.like(StringUtils.isNotBlank(userVo.getNickName()), "nick_name", userVo.getNickName());
        qw.like(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
        qw.like(StringUtils.isNotBlank(userVo.getPhone()), "phone", userVo.getPhone());
        qw.like(StringUtils.isNotBlank(userVo.getEmail()), "email", userVo.getEmail());
        qw.eq(userVo.getAvailable()!=null, "available", userVo.getAvailable());
        qw.eq(userVo.getSex()!=null, "sex", userVo.getSex());
        qw.eq(userVo.getType()!=null, "type", userVo.getType());
        qw.orderByDesc("create_time");
        this.userService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 修改用户信息
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public Result updateUser(UserVo userVo){
        try {
            User userTemp = this.userService.getById(userVo.getUserId());
            //该昵称已存在
            //如果前后昵称未改变则跳过；前后昵称改变判断是否存在
            if((!userTemp.getNickName().equals(userVo.getNickName()))&&(userService.findUserByNickName(userVo.getNickName())>0)){
                System.out.println(userTemp.getNickName().equals(userVo.getNickName()));
                return new Result(-1,"该昵称已被占用,请换个昵称吧!",null);
            }
            if(userVo.getPassWord()!=null&&userVo.getPassWord()!=""){
                String salt = IdUtil.simpleUUID().toUpperCase();
                //设置盐
                userVo.setSalt(salt);
                //新密码加密
                userVo.setPassWord(new Md5Hash(userVo.getPassWord(), salt, 3).toString());
            }
            //判断是否有图片
            //无图片：用户管理的更新操作/密码修改操作
            //有图片：资料更新：默认不操作/自定义需要更新路径
            if(userVo.getImg()!=null&&!userVo.getImg().equals("default.png")){
                //更新图片后缀路径
                String filePath = UploadController.updateFileName(userVo.getImg(),"photo","-temp");
                //保存路径
                userVo.setImg(filePath);
                //删除之前图片
                UploadController.deleteFileByPath(userTemp.getImg(),"photo");
            }
            userVo.setUpdateTime(new Date());
            this.userService.updateById(userVo);
            User user = this.userService.getById(userVo.getUserId());
            WebUtils.getSession().setAttribute("user", user);
            return Result.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("delUser")
    public Result deleteUser(String id) {
        try {
            this.userService.delUserByUid(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }


    /**
     * 批量删除用户
     * @param userVo
     * @return
     */
    @RequestMapping("delBatchUser")
    public Result delBatchUser(UserVo userVo){
        try {
            this.userService.deleteBatchUsersByIds(userVo.getIds());
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @RequestMapping("restUserPwd")
    public Result restUserPwd(String id) {
        try {
            User user = new User();
            user.setUserId(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            //设置盐
            user.setSalt(salt);
            //加密3次
            //保存密码 密码拥有默认值
            user.setPassWord(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 3).toString());
            this.userService.updateById(user);
            return Result.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.RESET_ERROR;
        }
    }

    /**
     * 加载用户角色json数据
     * @param userVo
     * @return
     */
    @RequestMapping("initUserRoleJsonData")
    public DataGrid initUserRoleJsonData(UserVo userVo){
        //查询所有可用角色
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq("available", Constast.AVAILABLE_TRUE);
        List<Role> roleList = roleService.list(qw);
        //查询当前用户所拥有的角色信息
        List<Role> ThisUserRoles = this.roleService.findUserRoleByUid(userVo.getUserId());

        List<Map<String,Object>> data = new ArrayList<>();

        for(Role r1:roleList){
            //表格数据项被勾选的属性
            Boolean LAY_CHECKED=false;
            for(Role r2:ThisUserRoles){
                if(r1.getId()==r2.getId()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("id", r1.getId());
            map.put("remark", r1.getRemark());
            map.put("name", r1.getName());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGrid(data);
    }

    /**
     * 添加用户角色信息
     * @param userVo
     * @return
     */
    @RequestMapping("addUserRole")
    public Result addUserRole(UserVo userVo){
        try {
            this.userService.addRoleUser(userVo);
            return Result.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DISPATCH_ERROR;
        }
    }

    /**
     * 获取旧密码
     * @param oldPwd
     * @param salt
     * @return
     */
    @RequestMapping("getPassWord")
    public Result addUser(String oldPwd,String salt) {
        String pwd = new Md5Hash(oldPwd, salt, 3).toString();
        return new Result(200,"",pwd);
    }




}

