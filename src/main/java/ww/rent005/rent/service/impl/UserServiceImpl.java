package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.mapper.UserMapper;
import ww.rent005.rent.service.UserService;
import ww.rent005.rent.vo.UserVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public void delUserByUid(String id) {
        //删除角色中间表
        // 删除用户表
        this.getBaseMapper().delUserRoleByUid(id);
        this.removeById(id);
    }

    @Override
    public void deleteBatchUsersByIds(String[] ids) {
        for(String id:ids){
            delUserByUid(id);
        }
    }

    @Override
    public void addRoleUser(UserVo userVo) {
        String uid = userVo.getUserId();
        Integer ids[] = userVo.getRid2();
        //先删除之前的角色权限信息
        this.getBaseMapper().delUserRoleByUid(uid);
        //再添加新的数据
        for(Integer id:ids){
            this.getBaseMapper().addRoleUser(uid,id);
        }
    }

    @Override
    public Integer findUserByUserName(UserVo userVo) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("user_name", userVo.getUserName());
        return this.getBaseMapper().selectCount(qw);
    }

    @Override
    public Integer findUserByNickName(String nickName) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("nick_name", nickName);
        return this.getBaseMapper().selectCount(qw);
    }

    @Override
    public User findUserIdByNickName(String nickName) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("nick_name", nickName);
        return this.getBaseMapper().selectOne(qw);
    }
}
