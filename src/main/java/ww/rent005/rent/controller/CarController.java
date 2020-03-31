package ww.rent005.rent.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.entity.Car;
import ww.rent005.rent.service.CarService;
import ww.rent005.rent.vo.CarVo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-01-29
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;
    
    /**
     * 添加车辆
     */
    @RequestMapping("addCar")
    public Result addCar(CarVo carVo) {
        try {
            Integer flag = this.carService.findCarByCarNum(carVo.getCarNum());
            if(flag>0){
                return new Result(-1,"该车辆已存在,请检查后重新输入!","");
            }else {
                carVo.setUpdateTime(new Date());
                carVo.setCreateTime(new Date());
                this.carService.save(carVo);
                return Result.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param carVo
     * @return
     */
    @RequestMapping("loadCars")
    public DataGrid loadCars(CarVo carVo) {
        //此处使用PageHelper分页处理 因为自动QueryWrapper不方便使用复杂SQL
        Page<Object> page = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        List<Car> carList = this.carService.findAllCar(carVo);
        return new DataGrid(page.getTotal(), carList);
    }

    /**
     * 修改汽车信息
     * @param carVo
     * @return
     */
    @RequestMapping("updateCar")
    public Result updateCar(CarVo carVo){
        try {
            Integer flag = this.carService.findCarByCarNum(carVo.getCarNum());
            if(flag>0){
                return new Result(-1,"该车牌已存在,请检查后重新输入!","");
            }else {
                carVo.setUpdateTime(new Date());
                this.carService.updateById(carVo);
                return Result.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }

    /**
     * 删除汽车
     * @param id
     * @return
     */
    @RequestMapping("delCar")
    public Result deleteCar(String id) {
        try {
            this.carService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }


    /**
     * 批量删除汽车信息
     * @param carVo
     * @return
     */
    @RequestMapping("delBatchCar")
    public Result delBatchCar(CarVo carVo){
        try {
            this.carService.removeByIds(Arrays.asList(carVo.getIds()));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

}

