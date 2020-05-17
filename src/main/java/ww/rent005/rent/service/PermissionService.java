package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Permission;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据菜单/权限ID删除 Role-Permission表/Permission表 信息
     * @param id
     */
    void delPermission(Serializable id);


    /**
     * 根据rid查询拥有的权限
     * @param roleid
     * @return
     */
    List<Permission> findRolePermissionsByRid(Integer roleid);

    /**
     * 根据userName查询拥有的权限
     * @param userName
     * @return
     */
    Set<String> listPermissions(String userName);

    List<Permission> findOwnMenuByUserId(String type, Integer available, String userId);
}
