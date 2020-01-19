package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Law_library;
import cn.hwsoft.wisdom.core.domain.Law_libraryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Law_libraryMapper {
    long countByExample(Law_libraryExample example);

    int deleteByExample(Law_libraryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Law_library record);

    int insertSelective(Law_library record);

    List<Law_library> selectByExample(Law_libraryExample example);

    Law_library selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Law_library record, @Param("example") Law_libraryExample example);

    int updateByExample(@Param("record") Law_library record, @Param("example") Law_libraryExample example);

    int updateByPrimaryKeySelective(Law_library record);

    int updateByPrimaryKey(Law_library record);

    int updateReadByPrimaryKey(Integer id);
}