package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Order;
import ww.rent005.rent.vo.OrderVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-02
 */
public interface OrderService extends IService<Order> {

    List<Order> findAllOrders(OrderVo orderVo);
}
