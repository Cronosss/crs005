package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ww.rent005.rent.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 删除用户角色中间表
     * @param uid
     */
    void delUserRoleByUid(String uid);

    /**
     * 添加用户角色中间表信息
     * @param uid
     * @param id
     */
    void addRoleUser(@Param("uid") String uid, @Param("rid") Integer id);

    /**
     * 根据id查询用户信息 -用于联合查询
     * @param userId
     * @return
     */
    User findByUserId(String userId);

}
