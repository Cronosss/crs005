package ww.rent005.sys.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeNode
 * @Author: cronos
 * @Date: 2020/01/30 14:28
 * @Version: 1.0
 * 对应layui的json数据格式
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private Integer id;
    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private String target;
    private List<TreeNode> children = new ArrayList<>();

    //增加一个checkArr属性 用于树的复选
    private String checkArr="0";//"0"表示不选中 "1"表示选中

    //该构造函数用于生成复选树JSON数据
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    //首页左侧导航树构造器
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.target = target;
    }
}
