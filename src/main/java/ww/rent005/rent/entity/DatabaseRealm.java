package ww.rent005.rent.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import ww.rent005.rent.service.PermissionService;
import ww.rent005.rent.service.RoleService;
import ww.rent005.rent.service.UserService;

/**
 * @ClassName: DatabaseRealm
 * @Author: cronos
 * @Date: 2020/1/30 11:18
 * @Version: 1.0
 **/
public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*//能进入到这里，表示账号已经通过验证了
        String userName =(String) principalCollection.getPrimaryPrincipal();
        //通过service获取角色和权限
        Set<String> permissions = permissionService.listPermissions(userName);
        Set<String> roles = roleService.listRoles(userName);

        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;*/
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //根据用户名查询登录用户
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_name",authenticationToken.getPrincipal().toString());
        User user = userService.getOne(qw);

        if(user != null){
            ActiverUser aUser = new ActiverUser();
            aUser.setUser(user);
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(aUser, user.getPassWord(), credentialsSalt,
                    this.getClass().getSimpleName());
            return info;
        }
        return null;
    }
}
