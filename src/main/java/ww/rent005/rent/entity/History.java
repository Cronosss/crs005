package ww.rent005.rent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Cronos
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_history")
public class History implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 历史id
     */
    @TableId(value = "his_id", type = IdType.AUTO)
    private Integer hisId;

    /**
     * 历史访问数
     */
    private Integer visitCount;
}
