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
@TableName("tb_return")
public class Return implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 归还id
     */
    @TableId(value = "return_id", type = IdType.INPUT)
    private String returnId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 汽车id
     */
    private String carNum;

    /**
     * 归还人id
     */
    private String nickName;

    /**
     * 预付价格
     */
    private Double estimatedPrice;

    /**
     * 实际支付
     */
    private Double actualPrice;

    /**
     * 附加说明 逾期/未逾期附加金额
     */
    private String moreInfo;

    /**
     * 反馈类别：汽车/用户
     */
    private Integer adType;

    /**
     * 反馈内容
     */
    private String advice;

    /**
     * 创建时间/归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;


}
