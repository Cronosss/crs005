package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.rent.entity.Message;

@Data
@EqualsAndHashCode(callSuper=false)
public class MessageVo extends Message {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;

	//用于接收多个id
	private String ids[];

}
