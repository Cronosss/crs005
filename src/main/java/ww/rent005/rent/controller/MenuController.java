package ww.rent005.rent.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.*;
import ww.rent005.rent.entity.Permission;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.PermissionService;
import ww.rent005.rent.vo.PermissionVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuController
 * @Author: cronos
 * @Date: 2020/1/30 14:36
 * @Version: 1.0
 **/
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    private PermissionService permissionService;


    /**
     * 加载左侧菜单栏 注意json格式
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadIndexLeftPermissionJson")
    public DataGrid loadIndexLeftPermissionJsonData(PermissionVo permissionVo){
        //查询所有菜单
        QueryWrapper<Permission> qw = new QueryWrapper<>();
        //查询菜单类别
        qw.eq("type", Constast.TYPE_MENU);
        //查询可用得
        qw.eq("available",Constast.AVAILABLE_TRUE);

        //获取当前对象
        User user = (User) WebUtils.getSession().getAttribute("user");
        //设置权限
        List<Permission> permissionList = null;

        //如果为超级用户
        if(user.getType() == Constast.USER_TYPE_SUPER) {
            permissionList = permissionService.list(qw);
        }else {
            //根据用户ID+角色+权限查询
            permissionList = permissionService.list(qw);
        }

        //将数据放入Tree中
        List<TreeNode> nodes = new ArrayList<>();
        //putPermissionsInTree(permissions,nodes)是把菜单数据添加到树数据中
        //该方法是整理treeNode
        List<TreeNode> dataList = TreeNodesBuilder.builderTree(putPermissionsInTree(permissionList,nodes));
        return new DataGrid(dataList);
    }

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
     * 菜单管理
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
        qw.eq("type",Constast.TYPE_MENU);
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
    @RequestMapping("loadMenus")
    public DataGrid loadAllMenu(PermissionVo permissionVo) {
        IPage<Permission> page=new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> qw=new QueryWrapper<>();
        //查询菜单
        qw.eq("type", Constast.TYPE_MENU);
        //根据菜单名称模糊查询
        qw.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        //根据id查询
        qw.eq(permissionVo.getId()!=null, "id", permissionVo.getId())
                .or()
                .eq(permissionVo.getId()!=null,"pid", permissionVo.getId());
        permissionService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 添加菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public Result addMenu(PermissionVo permissionVo) {
        try {
            //添加类型设置为菜单
            permissionVo.setType(Constast.TYPE_MENU);
            permissionService.save(permissionVo);
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public Result updateMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return Result.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 查询是否有子节点 用于删除
     */
    @RequestMapping("checkIsParentTree")
    public Result checkIsParentTree(PermissionVo permissionVo){
        Result rs  = null;
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if(list.size()>0) {
            rs = new Result(1,"有子节点",null);
        }else {
            rs = new Result(0,"无子节点",null);
        }
        return rs;
    }

    /**
     * 删除菜单
     * 注：删除两个表
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenuById")
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
