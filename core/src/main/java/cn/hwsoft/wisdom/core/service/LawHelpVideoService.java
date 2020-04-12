package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;

public interface LawHelpVideoService {
    boolean createLawHelpLog(Law_help_video_log lawHelpVideoLog);

    boolean updateLawHelpLog(Law_help_video_log lawHelpVideoLog);
}
