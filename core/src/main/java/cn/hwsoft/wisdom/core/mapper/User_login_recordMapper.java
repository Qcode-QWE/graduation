package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.User_login_record;
import cn.hwsoft.wisdom.core.domain.User_login_recordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User_login_recordMapper {
    long countByExample(User_login_recordExample example);

    int deleteByExample(User_login_recordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User_login_record record);

    int insertSelective(User_login_record record);

    List<User_login_record> selectByExample(User_login_recordExample example);

    User_login_record selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User_login_record record, @Param("example") User_login_recordExample example);

    int updateByExample(@Param("record") User_login_record record, @Param("example") User_login_recordExample example);

    int updateByPrimaryKeySelective(User_login_record record);

    int updateByPrimaryKey(User_login_record record);
}