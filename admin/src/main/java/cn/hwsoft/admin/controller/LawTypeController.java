package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.service.LawTypeService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 法务类型管理
 * Created by Lenovo on 2019/7/28.
 */
@Controller
@RequestMapping("/law/type")
public class LawTypeController {
    @Autowired
    LawTypeService service;

    @AuthorityCheck("law_type")
    @PostMapping("/update") //更改标题
    @ResponseBody
    public JSONResult update(Integer id,String title){
        boolean tag=service.update(id,title);
        JSONResult jsonResult=new JSONResult();
        if(!tag){
            jsonResult.mark("修改失败");
        }
        return jsonResult;
    }

    @GetMapping("/{id}") //根据id查找法务类型
    @ResponseBody
    public JSONResult getLawTypeById(@PathVariable("id") Integer id){
        Law_type law_type=service.searchById(id);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(law_type);
        return jsonResult;
    }

    @PostMapping("/chageStatus") //更改状态
    @AuthorityCheck("law_type")
    @ResponseBody
    public String chageStatus(Integer id){
        boolean tag=service.chageStatus(id);
        if(tag){
            return "1";
        }
        return "0";
    }

    @AuthorityCheck("law_type")
    @PostMapping("/add")  //添加法务类型
    @ResponseBody
    public String add(String title){
        Law_type type=new Law_type();
        type.setTitle(title);
        type.setStatus((byte)1);
        Date date=new Date();
        String stamp = TimeUtils.DateToTimeStamp(date);
        type.setCreate_time(Integer.valueOf(stamp));
        boolean tag=service.add(type);
        if(tag){
            return "1";
        }
        return "0";
    }

    @AuthorityCheck("law_type")
    @PostMapping("/delete") //删除法务类型
    @ResponseBody
    public String delete(Integer id){
        boolean tag=service.delete(id);
        if(tag){
            return "1";  //1 表示成功  0 表示删除失败
        }else{
            return "0";
        }
    }

    //@AuthorityCheck("law_type")
    @PostMapping("/list")  //显示status=1的数据
    @ResponseBody
    public List<Law_type> list(Byte status){
        List<Law_type> list=service.search(status);
        return list;
    }


    //@AuthorityCheck("law_type")
    @PostMapping("/listAll")
    @ResponseBody
    public LawJsonResult listByPage(){
        List<Law_type> list=service.searchAll();
        LawJsonResult result=new LawJsonResult();
        result.setCount(list.size());
        result.setObj(list);
        return result;
    }


}
