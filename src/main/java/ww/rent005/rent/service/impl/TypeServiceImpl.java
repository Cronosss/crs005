package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.Type;
import ww.rent005.rent.mapper.TypeMapper;
import ww.rent005.rent.service.TypeService;
import ww.rent005.rent.vo.TypeVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Override
    public Type findByContent(String typeContent) {
        QueryWrapper<Type> qw=new QueryWrapper<>();
        qw.eq(typeContent!=null, "type_content", typeContent);
        return this.baseMapper.selectOne(qw);
    }

    @Override
    public Integer findByTypeAndContent(String typeType, String typeContent) {
        QueryWrapper<Type> qw=new QueryWrapper<>();
        qw.eq(typeContent!=null, "type_content", typeContent);
        qw.eq(typeType!=null, "type_type", typeType);
        return this.baseMapper.selectCount(qw);
    }

    /**
     * 获取所有类型类别
     * @return
     */
    @Override
    public List<String> getAllTypeTypes() {
        return this.baseMapper.getAllTypeTypes();
    }

    /**
     * 获取所有类型名称
     * @return
     */
    @Override
    public List<String> getAllTypeContents(TypeVo typeVo) {
        return this.baseMapper.getAllTypeContents(typeVo.getTypeType());
    }
}
