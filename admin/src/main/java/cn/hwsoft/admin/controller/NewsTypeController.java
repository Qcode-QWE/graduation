package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.NewsTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: court
 * @description: 新闻类型管理Controller
 * @author: Boom
 * @create: 2019-08-20 18:14
 **/
@Controller
@RequestMapping("/news/type")
public class NewsTypeController {
    @Autowired
    private NewsTypeService newsTypeService;

    /**
     * 查询状态正常的新闻类型列表
     * @param status
     * @return JSONResult
     */
    //@AuthorityCheck("news_type")
    @PostMapping("/list")
    @ResponseBody
    public JSONResult getNewsTypeListByStatus(Byte status){
        //根据状态得到新闻类型列表
        List<News_type> news_types = newsTypeService.getNewsTypeListByStatus(status);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(news_types);
        return jsonResult;
    }

    /**
     * 查询新闻类型列表
     * @param qo
     * @return JSONResult
     */
    //@AuthorityCheck("news_type")
    @PostMapping("/listAll")
    @ResponseBody
    public JSONResult getNewsTypeList(QueryObject qo){
        //分页查询新闻类型信息
        PageInfo<News_type> pageInfo = newsTypeService.getNewsTypeList(qo);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(pageInfo);
        return jsonResult;
    }


    /**
     * 软删除：更改新闻类型状态：是否可用
     * @param news_type
     * @return JSONResult
     */
    @AuthorityCheck("news_type")
    @PostMapping("changeStatus")
    @ResponseBody
    public JSONResult changeStatus(News_type news_type){
        newsTypeService.changeStatus(news_type);
        JSONResult jsonResult=new JSONResult();
        return  jsonResult;
    }

    /**
     * 添加新闻类型
     * @param news_type
     * @return JSONResult
     */
    @AuthorityCheck("news_type")
    @PostMapping("addNews_type")
    @ResponseBody
    public JSONResult addNews_type(News_type news_type){
        newsTypeService.addNews_type(news_type);
        JSONResult jsonResult=new JSONResult();
        return  jsonResult;
    }

    /**
     * 根据id查找新闻类型
     * @param id
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult getNewsTypeById(@PathVariable("id") Integer id){
        News_type news_type=newsTypeService.getNewsTypeById(id);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(news_type);
        return  jsonResult;
    }

    /**
     * 更新新闻类型
     * @param news_type
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @PostMapping("/editNewsType")
    @ResponseBody
    public JSONResult updateNewsType(News_type news_type){
        newsTypeService.updateNewsType(news_type);
        JSONResult jsonResult=new JSONResult();
        return  jsonResult;
    }
}
