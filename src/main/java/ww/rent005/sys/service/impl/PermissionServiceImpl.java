package ww.rent005.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.sys.common.Constast;
import ww.rent005.sys.entity.Permission;
import ww.rent005.sys.mapper.PermissionMapper;
import ww.rent005.sys.service.PermissionService;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    public void delPermission(Serializable id){
        PermissionMapper pm = this.getBaseMapper();
        //删除Role-Permission表信息
        pm.delRolePermissinByPid(id);
        //删除Permission表信息
        this.removeById(id);
    }


    @Override
    public List<Permission> findRolePermissionsByRid(Integer roleid) {
        //根据rid查询拥有的权限
        return this.getBaseMapper().findRolePermissionsByRid((Integer) Constast.AVAILABLE_TRUE,roleid);
    }
}
