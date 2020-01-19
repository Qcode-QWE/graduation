package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: court
 * @description: 军检类型
 * @author: QEcode
 * @create: 2019-07-29 15:47
 **/
@Controller
@RequestMapping("/inspect/type")
public class InspectTypeController {
    @Autowired
    private InspectTypeService typeService;

    /**
     * 查询军检类型
     * @param qo
     * @param tag
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo,int tag){
        PageInfo<Inspect_type> list = typeService.list(qo,tag);
        JSONResult result = new JSONResult();
        result.setData(list);
        return result;
    }

    /**
     * 保存军检类型
     * @param type
     * @return
     */
    @AuthorityCheck("appeal_type")
    @PostMapping("/add")
    @ResponseBody
    public JSONResult add(Inspect_type type){
        typeService.save(type);
        return  new JSONResult();
    }

    @AuthorityCheck("appeal_type")
    @PostMapping("/edit")
    @ResponseBody
    public JSONResult edit(Inspect_type type){
        typeService.edit(type);
        return new JSONResult();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult getById(@PathVariable("id") int id){
        Inspect_type type = typeService.selectById(id);
        JSONResult result = new JSONResult();
        result.setData(type);
        return result;
    }


}
