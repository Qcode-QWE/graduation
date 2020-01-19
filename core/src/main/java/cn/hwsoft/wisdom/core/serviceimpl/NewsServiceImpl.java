package cn.hwsoft.wisdom.core.serviceimpl;

import cn.hwsoft.wisdom.core.domain.*;
import cn.hwsoft.wisdom.core.mapper.NewsMapper;
import cn.hwsoft.wisdom.core.mapper.News_detailMapper;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.NewsService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @program: court
 * @description:
 * @author:
 * @create: 2019-07-11 16:34
 **/
@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private News_detailMapper newsDetailMapper;
    /**
     * 查询最新的nums条新闻
     * @param nums
     * @return
     */
    /*@Override
    public List<News> getList(int nums) {
        PageHelper.startPage(1, nums);
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        //最新的,状态正常的nums条
        criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause("create_time desc");
        List<News> list = newsMapper.selectByExample(example);
        return list;
    }*/

    /**
     * 分页查询
     * @param qo
     * @return
     */
    @Override
    public PageInfo<News> getNewsPage(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria  = example.createCriteria();
        //根据keyword条件,查询状态为1的新闻
        criteria.andStatusEqualTo((byte)1);
        if(!StringUtils.isEmpty(qo.getKeyword())){
            criteria.andTitleLike("%"+qo.getKeyword()+"%");
        }
        //根据类型查询，如果类型值为0，则查询全部，否则查询具体
        if(qo.getType()!=0){
            criteria.andNews_type_idEqualTo(qo.getType());
        }
        example.setOrderByClause("create_time desc");
        List<News> list = newsMapper.selectByExample(example);
        return new PageInfo<>(list);
    }



    /**
     * 阅读人数增加
     */
    @Override
    public void updateRead(int id) {
        newsMapper.updateReadByPrimaryKey(id);
    }

    /**
     * 点赞人数增加
     * @param id
     */
    @Override
    public void updateLike(int id) {
        newsMapper.updateLikeByPrimaryKey(id);
    }


    @Override
    public PageInfo<News> getNewsList(QueryObject qo) {
        //设置分页信息
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        //创建条件查询
        NewsExample example=new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        //如果搜索关键字不等于空，带关键字查询
        if(!StringUtils.isEmpty(qo.getKeyword()))
        {
            criteria.andTitleLike("%"+qo.getKeyword()+"%");
        }
        //根据创建时间降序排序
        example.setOrderByClause("create_time desc");
        //条件查询新闻信息
        List<News> news = newsMapper.selectByExample(example);
        //如果有新闻信息
        if(null !=news && news.size()>0){
            //条件查询信息数量
            long count = newsMapper.countByExample(example);
            //封装数据到PageInfo
            PageInfo<News> pageInfo=new PageInfo<>(news);
            pageInfo.setTotal(count);
            //返回封装数据
            return pageInfo;
        }
        return null;
    }

    @Override
    public boolean addNews(News news, News_detail news_detail) {
        //封装news
        news.setLike(0);
        news.setRead(0);
        news.setStatus((byte) 1);
        news.setCreate_time(Integer.valueOf(TimeUtils.DateToTimeStamp(new Date())));
        //插入新闻信息
        Integer i=newsMapper.insert(news);
        //封装news_detail
        news_detail.setNews_id(news.getId());
        //查询新闻详细信息
        Integer j=newsDetailMapper.insert(news_detail);
        return (i!=0 && j!=0);
    }


    /**
     * 批量下架
     * @param ids
     */
    @Override
    public boolean deleteNews(Integer[] ids) {
        int i=0;
        //循环遍历
        for (Integer id : ids) {
//            System.out.println(id);
            //封装news
            News news=new News();
            news.setId(id);
            //设置状态为异常，软删除
            news.setStatus((byte) 2);
            //部分更新
            i = newsMapper.updateByPrimaryKeySelective(news);
        }
        return (i!=0);
    }

    @Override
    public News getNewsById(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        return news;
    }

    @Override
    public News_detail getNews_detailByNews_id(Integer id) {
        //创建查询条件
        News_detailExample example=new News_detailExample();
        News_detailExample.Criteria criteria = example.createCriteria();
        //根据新闻id查询新闻详细信息
        criteria.andNews_idEqualTo(id);
        List<News_detail> news_details = newsDetailMapper.selectByExampleWithBLOBs(example);
        //如果有详细信息，返回，否则返回null
        if(news_details!=null && news_details.size()>0){
            System.out.println(news_details.get(0));
            return  news_details.get(0);
        }
        return null;
    }

    @Override
    public void editNews(News news, String content) {
        //根据id部分更新新闻信息
        newsMapper.updateByPrimaryKeySelective(news);
        //封装news_detail
        News_detail news_detail=new News_detail();
        news_detail.setContent(content);
        //创建条件
        News_detailExample example=new News_detailExample();
        News_detailExample.Criteria criteria = example.createCriteria();
        //根据news_id部分更新新闻详细信息
        criteria.andNews_idEqualTo(news.getId());
        newsDetailMapper.updateByExampleSelective(news_detail,example);
    }

    @Override
    public void changeStatus(News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }

    /**
     * 批量发布
     * @param ids
     */
    @Override
    public boolean publishNews(Integer[] ids) {
        int i=0;
        //循环遍历
        for (Integer id : ids) {
            //封装news
            News news=new News();
            news.setId(id);
            //设置状态为正常,发布
            news.setStatus((byte) 1);
            //部分更新
            i = newsMapper.updateByPrimaryKeySelective(news);
        }
        return (i!=0);
    }
}
