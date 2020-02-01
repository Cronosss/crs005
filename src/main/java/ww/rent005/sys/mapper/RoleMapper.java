package ww.rent005.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ww.rent005.sys.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色id删除角色/权限表
     * @param id
     */
    void delRolePermissionByRid(Serializable id);

    /**
     * 根据角色id删除角色/用户表
     * @param id
     */
    void delRoleUserByRid(Serializable id);

    /**
     * 添加角色权限信息
     * @param rid
     * @param pid
     */
    void addRolePermission(@Param("rid") Integer rid,@Param("pid") Integer pid);

    /**
     * 根据uid查询角色信息
     * @param available
     * @param uid
     * @return
     */
    List<Role> findUserRoleByUid(@Param("available") Integer available, @Param("uid") String uid);
}
