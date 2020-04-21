package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.mapper.CarMapper;
import ww.rent005.rent.service.CarService;
import ww.rent005.rent.vo.CarVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-03-30
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    //根据车牌号查询
    @Override
    public Integer findCarByCarNum(String carNum) {
        return this.baseMapper.findCarByCarNum(carNum);
    }

    @Override
    public List<Car> findAllCar(CarVo carVo) {
        return this.baseMapper.findAllCar(carVo);
    }

    @Override
    public Car findCarById(String carId) {
        return this.baseMapper.findCarById(carId);
    }

    @Override
    public Car findCarInfoByCarNum(String carNum) {
        return this.baseMapper.findCarInfoByCarNum(carNum);
    }

    @Override
    public List<Car> findAllCarByIds(Set<String> carIds) {
        return this.baseMapper.findAllCarByIds(carIds);
    }

    @Override
    public Car findCarByIdReturnMap(String carId) {
        return this.baseMapper.findCarByIdReturnMap(carId);
    }

}
