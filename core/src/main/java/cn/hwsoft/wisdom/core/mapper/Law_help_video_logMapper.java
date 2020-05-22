package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;
import cn.hwsoft.wisdom.core.query.LawQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Law_help_video_logMapper {

    Integer create(Law_help_video_log lawHelpVideoLog);

    Integer delete(Integer id);

    Integer update(Law_help_video_log lawHelpVideoLog);

    Law_help_video_log findById(Integer id);

    List<Law_help_video_log> findAll();

    Law_help_video_log getRoomByUserId(Integer userId);

    int selectCount(byte reply_mark, Byte tag, String keyword);

    List<Law_help_video_log> list(@Param("query") LawQuery query);

}