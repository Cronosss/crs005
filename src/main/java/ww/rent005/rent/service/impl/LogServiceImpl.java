package ww.rent005.rent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ww.rent005.rent.entity.Log;
import ww.rent005.rent.mapper.LogMapper;
import ww.rent005.rent.service.LogService;
import ww.rent005.rent.vo.LogVo;

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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public List<Log> findAllLogs(LogVo logVo) {
        return this.baseMapper.findAllLogs(logVo);
    }
}
