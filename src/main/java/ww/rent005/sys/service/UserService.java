package ww.rent005.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.sys.entity.User;
import ww.rent005.sys.vo.UserVo;

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
}
