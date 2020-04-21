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
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.Back;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.BackService;
import ww.rent005.rent.vo.BackVo;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/back")
public class BackController {

    @Autowired
    BackService backService;

    /**
     * 添加反馈
     */
    @RequestMapping("addBack")
    public Result addReturn(BackVo backVo) {
        try {

            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param backVo
     * @return
     */
    @RequestMapping("loadBacks")
    public DataGrid loadReturns(BackVo backVo) {
        IPage<Back> page=new Page<>(backVo.getPage(), backVo.getLimit());
        QueryWrapper<Back> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(backVo.getBackId()), "back_id", backVo.getBackId());
        qw.like(StringUtils.isNotBlank(backVo.getUserName()), "user_name", backVo.getUserName());
        qw.like(StringUtils.isNotBlank(backVo.getAdvice()), "advice", backVo.getAdvice());
        qw.like(StringUtils.isNotBlank(backVo.getMoreInfo()), "more_info", backVo.getMoreInfo());
        qw.eq(backVo.getAdType()!=null, "ad_type", backVo.getAdType());
        qw.eq(backVo.getIsSolve()!=null, "is_solve", backVo.getIsSolve());
        qw.orderByDesc("is_solve","update_time","date_time");
        this.backService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 查询所有
     * @param backVo
     * @return
     */
    @RequestMapping("loadBacksByUser")
    public DataGrid loadBacksByUser(BackVo backVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Back> page=new Page<>(backVo.getPage(), backVo.getLimit());
        QueryWrapper<Back> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(backVo.getBackId()), "back_id", backVo.getBackId());
        qw.eq(backVo.getIsSolve()!=null, "is_solve", backVo.getIsSolve());
        qw.eq("user_name",user.getNickName());
        qw.eq(backVo.getAdType()!=null, "ad_type", backVo.getAdType());
        qw.like(StringUtils.isNotBlank(backVo.getAdvice()), "advice", backVo.getAdvice());
        qw.like(StringUtils.isNotBlank(backVo.getMoreInfo()), "more_info", backVo.getMoreInfo());
        qw.orderByDesc("is_solve","update_time","date_time");
        this.backService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 修改反单
     * @param backVo
     * @return
     */
    @RequestMapping("updateBack")
    public Result updateReturn(BackVo backVo) {
        try {

            return Result.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 删除订单信息
     * @param id
     * @return
     */
    @RequestMapping("delBack")
    public Result delReturn(String id) {
        try {
            this.backService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除订单信息
     * @param backVo
     * @return
     */
    @RequestMapping("delBatchBack")
    public Result delBatchReturn(BackVo backVo) {
        try {
            String[] ids = backVo.getIds();
            this.backService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

}

