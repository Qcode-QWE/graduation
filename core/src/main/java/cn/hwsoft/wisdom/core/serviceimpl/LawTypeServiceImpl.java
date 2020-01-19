package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.domain.Law_typeExample;
import cn.hwsoft.wisdom.core.mapper.Law_typeMapper;
import cn.hwsoft.wisdom.core.service.LawTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lenovo on 2019/7/28.
 */
@Service
public class LawTypeServiceImpl implements LawTypeService {
    @Autowired
    Law_typeMapper mapper;

    @Override
    public Law_type searchById(Integer law_type_id) {
        Law_type type = mapper.selectByPrimaryKey(law_type_id);
        return type;
    }

    @Override  //查询所有的法务类型
    public List<Law_type> searchAll() {
        Law_typeExample example=new Law_typeExample();
        Law_typeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Law_type> list = mapper.selectByExample(example);
        return list;
    }

    @Override //删除法务类型
    public boolean delete(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override  //添加法务类型
    public boolean add(Law_type type) {
        int i = mapper.insert(type);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override  //修改法务状态
    public boolean chageStatus(Integer id) {
        Law_type type = mapper.selectByPrimaryKey(id);
        if(type.getStatus()==(byte)1){
            type.setStatus((byte)2);
        }else if(type.getStatus()==(byte)2){
            type.setStatus((byte)1);
        }
        int i = mapper.updateByPrimaryKey(type);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override  //查询status=1的数据
    public List<Law_type> search(Byte status) {
        Law_typeExample example=new Law_typeExample();
        Law_typeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        List<Law_type> list = mapper.selectByExample(example);
        return list;
    }


    @Override  //更新法务类型
    public boolean update(Integer id, String title) {
        Law_type type=new Law_type();
        type.setTitle(title);
        Law_typeExample example=new Law_typeExample();
        Law_typeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i = mapper.updateByExampleSelective(type, example);
        if(i>0){
            return true;
        }
        return false;
    }
}
