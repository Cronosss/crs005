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
import ww.rent005.rent.entity.Return;
import ww.rent005.rent.service.ReturnService;
import ww.rent005.rent.vo.ReturnVo;

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
@RequestMapping("/return")
public class ReturnController {

    @Autowired
    ReturnService returnService;


    /**
     * 添加反单
     */
    @RequestMapping("addReturn")
    public Result addReturn(ReturnVo returnVo) {
        try {
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }


    /**
     * 查询所有
     * @param returnVo
     * @return
     */
    @RequestMapping("loadReturns")
    public DataGrid loadReturns(ReturnVo returnVo) {
        IPage<Return> page=new Page<>(returnVo.getPage(), returnVo.getLimit());
        QueryWrapper<Return> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(returnVo.getReturnId()), "return_id", returnVo.getReturnId());
        qw.like(StringUtils.isNotBlank(returnVo.getOrderId()), "order_id", returnVo.getOrderId());
        qw.like(StringUtils.isNotBlank(returnVo.getCarNum()), "car_num", returnVo.getCarNum());
        qw.like(StringUtils.isNotBlank(returnVo.getNickName()), "nick_name", returnVo.getNickName());
        qw.orderByDesc("create_time");
        this.returnService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 修改反单
     * @param returnVo
     * @return
     */
    @RequestMapping("updateReturn")
    public Result updateReturn(ReturnVo returnVo) {
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
    @RequestMapping("delReturn")
    public Result delReturn(String id) {
        try {
            this.returnService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除订单信息
     * @param returnVo
     * @return
     */
    @RequestMapping("delBatchReturn")
    public Result delBatchReturn(ReturnVo returnVo) {
        try {
            String[] ids = returnVo.getIds();
            this.returnService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}

