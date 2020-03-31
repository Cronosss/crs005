package ww.rent005.rent.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TreeNodesBuilder
 * @Author: cronos
 * @Date: 2020/01/30 14:32
 * @Version: 1.0
 **/
public class TreeNodesBuilder {

    public static List<TreeNode> builderTree(List<TreeNode> nodes){
        //根节点
        TreeNode tn = new TreeNode();
        //创建一个空的集合整理数据
        List<TreeNode> TreeNodes = new ArrayList<>();
        //使用map集合提升效率
        Map<Integer,TreeNode> map = new HashMap<>();
        //遍历将id设为key，值为对象
        for(TreeNode menu1:nodes){
            map.put(menu1.getId(),menu1);
        }
        for(TreeNode child:nodes){
            //父节点pID为0
            if(child.getPid()==0){
                tn=child;
            }else {
                //通过child.getPid为key去获取父节点
                TreeNode parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        //由于layui树目前支持2级，故现在得将根节点去掉
        for(TreeNode chids:tn.getChildren()){
            TreeNodes.add(chids);
        }
        return TreeNodes;
    }
}
