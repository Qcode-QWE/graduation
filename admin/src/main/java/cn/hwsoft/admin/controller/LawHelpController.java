package cn.hwsoft.admin.controller;

import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Law_help;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.LawHelpService;
import cn.hwsoft.wisdom.core.service.UserService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 后台管理：咨询，援助，救助（列表查看，留言回复）
 * Created by Lenovo on 2019/7/15.
 */
@Controller
@RequestMapping("/law/help")
public class LawHelpController {
    @Autowired
    private LawHelpService service;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/search")  //查看留言记录
    @ResponseBody
    public JSONResult search(Law_help lawHelp, QueryObject qo) {
        //根据uid及tag查询所有留言记录
        PageInfo pageInfo = service.searchByUids(lawHelp.getUid(), lawHelp.getReply_mark(), lawHelp.getTag(), qo);
        //根据留言记录查询用户姓名并封装
        List<Law_help> law_helps = pageInfo.getList();
        if(law_helps!=null && law_helps.size()>0){
            List<Map<String, Object>> result = LawHelpToJsonObject(law_helps, Law_help.class);
            pageInfo.setList(result);
        }
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(pageInfo);
        return jsonResult;
    }

    @GetMapping("/list")  //分页显示
    @ResponseBody
    public LawJsonResult list(LawQuery query) {
        LawJsonResult result = new LawJsonResult();
        //获取相关的uid
        List<Integer> list=service.getUids(query);

        int sum=0;  //记录条数
        sum=service.count(query.getReply_mark(),query.getTag());  //查询数据的总数
        result.setCount(sum);
        ArrayList<Law_help> resultData = new ArrayList<>();  //最终的结果

        for (Integer uid : list) {  //通过uid查询留言
            Law_help help = service.searchByUid(query, uid);
            resultData.add(help);
        }

        //根据uid查找用户信息
        Map<Integer, User> userMap = new HashMap<>();
        for (Law_help law : resultData) {
            User user=userService.selectUser(law.getUid());
            System.out.println(user);
            userMap.put(law.getUid(), user);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userMap", userMap);
        map.put("informs", resultData);
        result.setObj(map);
        return result;
    }


    @PostMapping("/edit")   //回复留言
    @ResponseBody
    public JSONResult edit(HttpServletRequest request, Law_help lawHelp) {
        System.out.println(lawHelp);
        Admin admin = (Admin) request.getSession().getAttribute("admin");//获取管理人员的信息
        //留言内容
        String content = lawHelp.getContent();

        //先更改原一系列留言的reply_mark
        service.updateById(lawHelp);

        //在保存新的留言
        Law_help help = new Law_help();
        help.setPid(lawHelp.getId());   //设置留言父id
        help.setUid(lawHelp.getUid());
//        help.setFrom_user_id(0);
        help.setFrom_user_id(admin.getId());
//        help.setFrom_user_id(1); //测试  整合要改
        help.setTo_user_id(lawHelp.getTo_user_id());
        help.setContent(content);
        help.setFrom_mark((byte) 2);
        help.setReply_mark((byte) 2);
        help.setStatus((byte) 1);
        help.setTag(lawHelp.getTag());
        Date date = new Date();
        String timeStamp = TimeUtils.DateToTimeStamp(date);
        help.setCreate_time(Integer.valueOf(timeStamp));

        service.saveAdminLawHelp(help);
        //查出最新的留言记录
        //封装查询条件
        LawQuery lawQuery=new LawQuery();
        lawQuery.setTag(lawHelp.getTag());
        Law_help law_help=service.searchByUid(lawQuery,lawHelp.getUid());
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(law_help);
        return jsonResult;
    }

    @PostMapping("/delete")  //删除留言
    @ResponseBody
    public String delete(Integer id) {
        boolean tag = service.delete(id);
        if (tag) {
            return "1";  //1 表示成功  0 表示删除失败
        } else {
            return "0";
        }
    }

    public List<Map<String, Object>> LawHelpToJsonObject(List<Law_help> lawHelps, Class<Law_help> clazz) {
        List<Map<String, Object>> list = new ArrayList<>();
        BeanInfo beanInfo = null;
        int userId = lawHelps.get(0).getUid();
        User user = userService.selectUser(userId);
        try {
            for (Law_help lawHelp : lawHelps) {
                Map<String, Object> map = new HashMap<>();
                beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(lawHelp);
                        if (null == value) {
                            map.put(key, "");
                        } else {
                            map.put(key, value);
                        }
                    }
                }
                //如果信息来自普通用户
                if((byte)map.get("from_mark") == 1){
                    //直接存储用户姓名
                    if(user!=null){
                        map.put("from_user_name",user.getRealname());
                    }else {
                        map.put("from_user_name","用户");
                    }

                }else{//如果信息来自管理员
                    //根据发送信息用户id查询管理员信息
                    Admin admin = adminService.selectAdmin((Integer) map.get("from_user_id"));
                    //存储用户姓名
                    if(admin!=null){
                        map.put("from_user_name", admin.getRealname());
                    }else {
                        map.put("from_user_name", "管理员");
                    }

                }
                list.add(map);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }
}
