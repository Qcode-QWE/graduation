package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.Law_library;
import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.LawLibraryService;
import cn.hwsoft.wisdom.core.service.LawTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 法务天地的controllrt
 * @author: QEcode
 * @create: 2019-07-13 09:42
 **/
@RequestMapping("/law/library")
@Controller
public class LawLibraryController {
    @Autowired
    private LawLibraryService lawLibraryService;
    @Autowired
    private LawTypeService lawTypeService;

    /**
     * 法务学习首页法务列表
     * @param nums
     * @return
     */
    @GetMapping("/{nums}")
    @ResponseBody
    public JSONResult selectList(@PathVariable("nums")int nums){
        JSONResult result = new JSONResult();
        List<Law_library> list = lawLibraryService.selectList(nums);
        result.setData(list);
        return result;
    }


    /**
     * 查询type类型的法务,并分页(带条件)
     * @param qo
     * @param typeId
     * @return
     */
    @PostMapping("/Page")
    @ResponseBody
    public JSONResult selectByType(@ModelAttribute("qo") QueryObject qo, int typeId){
        //分页查询法务知识信息
        PageInfo<Law_library> pageInfo = lawLibraryService.selectPageByType(qo, typeId);
        //获取法务知识信息列表
        List<Law_library> list = pageInfo.getList();
        Map<Integer, Law_type> lawTypeMap=new HashMap<>();
        //遍历list
        for (Law_library library : list) {
            //根据id获取法务知识类型信息
            Law_type type= lawTypeService.searchById(library.getLaw_type_id());
            //将记录存进HashMap
            lawTypeMap.put(library.getLaw_type_id(),type);
        }
        //封装数据
        Map<String,Object> resultMap = new HashMap<>();
        //将法务知识信息存进结果集
        resultMap.put("law_library",pageInfo);
        //将法务知识类型信息存进结果集
        resultMap.put("law_type",lawTypeMap);
        JSONResult result = new JSONResult();
        result.setData(resultMap);
        return result;
    }


    /**
     * 根据id查询法务内容
     * @param id
     * @return
     */
    @PostMapping("/id")
    @ResponseBody
    public JSONResult selectById(int id){
        JSONResult result = new JSONResult();
        Law_library lawLibrary = lawLibraryService.selectById(id);
        //如果法务详情不为空，阅读人数加一
        if(null != lawLibrary){
            lawLibraryService.read(id);
        }
        result.setData(lawLibrary);
        return result;
    }

}
