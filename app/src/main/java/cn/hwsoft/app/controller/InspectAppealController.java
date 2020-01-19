package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.InspectAppealService;
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
 *
 */
@Controller
@RequestMapping("/inspect/appeal")
public class InspectAppealController {
    @Autowired
    private InspectAppealService appealService;

    @Autowired
    private InspectTypeService typeService;

    /**
     * 获取申述类型
     * @return
     */
    @GetMapping("/type")
    @ResponseBody
    public JSONResult selectType(){
        List<Inspect_type> list = typeService.typeList(3);
        JSONResult result = new JSONResult();
        result.setData(list);
        return result;
    }

    /**
     * 保存军检申述
     * @param appeal
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JSONResult save(Inspect_appeal appeal, HttpServletRequest request){
        String ip = IpUtils.getIp(request);
        //ip
        appeal.setIp(ip);
        String date = TimeUtils.DateToTimeStamp(new Date());
        //创建时间
        appeal.setCreate_time(Integer.valueOf(date));
        //生成回执单号，唯一
        Integer receipt = BaseUtils.createUUID();
        appeal.setReceipt(receipt);
        //修改图片的存储地址
        List<String> paths = UploadUtil.changeFile(appeal.getProof(),3,receipt);
        appeal.setSmall_proof(paths.get(0));
        appeal.setProof(paths.get(1));
        appealService.save(appeal);
        JSONResult result  = new JSONResult();
        result.setData(receipt);
        return result;
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public JSONResult selectById(@PathVariable("id") int id){
        JSONResult result = new JSONResult();
        Inspect_appeal appeal = appealService.selectById(id);
        Inspect_type type = null;
        if(appeal!=null){
            type = typeService.selectById(appeal.getType_id());
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("appeal",appeal);
        map.put("type",type);
        result.setData(map);
        //String string= JSON.toJSONString(result);
        return  result;
    }




}
