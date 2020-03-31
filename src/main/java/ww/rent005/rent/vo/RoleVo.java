package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.rent.entity.Role;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends Role {

	private static final long serialVersionUID = 1L;
	
	private Integer page=1;
	private Integer limit=10;
	//用于接收多个角色id
	private Integer ids[];
	
}
