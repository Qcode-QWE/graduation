package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Admin_authoritys;
import cn.hwsoft.wisdom.core.domain.Admin_authoritysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Admin_authoritysMapper {
    long countByExample(Admin_authoritysExample example);

    int deleteByExample(Admin_authoritysExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin_authoritys record);

    int insertSelective(Admin_authoritys record);

    List<Admin_authoritys> selectByExample(Admin_authoritysExample example);

    Admin_authoritys selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin_authoritys record, @Param("example") Admin_authoritysExample example);

    int updateByExample(@Param("record") Admin_authoritys record, @Param("example") Admin_authoritysExample example);

    int updateByPrimaryKeySelective(Admin_authoritys record);

    int updateByPrimaryKey(Admin_authoritys record);
}