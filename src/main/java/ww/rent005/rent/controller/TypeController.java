package ww.rent005.rent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ww.rent005.rent.common.DataGrid;
import ww.rent005.rent.common.Result;
import ww.rent005.rent.entity.Type;
import ww.rent005.rent.service.TypeService;
import ww.rent005.rent.vo.TypeVo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    /**
     * 添加
     */
    @RequestMapping("addType")
    public Result addReturn(TypeVo typeVo) {
        try {
            if(typeVo.getOtherType()!=null&&typeVo.getOtherType()!=""){
                typeVo.setTypeType(typeVo.getOtherType());
            }
            //先判断类型相同 名称相同的
            //再判断类型不同的情况
            if (this.typeService.findByTypeAndContent(typeVo.getTypeType(),typeVo.getTypeContent())>0){
                return new Result(-1,"该条信息已存在,请重新添加",null);
            }else {
                typeVo.setCreateTime(new Date());
                this.typeService.save(typeVo);
                return Result.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }

    /**
     * 查询所有
     * @param typeVo
     * @return
     */
    @RequestMapping("loadTypes")
    public DataGrid loadReturns(TypeVo typeVo) {
        IPage<Type> page=new Page<>(typeVo.getPage(), typeVo.getLimit());
        QueryWrapper<Type> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(typeVo.getTypeType()), "type_type", typeVo.getTypeType());
        qw.like(StringUtils.isNotBlank(typeVo.getTypeContent()), "type_content", typeVo.getTypeContent());
        qw.orderByDesc("create_time");
        this.typeService.page(page, qw);
        return new DataGrid(page.getTotal(), page.getRecords());
    }

    /**
     * 查询所有类型类别
     * @param
     * @return
     */
    @RequestMapping("getAllTypeTypes")
    public DataGrid getAllTypeTypes() {
        List<String> allTypes = this.typeService.getAllTypeTypes();
        return new DataGrid(allTypes);
    }

    /**
     * 查询所有类型名称
     * @param
     * @return
     */
    @RequestMapping("getAllTypeContents")
    public DataGrid getAllTypeContents(TypeVo typeVo) {
        List<String> allTypeContents = this.typeService.getAllTypeContents(typeVo);
        return new DataGrid(allTypeContents);
    }

    /**
     * 修改
     * @param typeVo
     * @return
     */
    @RequestMapping("updateType")
    public Result updateReturn(TypeVo typeVo) {
        try {
            //类型相同 名称不能相同
            //类型不同 名称可以不同
            Type type = this.typeService.getById(typeVo.getTypeId());
            //重写了equals和hashCode
            if((type!=null)&&(typeVo.getTypeType().equals(type.getTypeType()))&&(typeVo.getTypeContent().equals(type.getTypeContent()))){
                return new Result(-1,"修改失败,无修改操作",null);
            }else if (this.typeService.findByTypeAndContent(typeVo.getTypeType(),typeVo.getTypeContent())>0){
                return new Result(-1,"该条信息已存在,请重试",null);
            }
            this.typeService.updateById(typeVo);
            return Result.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delType")
    public Result delReturn(String id) {
        try {
            this.typeService.removeById(id);
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除信息
     * @param typeVo
     * @return
     */
    @RequestMapping("delBatchType")
    public Result delBatchReturn(TypeVo typeVo) {
        try {
            String[] ids = typeVo.getIds();
            this.typeService.removeByIds(Arrays.asList(ids));
            return Result.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }
}
