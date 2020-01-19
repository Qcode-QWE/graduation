package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.News_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 新闻类型操作Controller
 * @author: Boom
 * @create: 2019-08-21 13:00
 **/
@Controller
@RequestMapping("/news/type")
public class NewsTypeController {
    @Autowired
    private NewsTypeService newsTypeService;

    /**
     * 查询状态正常的新闻类型列表
     * @return JSONResult
     */
    @GetMapping("/list")
    @ResponseBody
    public JSONResult getNewsTypeList(){
        //根据状态得到新闻类型列表
        List<News_type> news_types = newsTypeService.getNewsTypeListByStatus((byte) 1);
        JSONResult jsonResult=new JSONResult();
        Map<String,Object> list=new HashMap<>();
        list.put("list",news_types);
        jsonResult.setData(list);
        return jsonResult;
    }
}
