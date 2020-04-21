package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Type;
import ww.rent005.rent.vo.TypeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface TypeService extends IService<Type> {

    Type findByContent(String typeContent);

    Integer findByTypeAndContent(String typeType,String typeContent);

    List<String> getAllTypeTypes();

    List<String> getAllTypeContents(TypeVo typeVo);
}
