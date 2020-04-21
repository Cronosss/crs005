package ww.rent005.rent.controller;


import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.entity.Log;
import ww.rent005.rent.service.LogService;
import ww.rent005.rent.vo.LogVo;

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
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    /**
     * 添加日志
     */
    @RequestMapping("addLog")
    public Result addReturn(LogVo logVo) {
        try {
            this.logService.save(logVo);
            return Result.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * 使用了时间复杂查找 使用pageHelper分页查询
     * @param logVo
     * @return
     */
    @RequestMapping("loadLogs")
    public DataGrid loadLogs(LogVo logVo) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(logVo.getPage(), logVo.getLimit());
        List<Log> logList = this.logService.findAllLogs(logVo);
        return new DataGrid(page.getTotal(), logList);
    }

    //初始化订单卡数据
    @RequestMapping("initLogInfo")
    public Result initRentInfo(LogVo logVo){
        //1为登录IP日志 2为删除日志
        if ("登录".equals(logVo.getLogType())){
            logVo.setLogId(RandomUtils.getRandomLogId("IP"));
        }else if("删除".equals(logVo.getLogType())){
            logVo.setLogId(RandomUtils.getRandomLogId("DEL"));
        }else {
            logVo.setLogId(RandomUtils.getRandomLogId("UNDEF"));
        }
        logVo.setCreateTime(new Date());
        return new Result(200,"初始化成功",logVo);
    }

    /**
     * 修改
     * @param logVo
     * @return
     */
    @RequestMapping("updateLog")
    public Result updateReturn(LogVo logVo) {
        try {
            Log log = this.logService.getById(logVo);
            if(log.getLogType().equals(logVo.getLogType())&&log.getLogContent().equals(logVo.getLogContent())){
                return new Result(-1,"修改失败,无修改操作",null);
            } else{
                this.logService.updateById(logVo);
                return Result.UPDATE_SUCCESS;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }


    /**
     * 删除讯息
     * @param id
     * @return
     */
    @RequestMapping("delLog")
    public Result delReturn(String id) {
        try {
            this.logService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param logVo
     * @return
     */
    @RequestMapping("delBatchLog")
    public Result delBatchReturn(LogVo logVo) {
        try {
            String[] ids = logVo.getIds();
            this.logService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}
