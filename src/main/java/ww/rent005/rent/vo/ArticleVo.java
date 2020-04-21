package ww.rent005.rent.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ww.rent005.rent.entity.Article;

@Data
@EqualsAndHashCode(callSuper=false)
public class ArticleVo extends Article {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;

	//用于接收多个id
	private String ids[];

}
