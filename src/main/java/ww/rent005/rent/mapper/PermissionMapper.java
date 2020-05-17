package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ww.rent005.rent.entity.Permission;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据菜单/权限ID删除 角色菜单表 信息
     * @param id
     */
    void delRolePermissinByPid(@Param("id") Serializable id);


    /**
     * 根据rid查询拥有的权限
     * @param rid
     * @return
     */
    List<Permission> findRolePermissionsByRid(@Param("available") Integer available, @Param("rid") Integer rid);

    /**
     * 根据userName查询拥有的权限
     * @param userName
     * @return
     */
    Set<String> listPermissions(String userName);

    List<Permission> findOwnMenuByUserId(@Param("type") String type,@Param("available")  Integer available, @Param("userId")String userId);
}
