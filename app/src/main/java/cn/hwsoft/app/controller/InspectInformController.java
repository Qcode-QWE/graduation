package cn.hwsoft.app.controller;


import cn.hwsoft.app.utils.UploadUtil;
import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Inspect_inform;
import cn.hwsoft.wisdom.core.domain.Inspect_type;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.InspectInformService;
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
        PageInfo<Inspect_inform> pageInfo = informService.list(qo);
        List<Inspect_inform> list = pageInfo.getList();
        List<Object> viewList = new ArrayList<>();
        //查询控告对应的user,type和处理的admin
        for(Inspect_inform inform:list){
            Integer id = inform.getAdmin_id();
            Admin admin = null;
            if(id!=null && id!=0){
                admin = adminService.selectAdmin(id);
            }
            Integer typeID = inform.getType_id();
            Inspect_type type = null;
            if(typeID!=null && typeID!=0){
               type = typeService.selectById(typeID);
            }
            InspectView view = new InspectView(inform,type,admin,1);
            viewList.add(view);
        }
        result.setData(new PageView(viewList,pageInfo.getTotal()));
        return result;
    }

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
        Admin admin = null;
        if(inform!=null){
            type = typeService.selectById(inform.getType_id());
            admin = adminService.selectAdmin(inform.getAdmin_id());
        }
        result.setData(new InspectView(inform,type,admin,1));
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

class InspectView {
    public Object data;
    public Inspect_type type;
    public Admin admin;
    public int dataType;

    public InspectView(){}

    public InspectView(Object data, Inspect_type type, Admin admin,int dataType) {
        this.data = data;
        this.type = type;
        this.admin = admin;
        this.dataType = dataType;
    }
}

class PageView {
    public List<Object> list;
    public long total;

    public PageView(){}

    public PageView(List<Object> list, long total) {
        this.list = list;
        this.total = total;
    }
}