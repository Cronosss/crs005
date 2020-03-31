package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.common.Constast;
import ww.rent005.rent.entity.Role;
import ww.rent005.rent.mapper.RoleMapper;
import ww.rent005.rent.service.RoleService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-01-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    /**
     * 根据pid删除角色
     * @param id
     */
    @Override
    public void delRoleByPid(Serializable id) {
        //删除 角色表、角色权限表、角色用户表
        this.removeById(id);
        this.getBaseMapper().delRolePermissionByRid(id);
        this.getBaseMapper().delRoleUserByRid(id);
    }

    @Override
    public void deleteBatchRolesByIds(Integer[] ids) {
        for(Integer id:ids){
            delRoleByPid(id);
        }
    }


    @Override
    public void addRolePermission(Integer rid, Integer[] ids) {
        //先删除之前的角色权限信息
        this.getBaseMapper().delRolePermissionByRid(rid);
        //再添加新的数据
        for(Integer id:ids){
            this.getBaseMapper().addRolePermission(rid,id);
        }
    }

    @Override
    public List<Role> findUserRoleByUid(String userId) {
        //根据rid查询拥有的权限
        return this.getBaseMapper().findUserRoleByUid((Integer) Constast.AVAILABLE_TRUE,userId);
    }
}
