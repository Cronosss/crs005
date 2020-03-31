package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
public interface UserService extends IService<User> {

    void delUserByUid(String id);

    void deleteBatchUsersByIds(String[] ids);

    void addRoleUser(UserVo userVo);

    //查重检查
    Integer findUserByUserName(UserVo userVo);
    //查重检查
    Integer findUserByNickName(UserVo userVo);
}
