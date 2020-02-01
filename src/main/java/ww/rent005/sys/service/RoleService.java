package ww.rent005.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.sys.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据pid删除角色
     * @param id
     */
    void delRoleByPid(Serializable id);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteBatchRolesByIds(Integer ids[]);

    /**
     * 添加角色菜单表内容
     * @param rid
     * @param ids
     */
    void addRolePermission(Integer rid, Integer[] ids);

    /**
     * 根据用户id查询拥有的角色
     * @param userId
     * @return
     */
    List<Role> findUserRoleByUid(String userId);
}
