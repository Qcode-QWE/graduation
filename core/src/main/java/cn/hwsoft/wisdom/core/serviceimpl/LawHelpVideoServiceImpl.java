package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;
import cn.hwsoft.wisdom.core.mapper.Law_help_video_logMapper;
import cn.hwsoft.wisdom.core.service.LawHelpVideoService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class LawHelpVideoServiceImpl implements LawHelpVideoService {
    @Autowired
    private Law_help_video_logMapper lawHelpVideoLogMapper;

    @Override
    public boolean createLawHelpLog(Law_help_video_log lawHelpVideoLog) {
        String s = TimeUtils.DateToTimeStamp(new Date());
        lawHelpVideoLog.setRoom(s);
        lawHelpVideoLog.setCreateTime(Integer.valueOf(s));
        Integer lawHelpLog = lawHelpVideoLogMapper.create(lawHelpVideoLog);
        if (lawHelpLog > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLawHelpLog(Law_help_video_log lawHelpVideoLog) {
        return false;

    }
}
