package ww.rent005.rent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 保存登录用户信息、权限信息、角色信息
 * @ClassName: ActiverUser
 * @Author: cronos
 * @Date: 2020/1/30 11:15
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

    private User user;
    private List<String> permissions;
    private List<String> roles;

}
