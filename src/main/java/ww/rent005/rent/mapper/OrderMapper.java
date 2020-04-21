package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ww.rent005.rent.entity.Order;
import ww.rent005.rent.vo.OrderVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-04-02
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> findAllOrders(OrderVo orderVo);

    List<Order> findOrdersByCarId(String carId);
}
