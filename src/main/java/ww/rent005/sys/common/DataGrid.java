package ww.rent005.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DataGrid
 * @Author: cronos
 * @Date: 2020/1/30 14:25
 * @Version: 1.0
 * 满足DTree数据格式 只能多不能少
 * 封装layui数据表格对象
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGrid {
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;
    public DataGrid(Long count,Object data){
        this.count = count;
        this.data = data;
    }
    public DataGrid(Object data){
        this.data = data;
    }
}
