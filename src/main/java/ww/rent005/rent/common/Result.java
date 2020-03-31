package ww.rent005.rent.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Author: cronos
 * @Date: 2020/1/30 12:00
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    //Constast为常量接口
    /**
     * 登录
     */
    public static final Result  LOGIN_SUCCESS=new Result(Constast.OK, "登陆成功",null);
    public static final Result  LOGIN_ERROR_PASS=new Result(Constast.ERROR, "登陆失败,用户名或密码不正确",null);
    public static final Result  LOGIN_ERROR_CODE=new Result(Constast.ERROR, "登陆失败,验证码不正确",null);

    /**
     * 更新
     */
    public static final Result  UPDATE_SUCCESS=new Result(Constast.OK, "更新成功",null);
    public static final Result  UPDATE_ERROR=new Result(Constast.ERROR, "更新失败",null);

    /**
     * 添加
     */
    public static final Result  ADD_SUCCESS=new Result(Constast.OK, "添加成功",null);
    public static final Result  REG_SUCCESS=new Result(Constast.OK, "注册成功",null);
    public static final Result  ADD_ERROR=new Result(Constast.ERROR, "添加失败",null);
    public static final Result  REG_ERROR=new Result(Constast.ERROR, "注册失败",null);
    public static final Result  ADD_FALSE=new Result(Constast.ERROR, "用户名已存在",null);

    /**
     * 删除
     */
    public static final Result  DELETE_SUCCESS=new Result(Constast.OK, "删除成功",null);
    public static final Result  DELETE_ERROR=new Result(Constast.ERROR, "删除失败",null);

    /**
     * 重置
     */
    public static final Result  RESET_SUCCESS=new Result(Constast.OK, "重置成功",null);
    public static final Result  RESET_ERROR=new Result(Constast.ERROR, "重置失败",null);

    /**
     * 分配
     */
    public static final Result  DISPATCH_SUCCESS=new Result(Constast.OK, "分配成功",null);
    public static final Result  DISPATCH_ERROR=new Result(Constast.ERROR, "分配失败",null);

    /**
     * 操作
     */
    public static final Result  OPERATE_SUCCESS=new Result(Constast.OK, "操作成功",null);
    public static final Result  OPERATE_ERROR=new Result(Constast.ERROR, "操作失败",null);

    private Integer code;
    private String msg;
    private Object data;
}
