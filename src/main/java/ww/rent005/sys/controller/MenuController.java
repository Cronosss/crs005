package ww.rent005.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.sys.common.*;
import ww.rent005.sys.entity.Permission;
import ww.rent005.sys.entity.User;
import ww.rent005.sys.service.PermissionService;
import ww.rent005.sys.vo.PermissionVo;

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
     * 菜单管理左侧菜单树 注意其json格式与总菜单格式是不同的
     * 其中JSON数据需要parentId 在TreeNode类中已加上@JsonProperty("parentId")
     * @param permissionVo
     * @return
     */
    @ResponseBody
    @RequestMapping("loadIndexLeftPermissionJson")
    public DataGrid loadIndexLeftPermissionJsonData(PermissionVo permissionVo){
        //查询所有菜单
        QueryWrapper<Permission> qw = new QueryWrapper<>();
        //查询菜单类别
        qw.eq("type", Constast.TYPE_MNEU);
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
    
}
