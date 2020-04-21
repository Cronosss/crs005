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
import ww.rent005.rent.entity.Message;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.MessageService;
import ww.rent005.rent.vo.MessageVo;

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
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 添加讯息
     */
    @RequestMapping("addMessage")
    public Result addReturn(MessageVo messageVo) {
        try {

            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param messageVo
     * @return
     */
    @RequestMapping("loadMessages")
    public DataGrid loadReturns(MessageVo messageVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Message> page=new Page<>(messageVo.getPage(), messageVo.getLimit());
        QueryWrapper<Message> qw=new QueryWrapper<>();
        qw.eq(messageVo.getMesType()!=null, "mes_type", messageVo.getMesType());
        qw.eq(messageVo.getMesStatus()!=null, "mes_status", messageVo.getMesStatus());
        qw.like(StringUtils.isNotBlank(messageVo.getMesContent()), "mes_content", messageVo.getMesContent());
        //来自于系统 目前只提供系统讯息 默认为1
        qw.like(StringUtils.isNotBlank(messageVo.getMesFrom()), "mes_from", messageVo.getMesFrom());
        //判断用户类别 管理员1查询所有讯息 普通用户查询自身讯息
        if(user!=null&&user.getType()==2){
            messageVo.setMesTo(user.getNickName());
            qw.like(StringUtils.isNotBlank(messageVo.getMesTo()), "mes_to", messageVo.getMesTo());
        }
        qw.orderByDesc("create_time");
        this.messageService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }


    /**
     * 修改讯息
     * @param messageVo
     * @return
     */
    @RequestMapping("updateMessage")
    public Result updateReturn(MessageVo messageVo) {
        try {

            return Result.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }


    /**
     * 删除讯息信息
     * @param id
     * @return
     */
    @RequestMapping("delMessage")
    public Result delReturn(String id) {
        try {
            this.messageService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除讯息信息
     * @param messageVo
     * @return
     */
    @RequestMapping("delBatchMessage")
    public Result delBatchReturn(MessageVo messageVo) {
        try {
            String[] ids = messageVo.getIds();
            this.messageService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}

