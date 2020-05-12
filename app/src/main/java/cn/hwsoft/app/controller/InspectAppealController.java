package cn.hwsoft.app.controller;

import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Inspect_appeal;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.InspectAppealService;
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
 *
 */
@Controller
@RequestMapping("/inspect/appeal")
public class InspectAppealController {
    @Autowired
    private InspectAppealService appealService;
    @Autowired
    private InspectTypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 获取军检控告列表(分页,带条件)
     * @param qo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo){
        JSONResult result = new JSONResult();
        PageInfo<Inspect_appeal> pageInfo = appealService.list(qo);
        List<Inspect_appeal> list = pageInfo.getList();
        List<Object> viewList = new ArrayList<>();
        //查询控告对应的user和处理的admin
        for(Inspect_appeal appeal:list){
            Integer id = appeal.getAdmin_id();
            Admin admin = null;
            if(id!=null && id!=0){
                admin = adminService.selectAdmin(id);
            }
            Integer typeID = appeal.getType_id();
            Inspect_type type = null;
            if(typeID!=null && typeID!=0){
                type = typeService.selectById(typeID);
            }
            AppealView view = new AppealView(appeal,type,admin,3);
            viewList.add(view);
        }
        result.setData(new PageView(viewList,pageInfo.getTotal()));
        return result;
    }

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
        Admin admin = null;
        if(appeal!=null){
            type = typeService.selectById(appeal.getType_id());
            admin = adminService.selectAdmin(appeal.getAdmin_id());
        }
        result.setData(new AppealView(appeal,type,admin,1));
        return  result;
    }

}


class AppealView extends InspectView{
    public Object target;

    public AppealView(Object data, Inspect_type type, Admin admin,int dataType) {
        super(data, type, admin,dataType);
    }

    public AppealView(Object data, Inspect_type type, Admin admin,int dataType, Object target) {
        super(data, type, admin,dataType);
        this.target = target;
    }
}