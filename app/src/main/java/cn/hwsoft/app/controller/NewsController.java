package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.News;
import cn.hwsoft.wisdom.core.domain.News_detail;
import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.NewsService;
import cn.hwsoft.wisdom.core.service.NewsTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description:  新闻的controller
 * @author: QEcode
 * @create: 2019-07-11 16:29
 **/
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsTypeService newsTypeService;

    /**
     * 新闻页面首页的新闻列表
     * 获取最新的{nums}条新闻
     * @return
     */
    /*@GetMapping("/{nums}")
    @ResponseBody
    public JSONResult list(@PathVariable("nums")int nums){
        List list = newsService.getList(nums);
        JSONResult result = new JSONResult();
        result.setData(list);
        return result;
    }*/

    /**
     * 分页查询正常的新闻(带条件)
     * @param qo
     * @return
     */
    @PostMapping("/Page")
    @ResponseBody
    public JSONResult selectNewsPage(/*@ModelAttribute("qo") */QueryObject qo){
        //分页查询新闻信息
        PageInfo<News> info = newsService.getNewsPage(qo);
        //获取新闻信息列表
        List<News> list = info.getList();
        Map<Integer, News_type> newsTypeMap=new HashMap<>();
        //遍历list
        for (News news : list) {
            //根据id获取新闻类型信息
            News_type newsType = newsTypeService.getNewsTypeById(news.getNews_type_id());
            //将记录存进HashMap
            newsTypeMap.put(news.getNews_type_id(),newsType);
        }
        //封装数据
        Map<String,Object> resultMap = new HashMap<>();
        //将新闻信息存进结果集
        resultMap.put("news",info);
        //将新闻类型信息存进结果集
        resultMap.put("news_type",newsTypeMap);
        JSONResult result = new JSONResult();
        result.setData(resultMap);
        return result;
    }

    /**
     * 查询新闻对应的详细内容并增加阅读人数
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult getNewsById(@PathVariable("id") Integer id){
        //得到新闻信息
        News news=newsService.getNewsById(id);
        //得到新闻详细信息
        News_detail news_detail=newsService.getNews_detailByNews_id(id);
        //当新闻详细信息存在时增加阅读人数
        if(null != news && null != news_detail){
            newsService.updateRead(id);
        }
        //封装数据
        JSONResult jsonResult=new JSONResult();
        Map<String,Object> data=new HashMap<>();
        data.put("news",news);
        data.put("news_detail",news_detail);
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 阅读人数增加
     * @param id 新闻id
     * @return
     */
    /*@PostMapping("/read")
    @ResponseBody
    public JSONResult read(int id){
      JSONResult result = new JSONResult();
        newsService.updateRead(id);
        return result;
    }*/

    /**
     * 点赞人数增加
     * @param id 新闻id
     * @return
     */
    @PostMapping("/like")
    @ResponseBody
    public JSONResult like(int id){
        JSONResult result = new JSONResult();
        newsService.updateLike(id);
        return result;
    }
}
