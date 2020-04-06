package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.entity.Order;
import ww.rent005.rent.mapper.OrderMapper;
import ww.rent005.rent.service.OrderService;
import ww.rent005.rent.vo.OrderVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> findAllOrders(OrderVo orderVo) {
        return this.baseMapper.findAllOrders(orderVo);
    }
}