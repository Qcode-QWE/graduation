package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.News_detail;
import cn.hwsoft.wisdom.core.domain.News_detailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface News_detailMapper {
    long countByExample(News_detailExample example);

    int deleteByExample(News_detailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(News_detail record);

    int insertSelective(News_detail record);

    List<News_detail> selectByExampleWithBLOBs(News_detailExample example);

    List<News_detail> selectByExample(News_detailExample example);

    News_detail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News_detail record, @Param("example") News_detailExample example);

    int updateByExampleWithBLOBs(@Param("record") News_detail record, @Param("example") News_detailExample example);

    int updateByExample(@Param("record") News_detail record, @Param("example") News_detailExample example);

    int updateByPrimaryKeySelective(News_detail record);

    int updateByPrimaryKeyWithBLOBs(News_detail record);

    int updateByPrimaryKey(News_detail record);
}