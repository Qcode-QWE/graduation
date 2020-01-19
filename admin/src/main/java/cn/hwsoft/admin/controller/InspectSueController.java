package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.admin.util.DownloadFileInfo;
import cn.hwsoft.admin.util.FileUtil;
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
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: court
 * @description: 军检控告
 * @author: QEcode
 * @create: 2019-07-23 12:08
 **/
@Controller
@RequestMapping("/inspect/sue")
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
    @AuthorityCheck("sue_view")
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo){
        JSONResult result = new JSONResult();
        PageInfo<Inspect_sue> pageInfo = sueService.list(qo);
        List<Inspect_sue> list = pageInfo.getList();
        //查询控告对应的user和处理的admin
        Map<Integer,User> userMap =  new HashMap<>();
        Map<Integer,Admin> adminMap =  new HashMap<>();
        for(Inspect_sue sue:list){
            User user = userService.selectUser(sue.getUser_id());
            Integer id = sue.getAdmin_id();
            Admin admin = null;
            if(id!=null && id!=0){
                admin = adminService.selectAdmin(id);
            }
            userMap.put(sue.getId(),user);
            adminMap.put(sue.getId(),admin);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sues",pageInfo);
        map.put("adminMap",adminMap);
        map.put("userMap",userMap);
        result.setData(map);
        return result;
    }

    /**
     * 获取控告类型
     * @return
     */
    @AuthorityCheck("sue_view")
    @GetMapping("/type")
    @ResponseBody
    public JSONResult selectType(){
        List<Inspect_type> list = typeService.typeList(1);
        JSONResult result = new JSONResult();
        result.setData(list);
        return result;
    }

    /**
     * 查询控告的信息和类型
     * @param id
     * @return
     */
    @AuthorityCheck("sue_view")
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult selectById(@PathVariable("id") int id){
        JSONResult result = new JSONResult();
        Inspect_sue sue = sueService.selectById(id);
        Inspect_type type = typeService.selectById(sue.getType_id());
        HashMap<String,Object> map = new HashMap<>();
        map.put("sue",sue);
        map.put("type",type);
        result.setData(map);
        return result;
    }

    /**
     * 修改处理状态
     * @param id
     * @param type
     * @return
     */
    @AuthorityCheck("sue_view")
    @PostMapping("/ps")
    @ResponseBody
    public JSONResult updateProcessStatus(int id, int type, HttpSession session){
        Inspect_sue sue = new Inspect_sue();
        Admin admin = (Admin) session.getAttribute("admin");
        sue.setId(id);
        sue.setProcess_status((byte)type);
        sue.setAdmin_id(admin.getId());
        sueService.updateProcessStatus(sue);
        JSONResult result = new JSONResult();
        return result;
    }
    /**
     * 通过表单的方式来下载文件
     * @param id
     * @return
     */
    @AuthorityCheck("sue_download")
    @PostMapping("/load")
    @ResponseBody
    public ResponseEntity<Resource> loadByPOST(int id){
        String zipFile = writeFile(id);
        DownloadFileInfo downloadFileInfo = FileUtil.downloadFileInfo(zipFile);
        return FileUtil.downloadResponse(downloadFileInfo);
    }

    /**
     * 批量下载
     * @param ids
     * @return
     */
    @AuthorityCheck("sue_download")
    @PostMapping("/loads")
    @ResponseBody
    public ResponseEntity<Resource> loadsByPOST(int[] ids){
        List<String> zipFiles = new ArrayList<>();
        for(int id:ids){
            zipFiles.add(writeFile(id));
        }
        int num = BaseUtils.createUUID();
        String zipFile =  FileUtil.zip(zipFiles,num,"zip");
        DownloadFileInfo downloadFileInfo = FileUtil.downloadFileInfo(zipFile);
        return FileUtil.downloadResponse(downloadFileInfo);
    }


    private String writeFile(int id){
        Inspect_sue sue = sueService.selectById(id);
        //解析出图片数组
        List<String> images = new ArrayList<>();
        String proof = sue.getProof();
        String[] files = proof.split(",");
        //将图片的地址改为本机的绝对地址
        for(String image:files){
            images.add(FileUtil.path(image));
        }
        //将申述标题和申述内容写入word文档中
        User user = new User();
        Inspect_type type = new Inspect_type();
        if(sue.getId()!=null){
            user = userService.selectUser(sue.getUser_id());
        }
        if(sue.getType_id()!=null){
            type = typeService.selectById(sue.getType_id());
        }
        String word = FileUtil.sueWord(sue,user,type);
        images.add(word);
        //保存文件为zip
        String zipFile = FileUtil.zip(images,sue.getReceipt(),null);
        return  zipFile;
    }


    /**
     * 通过a标签来下载zip文件
     * @param id
     * @return
     */
    @AuthorityCheck("sue_download")
    @GetMapping("/load/{id}")
    @ResponseBody
    public JSONResult loadByGET(@PathVariable("id") int id){
        String zipFile = writeFile(id);
        //将文件地址从本机地址转为网络地址
        zipFile = FileUtil.WebPath(zipFile);
        JSONResult result = new JSONResult();
        result.setData(zipFile);
        return result;
    }


}
