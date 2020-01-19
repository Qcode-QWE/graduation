package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.domain.Law_typeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Law_typeMapper {
    long countByExample(Law_typeExample example);

    int deleteByExample(Law_typeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Law_type record);

    int insertSelective(Law_type record);

    List<Law_type> selectByExample(Law_typeExample example);

    Law_type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Law_type record, @Param("example") Law_typeExample example);

    int updateByExample(@Param("record") Law_type record, @Param("example") Law_typeExample example);

    int updateByPrimaryKeySelective(Law_type record);

    int updateByPrimaryKey(Law_type record);
}