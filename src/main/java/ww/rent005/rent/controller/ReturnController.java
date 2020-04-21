package ww.rent005.rent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.*;
import ww.rent005.rent.service.*;
import ww.rent005.rent.vo.OrderVo;
import ww.rent005.rent.vo.ReturnVo;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    BackService backService;

    /**
     * 添加反单
     */
    @RequestMapping("addReturn")
    public Result addReturn(ReturnVo returnVo) {
        try {
            //此时说明存在反馈信息
            if(returnVo.getAdType()!=null&&(returnVo.getAdvice()!=null&&returnVo.getAdvice()!="")){
                Back back = new Back();
                //设置id
                back.setBackId(RandomUtils.getRandomBackId(returnVo.getAdType()));
                //设置返回单id
                back.setReturnId(returnVo.getReturnId());
                //反馈类别
                back.setAdType(returnVo.getAdType());
                //0是车辆故障 即车辆反馈
                if(returnVo.getAdType()==0){
                    //补充信息为车牌号
                    back.setMoreInfo(returnVo.getCarNum());
                }else {
                    back.setMoreInfo("用户反馈");
                }
                //反馈内容
                back.setAdvice(returnVo.getAdvice());
                //反馈人nickName
                back.setUserName(returnVo.getNickName());
                //是否解决 创建均为0 未解决
                back.setIsSolve(0);
                //设置创建时间
                back.setDateTime(new Date());
                //保存反馈信息
                this.backService.save(back);
            }
            //设置车辆状态
            Car car = this.carService.findCarInfoByCarNum(returnVo.getCarNum());
            //设置为未租用
            car.setRentStatus(0);
            this.carService.updateById(car);

            //设置订单状态
            Order order = this.orderService.getById(returnVo.getOrderId());
            order.setReturnId(returnVo.getReturnId());
            order.setOrderStatus(1);
            this.orderService.updateById(order);

            //保存返还单信息
            this.returnService.save(returnVo);
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
     * 查询当前用户租用过的
     * @param orderVo
     * @return
     */
    @RequestMapping("loadCarsRentInfoByUser")
    public DataGrid loadCarsByUser(OrderVo orderVo) {
        //此处使用PageHelper分页处理 因为自动QueryWrapper不方便使用复杂SQL
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(orderVo.getPage(), orderVo.getLimit());
        User user = (User) WebUtils.getSession().getAttribute("user");
        HashMap<String,Object> map = null;
        //根据id查询当前用户拥有的订单
        //查询未还车辆 （防止同一辆车不同状态时出现省略情况
        orderVo.setUserId(user.getUserId());
        //获得当前用户所有订单信息
        List<Order> orders = this.orderService.findAllOrders(orderVo);
        for(Order order: orders){
            map = new HashMap<>();
            if(order.getReturnId()!=null&&order.getReturnId()!=""){
                map.put("return",this.returnService.getById(order.getReturnId()));
            }
            map.put("rentUser",this.userService.getById(order.getUserId()));
            map.put("car",this.carService.findCarByIdReturnMap(order.getCarId()));
            order.setMap(map);
        }
        //注：还车处只会显示一辆车信息
        return new DataGrid(page.getTotal(), orders);
    }

    //初始化订单卡数据
    @RequestMapping("initReturnInfo")
    public Result initRentInfo(ReturnVo returnVo){
        User user = (User) WebUtils.getSession().getAttribute("user");
        //设置编号
        returnVo.setReturnId(RandomUtils.getRandomReturnId());
        //设置创建时间
        returnVo.setCreateTime(new Date());
        return new Result(200,"初始化成功",returnVo);
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

