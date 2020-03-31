package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.vo.CarVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-03-30
 */
public interface CarMapper extends BaseMapper<Car> {

    //根据车牌号查询
    Integer findCarByCarNum(String carNum);
    //查询所有车辆信息
    List<Car> findAllCar(CarVo carVo);
}
