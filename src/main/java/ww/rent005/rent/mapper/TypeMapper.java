package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ww.rent005.rent.entity.Type;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface TypeMapper extends BaseMapper<Type> {

    List<String> getAllTypeTypes();

    List<String> getAllTypeContents(String typeType);

}
