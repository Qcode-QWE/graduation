package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;
import cn.hwsoft.wisdom.core.query.LawQuery;

import java.util.List;

public interface LawHelpVideoService {
    boolean createLawHelpLog(Law_help_video_log lawHelpVideoLog);

    boolean updateLawHelpLog(Law_help_video_log lawHelpVideoLog);

    Law_help_video_log getRoomByUserId(Integer userId);

    List<Law_help_video_log> list(LawQuery query);
}
