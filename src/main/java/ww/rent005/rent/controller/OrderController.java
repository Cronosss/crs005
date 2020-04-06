package ww.rent005.rent.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.entity.Order;
import ww.rent005.rent.service.CarService;
import ww.rent005.rent.service.OrderService;
import ww.rent005.rent.service.UserService;
import ww.rent005.rent.vo.OrderVo;

import java.util.Arrays;
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
            return Result.ADD_SUCCESS;
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
        //此处使用PageHelper分页处理 因为自动QueryWrapper不方便使用复杂SQL
        Page<Object> page = PageHelper.startPage(orderVo.getPage(), orderVo.getLimit());
        List<Order> orders = this.orderService.findAllOrders(orderVo);
        return new DataGrid(page.getTotal(), orders);
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

