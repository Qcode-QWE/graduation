package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;

import java.util.List;

public interface Law_help_video_logMapper {

    Integer create(Law_help_video_log lawHelpVideoLog);

    Integer delete(Integer id);

    Integer update(Law_help_video_log lawHelpVideoLog);

    Law_help_video_log findById(Integer id);

    List<Law_help_video_log> findAll();
}