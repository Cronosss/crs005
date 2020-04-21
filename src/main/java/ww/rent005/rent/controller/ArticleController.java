package ww.rent005.rent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.entity.Article;
import ww.rent005.rent.service.ArticleService;
import ww.rent005.rent.vo.ArticleVo;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 添加公告
     */
    @RequestMapping("addArticle")
    public Result addReturn(ArticleVo articleVo) {
        try {

            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param articleVo
     * @return
     */
    @RequestMapping("loadArticles")
    public DataGrid loadReturns(ArticleVo articleVo) {
        IPage<Article> page=new Page<>(articleVo.getPage(), articleVo.getLimit());
        QueryWrapper<Article> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(articleVo.getArticleId()), "article_id", articleVo.getArticleId());
        qw.like(StringUtils.isNotBlank(articleVo.getArticleTitle()), "article_title", articleVo.getArticleTitle());
        qw.like(StringUtils.isNotBlank(articleVo.getArticleContent()), "article_content", articleVo.getArticleContent());
        qw.like(StringUtils.isNotBlank(articleVo.getSendName()), "send_name", articleVo.getSendName());
        qw.orderByDesc("create_time");
        this.articleService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }


    /**
     * 修改
     * @param articleVo
     * @return
     */
    @RequestMapping("updateArticle")
    public Result updateReturn(ArticleVo articleVo) {
        try {

            return Result.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delArticle")
    public Result delReturn(String id) {
        try {
            this.articleService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除信息
     * @param articleVo
     * @return
     */
    @RequestMapping("delBatchArticle")
    public Result delBatchReturn(ArticleVo articleVo) {
        try {
            String[] ids = articleVo.getIds();
            this.articleService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}
