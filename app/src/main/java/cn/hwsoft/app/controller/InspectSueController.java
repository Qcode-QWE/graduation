package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.service.InspectSueService;
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
 * @program: parent
 * @description:    军检控告的controller
 * @author: QEcode
 * @create: 2019-07-12 10:57
 **/
@RequestMapping("/inspect/sue")
@Controller
public class InspectSueController {
    @Autowired
    private InspectSueService inspectSueService;
    @Autowired
    private InspectTypeService typeService;
    /**
     *  保存举报信息
     * @param inspectSue
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JSONResult save(Inspect_sue inspectSue, HttpServletRequest request){
        //举报人ip
        String ip = IpUtils.getIp(request);
        inspectSue.setIp(ip);
        //举报时间
        int date = Integer.valueOf(TimeUtils.DateToTimeStamp(new Date()));
        inspectSue.setCreate_time(date);
        //生成回执单号，唯一
        Integer receipt = BaseUtils.createUUID();
        inspectSue.setReceipt(receipt);
        //修改图片的存储地址
        List<String> paths = UploadUtil.changeFile(inspectSue.getProof(),2,receipt);
        inspectSue.setSmall_proof(paths.get(0));
        inspectSue.setProof(paths.get(1));
        //保存
        inspectSueService.save(inspectSue);
        JSONResult result = new JSONResult();
        result.setData(receipt);
        return result;
    }

    /**
     * 获取军检控告类型
     * @return
     */
    @GetMapping("/type")
    @ResponseBody
    public JSONResult selectAllSueType(){
        JSONResult result=  new JSONResult();
        //获取所有的控告的类型
        List<Inspect_type> types = typeService.typeList(2);
        result.setData(types);
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
        Inspect_sue sue = inspectSueService.selectById(id);
        Inspect_type type = null;
        if(sue !=null){
            type = typeService.selectById(sue.getType_id());
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("sue",sue);
        map.put("type",type);
        result.setData(map);
        return result;
    }

}
