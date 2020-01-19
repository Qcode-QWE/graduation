package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.BaseUtils;
import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.InspectAppealService;
import cn.hwsoft.wisdom.core.service.InspectInformService;
import cn.hwsoft.wisdom.core.service.InspectSueService;
import cn.hwsoft.wisdom.core.service.InspectTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 军检的controller
 * @author: QEcode
 * @create: 2019-08-08 09:04
 **/
@RequestMapping(value = "/inspect")
@Controller
public class InspectController {
    @Autowired
    private InspectAppealService appealService;
    @Autowired
    private InspectInformService informService;
    @Autowired
    private InspectSueService sueService;
    @Autowired
    private InspectTypeService typeService;




    /**
     * 查询军检列表 0:查询所有 ,1:举报,2:控告,3:申述
     * @param qo
     * @param type
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo, int type,int userId){
        JSONResult result = new JSONResult();
        PageInfo pageInfo = null;
        Map<Integer,Inspect_type> types = new HashMap<>();
        Class clazz = null;
        if(type==0){
            pageInfo = appealService.allList(qo,userId);
            List<Inspect_appeal> appeals = pageInfo.getList();
            types = getTypes(appeals,Inspect_appeal.class);
            clazz = Inspect_appeal.class;
        }else if(type==1){
            pageInfo = informService.listByUserId(qo,userId);
            List<Inspect_inform> informs = pageInfo.getList();
            types = getTypes(informs,Inspect_inform.class);
            clazz = Inspect_inform.class;
        }else if(type==2){
            pageInfo = sueService.listByUserId(qo,userId);
            List<Inspect_sue> sues = pageInfo.getList();
            types = getTypes(sues,Inspect_sue.class);
            clazz = Inspect_sue.class;
        }else if(type==3){
            pageInfo = appealService.listByUserId(qo,userId);
            List<Inspect_appeal> appeals = pageInfo.getList();
            types = getTypes(appeals,Inspect_appeal.class);
            clazz = Inspect_appeal.class;
        }
        List list = pageInfo.getList();
        pageInfo.setList(BaseUtils.InspectToJsonObject(list,types,clazz,BaseUtils.InspectFieldsName));
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo",pageInfo);
        result.setData(resultMap);
        return result;
    }

    private <T> Map<Integer,Inspect_type> getTypes(List<T> list,Class<T> clazz){
        Map<Integer,Inspect_type> map = new HashMap<>();
        for(T inspect:list){
            try {
                Method method = clazz.getMethod("getType_id");
                Integer typeId = (Integer) method.invoke(inspect);
                Method method1 = clazz.getMethod("getId");
                Integer id = (Integer) method1.invoke(inspect);
                if(typeId!=null){
                    Inspect_type type = typeService.selectById(typeId);
                    map.put(id,type);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  map;
    }

    //文件上传接口
    @PostMapping("/img/upload")
    @ResponseBody
    public JSONResult uploadImage(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        String url= UploadUtil.upload(file,UploadUtil.getInspectPath()+UploadUtil.getInspectTempPath());
        JSONResult result = new JSONResult();
        if(url!=null){
            result.setData(url);
        }else{
            result.mark("上传失败");
        }
        return  result;
    }

    @PostMapping("/img/delete")
    @ResponseBody
    public JSONResult deleteImage(String url){
        JSONResult result = new JSONResult();
        UploadUtil.deleteFile(url);
        return  result;
    }

}
