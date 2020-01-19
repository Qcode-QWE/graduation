package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.News;
import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.domain.News_typeExample;
import cn.hwsoft.wisdom.core.mapper.News_typeMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.NewsTypeService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: court
 * @description: 新闻类型管理Service
 * @author: Boom
 * @create: 2019-08-20 16:35
 **/
@Service
public class NewsTypeServiceImpl implements NewsTypeService {
    @Autowired
    private News_typeMapper news_typeMapper;

    @Override
    public News_type getNewsTypeById(Integer id) {
        return news_typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<News_type> getNewsTypeListByStatus(Byte status) {
        //创建查询条件
        News_typeExample example=new News_typeExample();
        News_typeExample.Criteria criteria = example.createCriteria();
        //根据状态查询新闻类型列表
        criteria.andStatusEqualTo(status);
        List<News_type> news_types = news_typeMapper.selectByExample(example);
        return news_types;
    }

    @Override
    public PageInfo<News_type> getNewsTypeList(QueryObject qo) {
        //设置分页信息
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        News_typeExample example=new News_typeExample();
        List<News_type> news_types = news_typeMapper.selectByExample(example);
        if(null != news_types && news_types.size()>0){
            PageInfo<News_type> pageInfo=new PageInfo<>(news_types);
            return pageInfo;
        }
        return null;
    }

    @Override
    public void changeStatus(News_type news_type) {
        news_typeMapper.updateByPrimaryKeySelective(news_type);
    }

    @Override
    public void addNews_type(News_type news_type) {
        //封装news_type
        //初始化新闻类型状态：1:数据正常，2：数据异常
        news_type.setStatus((byte) 1);
        //初始化创建时间
        news_type.setCreate_time(Integer.parseInt(TimeUtils.DateToTimeStamp(new Date())));
        news_typeMapper.insert(news_type);
    }

    @Override
    public void updateNewsType(News_type news_type) {
        news_typeMapper.updateByPrimaryKeySelective(news_type);
    }
}
