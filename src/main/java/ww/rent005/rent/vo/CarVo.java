package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.rent.entity.Car;

@Data
@EqualsAndHashCode(callSuper=false)
public class CarVo extends Car {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;

	//用于接收多个汽车id
	private String ids[];

	private Double startPrice;
	private Double endPrice;
}
