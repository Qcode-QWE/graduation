package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.News;
import cn.hwsoft.wisdom.core.domain.News_detail;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @program: court
 * @description:新闻的业务层接口--
 * @author:
 * @create: 2019-07-11 16:30
 **/
public interface NewsService {
    /*--------------用户界面-----------------------*/
//    public List<News> getList(int nums);
    public PageInfo<News> getNewsPage(QueryObject qo);
    public void updateRead(int id);
    public void updateLike(int id);

    /*--------------管理界面-----------------------*/

    PageInfo<News> getNewsList(QueryObject qo);
    boolean addNews(News news, News_detail news_detail);
    boolean deleteNews(Integer[] ids);
    void editNews(News news, String content);
    void changeStatus(News news);
    boolean publishNews(Integer[] ids);

    /*--------------用户与管理界面共用-----------------------*/
    News_detail getNews_detailByNews_id(Integer id);
    News getNewsById(Integer id);
}
