package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Article;
import ww.rent005.rent.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface ArticleService extends IService<Article> {

    List<Article> findAllArticles(ArticleVo articleVo);

    List<Article> getNewArticle();
}
