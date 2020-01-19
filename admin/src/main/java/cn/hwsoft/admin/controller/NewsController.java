package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.admin.util.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Admin;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 新闻管理Controller
 * @author: Boom
 * @create: 2019-07-20 17:23
 **/
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsTypeService newsTypeService;


    /**
     * 查询新闻列表
     * @param qo
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @PostMapping("/list")
    @ResponseBody
    public JSONResult getNewsList(QueryObject qo){
        //分页查询新闻信息
        PageInfo<News> pageInfo = newsService.getNewsList(qo);
        //获取新闻信息列表
        List<News> list = pageInfo.getList();
        Map<Integer, News_type> newsTypeMap=new HashMap<>();
        //遍历list
        for (News news : list) {
            //根据id获取新闻类型信息
            News_type newsType = newsTypeService.getNewsTypeById(news.getNews_type_id());
            //将记录存进HashMap
            newsTypeMap.put(news.getNews_type_id(),newsType);
        }
        //封装数据
        JSONResult jsonResult=new JSONResult();
        Map<String,Object> resultMap = new HashMap<>();
        //将新闻信息存进结果集
        resultMap.put("news",pageInfo);
        //将新闻类型信息存进结果集
        resultMap.put("news_type",newsTypeMap);
        //设置结果集
        jsonResult.setData(resultMap);
        return jsonResult;
    }



    /**
     * 添加新闻
     * @param news，news_detail
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @PostMapping("addNews")
    @ResponseBody
    public JSONResult addNews(News news, News_detail news_detail){
        boolean i = newsService.addNews(news, news_detail);
        JSONResult jsonResult=new JSONResult();
        //如果添加失败
        if(!i)
            jsonResult.mark("添加新闻失败");
         return  jsonResult;
    }

    //富文本编辑器图片上传接口
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String,Object> uploadImage(@RequestParam("file")MultipartFile file) {
        UploadUtil.setUploadPATH("/news");
        String url= UploadUtil.upload(file);
        System.out.println(url);
        Map<String,Object> result=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        data.put("src",url);
        result.put("code",0);
        result.put("msg","");
        result.put("data",data);
        return result;
    }

    /**
     * 批量下架
     * @param ids
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @PostMapping("/deleteNews")
    @ResponseBody
    public JSONResult deleteNews(Integer[] ids){
        boolean b = newsService.deleteNews(ids);
        JSONResult jsonResult=new JSONResult();
        if(!b)
            jsonResult.mark("下架失败");
        return jsonResult;
    }

    /**
     * 批量发布
     * @param ids
     * @return JSONResult
     */
    @AuthorityCheck("news")
    @PostMapping("/publishNews")
    @ResponseBody
    public JSONResult publishNews(Integer[] ids){
        boolean b = newsService.publishNews(ids);
        JSONResult jsonResult=new JSONResult();
        if(!b)
            jsonResult.mark("发布失败");
        return jsonResult;
    }

    @AuthorityCheck("news")
    @GetMapping("/{id}")
    @ResponseBody
    public Map<String,Object> getNewsById(@PathVariable("id") Integer id){
        //得到新闻信息
        News news=newsService.getNewsById(id);
        //得到新闻详细信息
        News_detail news_detail=newsService.getNews_detailByNews_id(id);
        //封装数据
        Map<String,Object> result=new HashMap<>();
        result.put("news",news);
        result.put("news_detail",news_detail);
        result.put("success",true);
        return result;
    }

    @AuthorityCheck("news")
    @PostMapping("editNews")
    @ResponseBody
    public JSONResult editNews(News news, String content){
        newsService.editNews(news, content);
        JSONResult jsonResult=new JSONResult();
        return  jsonResult;
    }

    @AuthorityCheck("news")
    @PostMapping("changeStatus")
    @ResponseBody
    public JSONResult changeStatus(News news){
        newsService.changeStatus(news);
        JSONResult jsonResult=new JSONResult();
        return  jsonResult;
    }

}
