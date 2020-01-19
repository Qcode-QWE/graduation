package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.Inspect_informExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Inspect_informMapper {
    long countByExample(Inspect_informExample example);

    int deleteByExample(Inspect_informExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inspect_inform record);

    int insertSelective(Inspect_inform record);

    List<Inspect_inform> selectByExample(Inspect_informExample example);

    Inspect_inform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inspect_inform record, @Param("example") Inspect_informExample example);

    int updateByExample(@Param("record") Inspect_inform record, @Param("example") Inspect_informExample example);

    int updateByPrimaryKeySelective(Inspect_inform record);

    int updateByPrimaryKey(Inspect_inform record);
}