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
import ww.rent005.rent.entity.User;
import ww.rent005.rent.service.CarService;
import ww.rent005.rent.service.UserService;
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

    @Autowired
    UserService userService;
    
    /**
     * 添加车辆
     */
    @RequestMapping("addCar")
    public Result addCar(CarVo carVo) {
        try {
            Integer flag = this.carService.findCarByCarNum(carVo.getCarNum());
            //添加时判断条件为大于0
            if(flag>0){
                return new Result(-1,"该车辆已存在,请检查后重新输入!","");
            }else {
                //判断是否有图片
                //无图片：用户管理的更新操作/密码修改操作
                //有图片：资料更新：默认不操作/自定义需要更新路径
                if(carVo.getCarImg()!=null&&!carVo.getCarImg().equals("default-car.png")){
                    //更新图片后缀路径
                    String filePath = UploadController.updateFileName(carVo.getCarImg(),"car","-temp");
                    //保存路径
                    carVo.setCarImg(filePath);
                }
                //注：车辆管理功能属于管理员功能 此处添加车辆为手动添加
                //用户自身拥有的发布车辆功能 此处为登记人信息为自身 此功能单独写
                if(carVo.getRentUserId()!=null&&carVo.getRentUserId()!=""){
                    //此处只是用rentUserId存储租用主人信息
                    User user = this.userService.findUserIdByNickName(carVo.getRentUserId());
                    if(user == null){
                        return new Result(-1,"该用户不存在,请重新输入!","");
                    }else {
                        carVo.setRentUserId(user.getUserId());
                    }
                }
                //设置id
                carVo.setCarId(RandomUtils.getRandomCarId());
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
     * 查询当前用户租用过的
     * @param carVo
     * @return
     */
    @RequestMapping("loadCarsByUser")
    public DataGrid loadCarsByUser(CarVo carVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        carVo.setUser(user);
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
            //修改时判断条件为大于1
            if(flag>1){
                return new Result(-1,"该车牌已存在,请检查后重新输入!","");
            }else {
                //判断是否有图片
                //无图片：用户管理的更新操作/密码修改操作
                //有图片：资料更新：默认不操作/自定义需要更新路径
                if(carVo.getCarImg()!=null&&!carVo.getCarImg().equals("default-car.png")){
                    //更新图片后缀路径
                    String filePath = UploadController.updateFileName(carVo.getCarImg(),"car","-temp");
                    //保存路径
                    carVo.setCarImg(filePath);
                    //删除之前图片
                    Car carTemp = this.carService.findCarById(carVo.getCarId());
                    UploadController.deleteFileByPath(carTemp.getCarImg(),"car");
                }
                //注：车辆管理功能属于管理员功能 此处添加车辆为手动添加
                //用户自身拥有的发布车辆功能 此处为登记人信息为自身 此功能单独写
                if(carVo.getRentUserId()!=null&&carVo.getRentUserId()!=""){
                    //此处只是用rentUserId存储租用主人信息
                    User user = this.userService.findUserIdByNickName(carVo.getRentUserId());
                    if(user == null){
                        return new Result(-1,"该用户不存在,请重新输入!","");
                    }else {
                        carVo.setRentUserId(user.getUserId());
                    }
                }
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
            //删除图片
            Car carTemp = this.carService.findCarById(id);
            UploadController.deleteFileByPath(carTemp.getCarImg(),"car");
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
            String[] ids = carVo.getIds();
            for(String id:ids){
                //删除图片
                Car carTemp = this.carService.findCarById(id);
                UploadController.deleteFileByPath(carTemp.getCarImg(),"car");
            }
            this.carService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

}

