package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Inspect_sue;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.InspectSueService;
import cn.hwsoft.wisdom.core.service.InspectTypeService;
import cn.hwsoft.wisdom.core.service.UserService;
import cn.hwsoft.wisdom.core.utils.BaseUtils;
import cn.hwsoft.wisdom.core.utils.IpUtils;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private InspectSueService sueService;
    @Autowired
    private InspectTypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 获取军检举报列表(分页,带条件)
     * @param qo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo){
        JSONResult result = new JSONResult();
        PageInfo<Inspect_sue> pageInfo = sueService.list(qo);
        List<Inspect_sue> list = pageInfo.getList();
        List<Object> viewList = new ArrayList<>();
        //查询控告对应的user和处理的admin
        for(Inspect_sue sue:list){
            Integer id = sue.getAdmin_id();
            Admin admin = null;
            if(id!=null && id!=0){
                admin = adminService.selectAdmin(id);
            }
            Integer typeID = sue.getType_id();
            Inspect_type type = null;
            if(typeID!=null && typeID!=0){
                type = typeService.selectById(typeID);
            }
            InspectView view = new InspectView(sue,type,admin,2);
            viewList.add(view);
        }
        result.setData(new PageView(viewList,pageInfo.getTotal()));
        return result;
    }

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
        sueService.save(inspectSue);
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
        Inspect_sue sue = sueService.selectById(id);
        Inspect_type type = null;
        Admin admin = null;
        if(sue !=null){
            type = typeService.selectById(sue.getType_id());
            admin = adminService.selectAdmin(sue.getAdmin_id());
        }
        result.setData(new InspectView(sue,type,admin,1));
        return result;
    }

}
