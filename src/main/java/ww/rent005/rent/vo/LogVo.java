package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import ww.rent005.rent.entity.Log;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class LogVo extends Log {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;

	//用于接收多个id
	private String ids[];

	//开始事件
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	//结束事件
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endOfTime;

}
