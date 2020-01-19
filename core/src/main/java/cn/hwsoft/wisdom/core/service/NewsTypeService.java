package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NewsTypeService {
    News_type getNewsTypeById(Integer id);

    List<News_type> getNewsTypeListByStatus(Byte status);

    PageInfo<News_type> getNewsTypeList(QueryObject qo);

    void changeStatus(News_type news_type);

    void addNews_type(News_type news_type);

    void updateNewsType(News_type news_type);
}
