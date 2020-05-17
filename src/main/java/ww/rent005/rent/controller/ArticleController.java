package ww.rent005.rent.controller;


import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.Article;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.ArticleService;
import ww.rent005.rent.vo.ArticleVo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            User user = (User) WebUtils.getSession().getAttribute("user");
            if(articleVo.getArticleContent()==null||articleVo.getArticleContent()==""){
                return new Result(-1,"公告内容不能为空,请重新输入",null);
            }else {
                articleVo.setSendName(user.getNickName());
                articleVo.setCreateTime(new Date());
                articleVo.setArticleId(RandomUtils.getRandomArticleId());
                this.articleService.save(articleVo);
                return Result.ADD_SUCCESS;
            }
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
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(articleVo.getPage(), articleVo.getLimit());
        List<Article> logList = this.articleService.findAllArticles(articleVo);
        return new DataGrid(page.getTotal(), logList);
    }


    /**
     * 修改
     * @param articleVo
     * @return
     */
    @RequestMapping("updateArticle")
    public Result updateReturn(ArticleVo articleVo) {
        try {
            Article article = this.articleService.getById(articleVo);
            if(article.getArticleTitle().equals(articleVo.getArticleTitle())&&article.getArticleContent().equals(articleVo.getArticleContent())){
                return new Result(-1,"修改失败,无修改操作",null);
            }else {
                //需要判断操作员
                User user = (User) WebUtils.getSession().getAttribute("user");
                String[] senders = user.getNickName().split(" ");
                Boolean flag = false;
                for (String name:senders){
                    if (user.getNickName()==name)
                        flag = true;
                }
                //如果当前用户操作的人不在发布者里面 则添加
                if(!flag){
                    articleVo.setSendName(articleVo.getSendName()+" "+user.getNickName());
                }
                this.articleService.updateById(articleVo);
                return Result.UPDATE_SUCCESS;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     * @param articleId
     * @return
     */
    @RequestMapping("getArticle")
    public Article getArticle(String articleId){
        return this.articleService.getById(articleId);
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
