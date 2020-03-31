package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.rent.entity.Permission;

/**
 * @ClassName: PermissionVo
 * @Author: cronos
 * @Date: 2020/1/30 14:40
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class PermissionVo extends Permission {

    private static final long serialVersionUID = 1L;
    private Integer page=1;
    private Integer limit=10;
}
