package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.Law_library;
import cn.hwsoft.wisdom.core.domain.Law_libraryExample;
import cn.hwsoft.wisdom.core.mapper.Law_libraryMapper;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.LawLibraryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: court
 * @description:    法务学习--
 * @author:
 * @create: 2019-07-13 10:14
 **/
@Service
public class LawLibraryServiceImpl implements LawLibraryService{
    @Autowired
    private Law_libraryMapper lawLibraryMapper;

    /**
     * 查询最新的nums条法务
     * @param nums
     * @return
     */
    @Override
    public List<Law_library> selectList(int nums) {
        PageHelper.startPage(1, nums);
        Law_libraryExample example = new Law_libraryExample();
        Law_libraryExample.Criteria criteria = example.createCriteria();
        //最新的,状态正常的nums条
        criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause("create_time desc");
        List<Law_library> list = lawLibraryMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询type类型的法务
     * @param qo
     * @param typeId
     * @return
     */
    @Override
    public PageInfo<Law_library> selectPageByType(QueryObject qo, Integer typeId) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        Law_libraryExample example = new Law_libraryExample();
        Law_libraryExample.Criteria criteria = example.createCriteria();
        //类型为type,如果typeId==0,则查询所有类型
        if(typeId != 0){
            criteria.andLaw_type_idEqualTo(typeId);
        }
        //如果关键字不为空,则根据关键字查询
        if(!StringUtils.isEmpty(qo.getKeyword())){
            criteria.andTitleLike("%"+qo.getKeyword()+"%");
        }
        //状态正常
        criteria.andStatusEqualTo((byte)1);
        //最新的
        example.setOrderByClause("create_time desc");
        List<Law_library> list = lawLibraryMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 根据id查询法务内容
     * @param id
     * @return
     */
    @Override
    public Law_library selectById(Integer id) {
        Law_library lawLibrary = lawLibraryMapper.selectByPrimaryKey(id);
        return lawLibrary;
    }


    /**
     * 法务内容阅读人数增加
     * @param id
     */
    @Override
    public void read(Integer id) {
        lawLibraryMapper.updateReadByPrimaryKey(id);
    }


    /*--------------------------------管理界面------------------------------*/


    @Override   //分页显示法务学习
    public LawJsonResult listByPage(LawQuery query) {
        Law_libraryExample example = new Law_libraryExample();
        LawJsonResult<Law_library> result=new LawJsonResult<>();
        //设置查询分页条件
        int start = (query.getPage() - 1) * query.getLimit();
        example.setStartRow(start);
        example.setPageSize(query.getLimit());
        example.setOrderByClause("create_time desc"); //设置倒
        List<Law_library> list = lawLibraryMapper.selectByExample(example);
        System.out.println(list);
        result.setData((ArrayList<Law_library>) list);
        long count = lawLibraryMapper.countByExample(example);
        result.setCount((int) count);
        return result;
    }

    @Override  //添加法务
    public boolean add(Law_library lawLibrary) {
        int i = lawLibraryMapper.insert(lawLibrary);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override //删除法务
    public boolean delete(Integer id) {
        int key = lawLibraryMapper.deleteByPrimaryKey(id);
        if(key>0){
            return true;
        }
        return false;
    }

    @Override  //条件查询法务
    public LawJsonResult search(LawQuery query) {
        Law_libraryExample example = new Law_libraryExample();
        LawJsonResult<Law_library> result=new LawJsonResult<>();
        //设置查询分页条件
        int start = (query.getPage() - 1) * query.getLimit();
        example.setStartRow(start);
        example.setPageSize(query.getLimit());

        Law_libraryExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+query.getKeyword()+"%");
        List<Law_library> list = lawLibraryMapper.selectByExample(example);
        result.setData((ArrayList<Law_library>) list);
        long count = lawLibraryMapper.countByExample(example);
        result.setCount((int) count);
        return result;
    }

    @Override
    public Law_library searchById(Integer id) {
        return lawLibraryMapper.selectByPrimaryKey(id);
    }

    @Override  //更新法务
    public boolean update(Law_library lawLibrary) {
        int i = lawLibraryMapper.updateByPrimaryKeySelective(lawLibrary);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override  //修改法务的状态
    public boolean changeStatus(Law_library lawLibrary) {
        Byte status = lawLibrary.getStatus();
        if(status==(byte)1){
            lawLibrary.setStatus((byte)2);
        }else{
            lawLibrary.setStatus((byte)1);
        }
        int i = lawLibraryMapper.updateByPrimaryKeySelective(lawLibrary);
        if(i>0){
            return true;
        }
        return false;
    }

}
