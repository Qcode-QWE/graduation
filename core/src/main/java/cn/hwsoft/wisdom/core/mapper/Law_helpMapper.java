package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Law_help;
import cn.hwsoft.wisdom.core.domain.Law_helpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Law_helpMapper {
    long countByExample(Law_helpExample example);

    int deleteByExample(Law_helpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Law_help record);

    int insertSelective(Law_help record);

    List<Law_help> selectByExample(Law_helpExample example);

    Law_help selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Law_help record, @Param("example") Law_helpExample example);

    int updateByExample(@Param("record") Law_help record, @Param("example") Law_helpExample example);

    int updateByPrimaryKeySelective(Law_help record);

    int updateByPrimaryKey(Law_help record);

    int selectCount(@Param("reply_mark") byte reply_mark,@Param("tag")byte tag);


    List<Integer> selectUids(Law_helpExample example);
}