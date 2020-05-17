package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ww.rent005.rent.entity.Article;
import ww.rent005.rent.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findAllArticles(ArticleVo articleVo);

    List<Article> getNewArticle();

}
