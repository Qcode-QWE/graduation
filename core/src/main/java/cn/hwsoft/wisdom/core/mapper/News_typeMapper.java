package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.domain.News_typeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface News_typeMapper {
    long countByExample(News_typeExample example);

    int deleteByExample(News_typeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(News_type record);

    int insertSelective(News_type record);

    List<News_type> selectByExample(News_typeExample example);

    News_type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News_type record, @Param("example") News_typeExample example);

    int updateByExample(@Param("record") News_type record, @Param("example") News_typeExample example);

    int updateByPrimaryKeySelective(News_type record);

    int updateByPrimaryKey(News_type record);
}