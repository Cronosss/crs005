package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.Article;
import ww.rent005.rent.mapper.ArticleMapper;
import ww.rent005.rent.service.ArticleService;
import ww.rent005.rent.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> findAllArticles(ArticleVo articleVo) {
        return this.baseMapper.findAllArticles(articleVo);
    }

    @Override
    public List<Article> getNewArticle() {
        return this.baseMapper.getNewArticle();
    }
}
