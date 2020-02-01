package ww.rent005.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ww.rent005.sys.entity.Permission;

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

}
