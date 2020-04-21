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
 * @ClassName: Car
 * @Author: cronos
 * @Date: 2020/3/30 14:23
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_car")
public class Car implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 汽车id
     */
    @TableId(value = "car_id", type = IdType.INPUT)
    private String carId;

    /**
     * 汽车牌照
     */
    private String carNum;

    /**
     * 汽车类型
     */
    private String carType;

    /**
     * 汽车市值
     */
    private Double buyPrice;

    /**
     * 租用价格/天
     */
    private Double rentPrice;

    /**
     * 租用押金/天
     */
    private Double depositPrice;

    /**
     * 租用状态
     */
    private Integer rentStatus;

    /**
     * 车辆颜色
     */
    private String carColor;

    /**
     * 汽车描述
     */
    private String description;

    /**
     * 汽车图片
     */
    private String carImg;

    /**
     * 车主id
     */
    private String rentUserId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    //用于联合查询车主信息
    private User user;

    //用于显示车辆是否需要维护
    private Integer isRepair;
}
