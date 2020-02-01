package ww.rent005.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.sys.entity.User;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends User {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	//用于接收多个用户id
	private String ids[];
	//用于接收角色id值
	private Integer rid2[];
	
}
