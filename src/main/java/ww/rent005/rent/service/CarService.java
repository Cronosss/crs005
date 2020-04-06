package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.vo.CarVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-03-30
 */
public interface CarService extends IService<Car> {
    //根据车牌号查询
    Integer findCarByCarNum(String carNum);
    //查询所有车辆信息
    List<Car> findAllCar(CarVo carVo);
    //根据id查询车辆信息
    Car findCarById(String carId);
    //根据车牌号查询
    Car findCarInfoByCarNum(String carNum);
}
