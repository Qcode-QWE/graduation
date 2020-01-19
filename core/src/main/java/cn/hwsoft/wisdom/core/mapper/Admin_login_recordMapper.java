package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Admin_login_record;
import cn.hwsoft.wisdom.core.domain.Admin_login_recordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Admin_login_recordMapper {
    long countByExample(Admin_login_recordExample example);

    int deleteByExample(Admin_login_recordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin_login_record record);

    int insertSelective(Admin_login_record record);

    List<Admin_login_record> selectByExample(Admin_login_recordExample example);

    Admin_login_record selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin_login_record record, @Param("example") Admin_login_recordExample example);

    int updateByExample(@Param("record") Admin_login_record record, @Param("example") Admin_login_recordExample example);

    int updateByPrimaryKeySelective(Admin_login_record record);

    int updateByPrimaryKey(Admin_login_record record);
}