package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.domain.Inspect_typeExample;
import cn.hwsoft.wisdom.core.mapper.Inspect_typeMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: court
 * @description: 军检类型
 * @author: QEcode
 * @create: 2019-07-19 10:41
 **/
@Service
public class InspectTypeServiceImpl implements InspectTypeService {
    @Autowired
    private Inspect_typeMapper typeMapper;

    /**
     * 查询类型列表 : 1:举报，2：控告，3：申诉 ,0:所有类型
     * @param type
     * @return
     */
    @Override
    public List<Inspect_type> typeList(int type) {
        Inspect_typeExample example = new Inspect_typeExample();
        Inspect_typeExample.Criteria criteria = example.createCriteria();
        if(type != 0){
            criteria.andTagEqualTo((byte)type);
        }
        criteria.andStatusEqualTo((byte)1);
        List<Inspect_type> list = typeMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据id查询类型
     * @param id
     * @return
     */
    @Override
    public Inspect_type selectById(int id) {
        Inspect_type type = typeMapper.selectByPrimaryKey(id);
        return type;
    }

    /**
     * 查询军检类型
     * @param tag
     * @return
     */
    @Override
    public PageInfo<Inspect_type> list(QueryObject qo, int tag) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        Inspect_typeExample example = new Inspect_typeExample();
        Inspect_typeExample.Criteria criteria = example.createCriteria();
        if(tag != 0){
            criteria.andTagEqualTo((byte)tag);
        }
        List<Inspect_type> list = typeMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 保存军检类型
     * @param type
     */
    @Override
    public void save(Inspect_type type) {
        typeMapper.insertSelective(type);
    }

    /**
     * 修改军检类型
     * @param type
     */
    @Override
    public void edit(Inspect_type type) {
        typeMapper.updateByPrimaryKeySelective(type);
    }
}
