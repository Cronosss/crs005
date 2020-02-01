package ww.rent005.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.sys.common.Constast;
import ww.rent005.sys.common.DataGrid;
import ww.rent005.sys.common.Result;
import ww.rent005.sys.common.TreeNode;
import ww.rent005.sys.entity.Permission;
import ww.rent005.sys.service.PermissionService;
import ww.rent005.sys.vo.PermissionVo;

import java.util.ArrayList;
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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 菜单数据放入树数据
     * @param permissions
     * @param nodes
     * @return
     */
    public List<TreeNode> putPermissionsInTree(List<Permission> permissions, List<TreeNode> nodes){
        for(Permission mData :permissions){
            Integer id = mData.getId();
            Integer pid = mData.getPid();
            String title = mData.getTitle();
            String icon = mData.getIcon();
            String href = mData.getHref();
            Boolean spread = mData.getSpread()== Constast.SPREAD_TRUE?true:false;
            String target = mData.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return nodes;
    }

    /**
     * 权限管理
     */
    /**
     * 菜单管理左侧菜单树 注意其json格式与总菜单格式是不同的
     * 其中JSON数据需要parentId 在TreeNode类中已加上@JsonProperty("parentId")
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJsonData")
    public DataGrid loadMenuLeftTreeDataJson(PermissionVo permissionVo){
        QueryWrapper<Permission> qw = new QueryWrapper<>();
        //查询菜单
        qw.eq("type", Constast.TYPE_MNEU);
        List<Permission> list = permissionService.list(qw);
        //将数据放入Tree中
        List<TreeNode> nodes = new ArrayList<>();
        return new DataGrid(putPermissionsInTree(list,nodes));
    }

    /**
     * 用于加载菜单列表
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadPermission")
    public DataGrid loadPermission(PermissionVo permissionVo) {
        IPage<Permission> page=new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> qw=new QueryWrapper<>();
        //查询权限
        qw.eq("type", Constast.TYPE_PERMISSION);
        //根据权限名称模糊查询
        qw.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        //根据id查询
        qw.eq(permissionVo.getId()!=null,"pid", permissionVo.getId());
        permissionService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 添加权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("addPermission")
    public Result addPermission(PermissionVo permissionVo) {
        try {
            //添加类型设置为菜单
            permissionVo.setType(Constast.TYPE_PERMISSION);
            permissionService.save(permissionVo);
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 修改权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("updatePermission")
    public Result updatePermission(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return Result.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 删除权限
     * 注：删除两个表
     * @param permissionVo
     * @return
     */
    @RequestMapping("deletePermissionById")
    public Result deleteMenuById(PermissionVo permissionVo) {
        try {
            permissionService.delPermission(permissionVo.getId());
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}

