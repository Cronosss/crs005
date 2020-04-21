package ww.rent005.rent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ww.rent005.rent.entity.Log;
import ww.rent005.rent.vo.LogVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface LogMapper extends BaseMapper<Log> {

    List<Log> findAllLogs(LogVo logVo);

}
