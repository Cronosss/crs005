package ww.rent005.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.sys.common.Constast;
import ww.rent005.sys.common.DataGrid;
import ww.rent005.sys.common.Result;
import ww.rent005.sys.common.TreeNode;
import ww.rent005.sys.entity.Permission;
import ww.rent005.sys.entity.Role;
import ww.rent005.sys.service.PermissionService;
import ww.rent005.sys.service.RoleService;
import ww.rent005.sys.vo.RoleVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询角色
     * @param roleVo
     * @return
     */
    @RequestMapping("loadRoles")
    public DataGrid loadRoles(RoleVo roleVo) {
        IPage<Role> page=new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
        qw.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
        qw.eq(roleVo.getAvailable()!=null, "available", roleVo.getAvailable());
        qw.orderByDesc("create_time");
        this.roleService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @RequestMapping("addRole")
    public Result addRole(RoleVo roleVo) {
        try {
            roleVo.setCreateTime(new Date());
            this.roleService.save(roleVo);
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     * @param roleVo
     * @return
     */
    @RequestMapping("updateRole")
    public Result updateRole(RoleVo roleVo) {
        try {
            this.roleService.updateById(roleVo);
            return Result.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("delRole")
    public Result deleteRole(Integer id) {
        try {
            this.roleService.delRoleByPid(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }


    /**
     * 批量删除角色
     * @param roleVo
     * @return
     */
    @RequestMapping("delBatchRole")
    public Result delBatchRole(RoleVo roleVo){
        try {
            this.roleService.deleteBatchRolesByIds(roleVo.getIds());
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 初始化权限分配树内容
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping("initRolePermissionTreeJsonData")
    public DataGrid initRolePermissionTreeJsonData(Integer roleid){
        //查询所有可用菜单
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Permission> permissionList = permissionService.list(queryWrapper);
        //查询选中用户所拥有可用菜单
        List<Permission> ThisRolePermissions = this.permissionService.findRolePermissionsByRid(roleid);
        //用于放置树数据的集合
        List<TreeNode> data = new ArrayList<>();
        //赋值到树节点中
        for(Permission all:permissionList){
            //判断是否选中
            String checkArr = "0";
            for(Permission own:ThisRolePermissions){
                //表示有该权限
                if(all.getId() == own.getId()){
                    checkArr = "1";
                    break;
                }
            }
            Integer id = all.getId();
            Integer pid = all.getPid();
            String title = all.getTitle();
            Boolean spread = (all.getSpread()==null||all.getSpread()==1)?true:false;
            //放入集合中
            data.add(new TreeNode(id,pid,title,spread,checkArr));
        }
        //返回对应的json格式展示
        return new DataGrid(data);
    }

    /**
     * 添加角色菜单表内容
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping("addRolePermission")
    public Result addRolePermission(Integer rid,Integer[] ids) {
        try {
            this.roleService.addRolePermission(rid,ids);
            return Result.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DISPATCH_ERROR;
        }
    }
}

