package cn.hwsoft.app.controller;


import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.InspectInformService;
import cn.hwsoft.wisdom.core.service.InspectTypeService;
import cn.hwsoft.wisdom.core.utils.BaseUtils;
import cn.hwsoft.wisdom.core.utils.IpUtils;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 军检举报Controller
 * Created by Boom on 2019/7/9.
 */
@Controller
@RequestMapping("/inspect/inform")
public class InspectInformController {

    @Autowired
    private InspectInformService informService;
    @Autowired
    private InspectTypeService typeService;


    /**
     * 保存军检举报
     * @param inspectInform
     * @param request
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JSONResult saveInspectInform(Inspect_inform inspectInform, HttpServletRequest request){
        //获取用户操作时Ip
        String ip= IpUtils.getIp(request);
        inspectInform.setIp(ip);
        String date = TimeUtils.DateToTimeStamp(new Date());
        //创建时间
        inspectInform.setCreate_time(Integer.valueOf(date));
        //生成回执单号，唯一
        Integer receipt = BaseUtils.createUUID();
        inspectInform.setReceipt(receipt);
        //修改图片的存储地址
        List<String> paths = UploadUtil.changeFile(inspectInform.getProof(),1,receipt);
        inspectInform.setSmall_proof(paths.get(0));
        inspectInform.setProof(paths.get(1));
        //添加军检举报记录
        informService.saveInspectInform(inspectInform);
        //封装返回类型
        JSONResult result = new JSONResult();
        result.setData(receipt);
        return result;
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult selectById(@PathVariable("id") int id){
        JSONResult result = new JSONResult();
        Inspect_inform inform = informService.selectById(id);
        Inspect_type type = null;
        if(inform!=null){
            type = typeService.selectById(inform.getType_id());
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("inform",inform);
        map.put("type",type);
        result.setData(map);
        return result;
    }
    /**
     * 获取举报类型
     * @return
     */
    @GetMapping("/type")
    @ResponseBody
    public JSONResult selectType(){
        List<Inspect_type> list = typeService.typeList(1);
        JSONResult result = new JSONResult();
        result.setData(list);
        return result;
    }


}
