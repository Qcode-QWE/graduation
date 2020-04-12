package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.domain.Inspect_typeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Inspect_typeMapper {
    long countByExample(Inspect_typeExample example);

    int deleteByExample(Inspect_typeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inspect_type record);

    int insertSelective(Inspect_type record);

    List<Inspect_type> selectByExample(Inspect_typeExample example);

    Inspect_type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inspect_type record, @Param("example") Inspect_typeExample example);

    int updateByExample(@Param("record") Inspect_type record, @Param("example") Inspect_typeExample example);

    int updateByPrimaryKeySelective(Inspect_type record);

    int updateByPrimaryKey(Inspect_type record);
}