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
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;

    /**
     * 租用者
     */
    private String nickName;

    /**
     * 汽车车牌
     */
    private String carNum;

    /**
     * 租用天数
     */
    private Integer dayCount;

    /**
     * 租用价格
     */
    private Double orderPrice;

    /**
     * 押金金额
     */
    private Double orderDeposit;

    /**
     * 预计金额
     */
    private Double totalPrice;

    /**
     * 是否归还 已还/未还
     */
    private Integer orderStatus;

    /**
     * 是否逾期 正常/逾期
     */
    private Integer isOver;

    /**
     * 租借时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 预还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;


}
