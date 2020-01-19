package cn.hwsoft.wisdom.core.mapper;

import cn.hwsoft.wisdom.core.domain.Admin_authority;
import cn.hwsoft.wisdom.core.domain.Admin_authorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Admin_authorityMapper {
    long countByExample(Admin_authorityExample example);

    int deleteByExample(Admin_authorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin_authority record);

    int insertSelective(Admin_authority record);

    List<Admin_authority> selectByExample(Admin_authorityExample example);

    Admin_authority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin_authority record, @Param("example") Admin_authorityExample example);

    int updateByExample(@Param("record") Admin_authority record, @Param("example") Admin_authorityExample example);

    int updateByPrimaryKeySelective(Admin_authority record);

    int updateByPrimaryKey(Admin_authority record);
}