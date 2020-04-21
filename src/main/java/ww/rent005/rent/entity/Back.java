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
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_back")
public class Back implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 反馈id
     */
    @TableId(value = "back_id", type = IdType.INPUT)
    private String backId;

    /**
     * 返还单id
     */
    private String returnId;

    /**
     * 反馈类别 建议/故障
     */
    private Integer adType;

    /**
     * 如果为车辆故障 则为车辆车牌
     */
    private String moreInfo;

    /**
     * 反馈内容
     */
    private String advice;

    /**
     * 反馈人 nick_name
     */
    private String userName;

    /**
     * 是否解决
     */
    private Integer isSolve;

    /**
     * 解决人 nick_name
     */
    private String solveName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "date_time")
    private Date dateTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;


}
