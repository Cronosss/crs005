package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.vo.CarVo;

import java.util.List;
import java.util.Set;

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

    //根据车牌号查询
    Car findCarInfoByCarNum(String carNum);
    //查询所有车辆信息
    List<Car> findAllCar(CarVo carVo);
    //根据id查询车辆信息
    Car findCarById(String carId);
    //根据id查询车辆信息
    Car findCarByIdReturnMap(String carId);
    //根据ids查询
    List<Car> findAllCarByIds(@Param("carIds") Set<String> carIds);
    //查询最新的车辆信息
    List<String> getNewCar();

    List<Car> getRankingListCar(@Param("carIds") List<String> carIds);
}
