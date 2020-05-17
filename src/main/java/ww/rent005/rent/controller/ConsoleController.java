package ww.rent005.rent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.service.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;
    @Autowired
    ArticleService articleService;
    @Autowired
    OrderService orderService;

    /**
     * 初始化首页数据
     * @return
     */
    @RequestMapping("/initConInfo")
    public Result initConsoleInfo(){
        int num[] = new int[4];
        //站内车辆
        num[0] = this.carService.findCarCount();
        //在租车辆
        num[1] = this.carService.findCarCountInRent();
        //访问人数
        num[2] = this.historyService.getById(1).getVisitCount();
        //在线人数
        num[3] = this.userService.findOnLineCount();
        return new Result(200,"初始化成功",num);
    }

    /**
     * 轮播图
     * @return
     */
    @RequestMapping("/getNewCarInfo")
    public Result getNewCarInfo(){
        try {
            return new Result(200,"初始化成功",this.carService.getNewCar());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"初始化失败",null);
        }
    }

    /**
     * 最新公告
     * @return
     */
    @RequestMapping("/getNewArticle")
    public Result getNewArticle(){
        try {
            return new Result(200,"初始化成功",this.articleService.getNewArticle());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"初始化失败",null);
        }
    }

    /**
     * 查看公告详情
     */
    @RequestMapping("/getArticleById")
    public Result getArticleById(String articleId){
        try {
            return new Result(200,"获取成功",this.articleService.getById(articleId));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"获取失败",null);
        }
    }

    /**
     * 获取用户排行榜
     * @return
     */
    @RequestMapping("/getRankingListUser")
    public Result getRankingListUser(){
        try {
            List<String> temp = this.orderService.getRankingListUser();
            String[] names = new String[temp.size()];
            for(int i = 0; i<temp.size();i++){
                if(i==0){
                    names[1] = temp.get(0);
                }else if(i==1){
                    names[0] = temp.get(1);
                }else if(i==2){
                    names[2] = temp.get(2);
                }
            }
            return new Result(200,"获取成功",names);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"获取失败",null);
        }
    }

    /**
     * 获取车辆排行榜
     * @return
     */
    @RequestMapping("/getRankingListCar")
    public Result getRankingListCar(){
        try {
            List<String> carIds = this.orderService.getRankingListCarForCarIds();
            String[] names = new String[carIds.size()];
            for(int i = 0; i<carIds.size();i++){
                if(i==0){
                    names[1] = carIds.get(0);
                }else if(i==1){
                    names[0] = carIds.get(1);
                }else if(i==2){
                    names[2] = carIds.get(2);
                }
            }
            List<String> ids = Arrays.asList(names);
            return new Result(200,"获取成功",this.carService.getRankingListCar(ids));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"获取失败",null);
        }
    }

    /**
     * 获取车辆详情
     * @param id
     * @return
     */
    @RequestMapping("/getCarInfo")
    public Result getCarInfo(String id){
        try {
            return new Result(200,"获取成功",this.carService.findCarByIdReturnMap(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"获取失败",null);
        }
    }

}
