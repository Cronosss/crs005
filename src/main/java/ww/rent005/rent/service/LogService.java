package ww.rent005.rent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ww.rent005.rent.entity.Log;
import ww.rent005.rent.vo.LogVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cronos
 * @since 2020-04-19
 */
public interface LogService extends IService<Log> {

    List<Log> findAllLogs(LogVo logVo);

}
