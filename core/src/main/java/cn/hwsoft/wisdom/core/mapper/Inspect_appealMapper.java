package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.domain.Inspect_appealExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Inspect_appealMapper {
    long countByExample(Inspect_appealExample example);

    int deleteByExample(Inspect_appealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inspect_appeal record);

    int insertSelective(Inspect_appeal record);

    List<Inspect_appeal> selectByExample(Inspect_appealExample example);

    List<Inspect_appeal> selectAllInspect(Inspect_appealExample example);

    Inspect_appeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inspect_appeal record, @Param("example") Inspect_appealExample example);

    int updateByExample(@Param("record") Inspect_appeal record, @Param("example") Inspect_appealExample example);

    int updateByPrimaryKeySelective(Inspect_appeal record);

    int updateByPrimaryKey(Inspect_appeal record);
}