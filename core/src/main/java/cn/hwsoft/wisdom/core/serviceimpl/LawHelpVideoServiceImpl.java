package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;
import cn.hwsoft.wisdom.core.mapper.Law_help_video_logMapper;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.service.LawHelpVideoService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class LawHelpVideoServiceImpl implements LawHelpVideoService {
    @Autowired
    private Law_help_video_logMapper lawHelpVideoLogMapper;

    @Override
    public boolean createLawHelpLog(Law_help_video_log lawHelpVideoLog) {
        String s = TimeUtils.DateToTimeStamp(new Date());
        lawHelpVideoLog.setRoom(s);
        lawHelpVideoLog.setCreateTime(Long.valueOf(s));
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

    @Override
    public Law_help_video_log getRoomByUserId(Integer userId) {
        return lawHelpVideoLogMapper.getRoomByUserId(userId);
    }

    @Override
    public List<Law_help_video_log> list(LawQuery query) {
        List<Law_help_video_log> result = lawHelpVideoLogMapper.list(query);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.forEach(law->{
            String s = TimeUtils.timeStampToDate(String.valueOf(law.getCreateTime()));
            law.setCreateAt(s);
        });
        return result;
    }


}
