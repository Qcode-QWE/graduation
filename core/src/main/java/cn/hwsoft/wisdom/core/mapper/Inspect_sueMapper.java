package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.domain.Inspect_sueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Inspect_sueMapper {
    long countByExample(Inspect_sueExample example);

    int deleteByExample(Inspect_sueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inspect_sue record);

    int insertSelective(Inspect_sue record);

    List<Inspect_sue> selectByExample(Inspect_sueExample example);

    Inspect_sue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inspect_sue record, @Param("example") Inspect_sueExample example);

    int updateByExample(@Param("record") Inspect_sue record, @Param("example") Inspect_sueExample example);

    int updateByPrimaryKeySelective(Inspect_sue record);

    int updateByPrimaryKey(Inspect_sue record);
}