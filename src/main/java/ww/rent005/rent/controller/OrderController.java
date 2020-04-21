package ww.rent005.rent.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.RandomUtils;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.common.WebUtils;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.entity.Order;
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.CarService;
import ww.rent005.rent.service.OrderService;
import ww.rent005.rent.service.UserService;
import ww.rent005.rent.vo.OrderVo;

import java.util.Arrays;
import java.util.Date;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    /**
     * 添加订单
     */
    @RequestMapping("addOrder")
    public Result addCar(OrderVo orderVo) {
        try {
            if(orderVo.getBeginTime().compareTo(orderVo.getEndTime())<0){
                Car car = this.carService.findCarById(orderVo.getCarId());
                //设置为已租
                car.setRentStatus(1);
                car.setUpdateTime(new Date());
                this.carService.updateById(car);
                this.orderService.save(orderVo);
                return new Result(200,"租用成功",null);
            }else {
                return new Result(-1,"时间选择有误,请重新选择",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param orderVo
     * @return
     */
    @RequestMapping("loadOrders")
    public DataGrid loadCars(OrderVo orderVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        //判断用户类型
        //管理员可以管理所有订单 用户只能管理自身订单
        if(user!=null&&user.getType()!=1){
            //为普通用户
            orderVo.setUserId(user.getUserId());
        }
        //此处使用PageHelper分页处理 因为自动QueryWrapper不方便使用复杂SQL
        Page<Object> page = PageHelper.startPage(orderVo.getPage(), orderVo.getLimit());
        List<Order> orders = this.orderService.findAllOrders(orderVo);
        return new DataGrid(page.getTotal(), orders);
    }

    //初始化订单卡数据
    @RequestMapping("initRentInfo")
    public Result initRentInfo(OrderVo orderVo){
        User user = (User) WebUtils.getSession().getAttribute("user");
        //设置编号
        orderVo.setOrderId(RandomUtils.getRandomOrderId());
        //设置创建时间
        orderVo.setCreateTime(new Date());
        orderVo.setUserId(user.getUserId());
        orderVo.setNickName(user.getNickName());
        return new Result(200,"初始化成功",orderVo);
    }

    /**
     * 修改订单信息
     * @param orderVo
     * @return
     */
    @RequestMapping("updateOrder")
    public Result updateCar(OrderVo orderVo) {
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
    @RequestMapping("delOrder")
    public Result deleteOrder(String id) {
        try {
            this.orderService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除订单信息
     * @param orderVo
     * @return
     */
    @RequestMapping("delBatchOrder")
    public Result delBatchOrder(OrderVo orderVo) {
        try {
            String[] ids = orderVo.getIds();
            this.orderService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}

