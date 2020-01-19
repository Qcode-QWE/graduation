package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.LawTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 法务类型管理
 * Created by Lenovo on 2019/8/9.
 */
@Controller
@RequestMapping("/law/type")
public class LawTypeController {
    @Autowired
    LawTypeService service;

    /**
     * 查询所有法务类型
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public JSONResult list(){
        JSONResult result=new JSONResult();
        byte status=1;
        List<Law_type> list = service.search(status);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        result.setData(map);
        return result;
    }
}
