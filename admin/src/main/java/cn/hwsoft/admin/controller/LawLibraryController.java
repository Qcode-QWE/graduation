package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.admin.util.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Law_library;
import cn.hwsoft.wisdom.core.domain.Law_type;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.service.LawLibraryService;
import cn.hwsoft.wisdom.core.service.LawTypeService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 法务天地
 * Created by Lenovo on 2019/7/19.
 */
@Controller
@RequestMapping("/law/library")
public class LawLibraryController {
    @Autowired
    private LawLibraryService service;
    @Autowired
    private LawTypeService typeservice;

    @AuthorityCheck("law_library")
    @PostMapping("/changeStatus")  //修改法务的数据状态
    @ResponseBody
    public String changeStatus(Law_library lawLibrary){
        boolean tag=service.changeStatus(lawLibrary);
        if(tag){
            return "1";
        }
        return "0";
    }

    @AuthorityCheck("law_library")
    @PostMapping("/update")  //更新法务
    @ResponseBody
    public String update(Law_library lawLibrary){
        System.out.println(lawLibrary);
        boolean tag=service.update(lawLibrary);
        if(tag){
            return "1";
        }
        return "0";
    }

    @AuthorityCheck("law_library")
    @GetMapping("/{id}")  //通过id查找法务
    @ResponseBody
    public Law_library searchById(@PathVariable("id") Integer id){
        Law_library lawLibrary=service.searchById(id);
        if(lawLibrary!=null){
            return lawLibrary;
        }
        return null;
    }

    //富文本编辑器图片上传接口
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String,Object> uploadImage(@RequestParam("file")MultipartFile file) {
        UploadUtil.setUploadPATH("/lawLibrary");
        String url= UploadUtil.upload(file);
        Map<String,Object> result=new HashMap<>();
        Map<String,Object> data;
        data = new HashMap<>();
        data.put("src",url);
        result.put("code",0);
        result.put("msg","");
        result.put("data",data);
        return result;
    }

    @AuthorityCheck("law_library")
    @PostMapping("/search")//条件查询法务
    @ResponseBody
    public LawJsonResult search(LawQuery query){
        LawJsonResult result= service.search(query);

        //根据law_type_id 查找法务类型
        Map<Integer,Law_type> typeMap=new HashMap<>();
        ArrayList<Law_library> list = result.getData();
        for (Law_library library : list) {
            Law_type type= typeservice.searchById(library.getLaw_type_id());
            typeMap.put(library.getLaw_type_id(),type);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("typeMap",typeMap);
        map.put("informs",result.getData());
        result.setObj(map);
        return result;
    }

    @AuthorityCheck("law_library")
    @PostMapping("/delete") //删除法务
    @ResponseBody
    public String delete(Integer id){
        boolean tag=service.delete(id);
        if(tag){
            return "1";  //1 表示成功  0 表示删除失败
        }else{
            return "0";
        }
    }

    @AuthorityCheck("law_library")
    @GetMapping("/list")  //分页显示法务学习
    @ResponseBody
    public LawJsonResult list(LawQuery query){
        LawJsonResult result=service.listByPage(query);

        //根据law_type_id 查找法务类型
        Map<Integer,Law_type> typeMap=new HashMap<>();
        ArrayList<Law_library> list = result.getData();
        for (Law_library library : list) {
            Law_type type= typeservice.searchById(library.getLaw_type_id());
            typeMap.put(library.getLaw_type_id(),type);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("typeMap",typeMap);
        map.put("informs",result.getData());
        result.setObj(map);
        return result;
    }

    @AuthorityCheck("law_library")
    @PostMapping("/add")  //添加法务
    @ResponseBody
    public String add(Law_library lawLibrary){
        lawLibrary.setRead(0);
        lawLibrary.setStatus((byte)1);
        Date date=new Date();
        String s = TimeUtils.DateToTimeStamp(date);
        lawLibrary.setCreate_time(Integer.valueOf(s));
        boolean tag=service.add(lawLibrary);
        if(tag){
            return "1";
        }
        return "0";
    }
}
