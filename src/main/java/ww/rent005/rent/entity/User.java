package ww.rent005.rent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id",type = IdType.INPUT)
    private String userId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @TableField(value = "pass_word")
    private String passWord;

    /**
     * 称呼
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 性别 0-女,1-男
     */
    private Integer sex;

    /**
     * 所在地
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 类别 1-超级管理员,2-系统用户
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer available;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 用户头像
     */
    private String img;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 3.27 增加字段
     * updateTime 用于后续统计活跃情况
     * email 用于修改密码验证
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    private String email;

}
