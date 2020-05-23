package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.Admin;
import cn.hwsoft.wisdom.core.domain.Law_help;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.mapper.Law_helpMapper;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.LawJsonResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.LawHelpService;
import cn.hwsoft.wisdom.core.service.UserService;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: court
 * @description: 留言记录的controller
 * @author: QEcode
 * @create: 2019-07-13 11:59
 **/
@RequestMapping("/law/help")
@Controller
public class LawHelpController {
    @Autowired
    private LawHelpService lawHelpService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private Law_helpMapper lawHelpMapper;

    /**
     * 查询当前用户的留言列表,按顺序
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JSONResult selectList(Integer userId, byte tag, QueryObject qo) {
        System.out.println(qo);
        //通过id和tag来查询留言记录
        PageInfo pageInfo = lawHelpService.selectList(userId, tag, qo);
        List list = pageInfo.getList();
        if (list.size() > 0 && list != null) {
            List<Map<String, Object>> resultList = CutLawHelpFields(list, keepFields, Law_help.class);
            pageInfo.setList(resultList);
        }
        JSONResult result = new JSONResult();
        result.setData(pageInfo);
        return result;
    }

    /**
     * 查询当前用户的全部留言列表,按顺序
     *
     * @return
     */
    @PostMapping("/allList")
    @ResponseBody
    public LawJsonResult selectAllList(LawQuery query, HttpSession session) {
        query.setTag((byte) 0);
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(user)) {
            return null;
        }
        LawJsonResult result = new LawJsonResult();
        //获取相关的uid
        Integer userId = user.getId();

        int sum = 0;  //记录条数
        sum = lawHelpMapper.selectCountByUser(query.getReply_mark(), (byte) 0, userId);
        result.setCount(sum);
        ArrayList<Law_help> resultData = new ArrayList<>();  //最终的结果
        query.setTag((byte) 1);
        Law_help help1 = lawHelpService.searchByUid(query, userId);
        query.setTag((byte) 2);
        Law_help help2 = lawHelpService.searchByUid(query, userId);
        query.setTag((byte) 3);
        Law_help help3 = lawHelpService.searchByUid(query, userId);
        if (Objects.nonNull(help1)) {
            resultData.add(help1);
        }
        if (Objects.nonNull(help2)) {
            resultData.add(help2);
        }
        if (Objects.nonNull(help3)) {
            resultData.add(help3);
        }

        //根据uid查找用户信息
        Map<Integer, User> userMap = new HashMap<>();
        for (Law_help law : resultData) {
            userMap.put(law.getUid(), user);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userMap", userMap);
        map.put("informs", resultData);
        result.setObj(map);
        return result;
    }

    @PostMapping("/search")  //查看留言记录
    @ResponseBody
    public JSONResult search(Law_help lawHelp, QueryObject qo) {
        //根据uid及tag查询所有留言记录
        PageInfo pageInfo = lawHelpService.searchByUids(lawHelp.getUid(), lawHelp.getReply_mark(), lawHelp.getTag(), qo);
        //根据留言记录查询用户姓名并封装
        List<Law_help> law_helps = pageInfo.getList();
        if (law_helps != null && law_helps.size() > 0) {
            List<Map<String, Object>> result = LawHelpToJsonObject(law_helps, Law_help.class);
            pageInfo.setList(result);
        }
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(pageInfo);
        return jsonResult;
    }

    /**
     * 保存留言记录
     *
     * @param lawHelp
     * @return
     */
    @PostMapping("/save")   //必须传递 content和 tag这两个参数
    @ResponseBody
    public JSONResult save(int userId, Law_help lawHelp) {
        // 一系列留言的uid
        lawHelp.setUid(userId);
        //根据uid查找最近最后一条留言记录
        LawQuery lawQuery = new LawQuery();
        lawQuery.setTag(lawHelp.getTag());
        Law_help law_help = lawHelpService.searchByUid(lawQuery, userId);
        // 设置留言的pid，如果用户曾经咨询过，则pid为最新的一条留言记录id，否则为0
        if (null != law_help) {
            lawHelp.setPid(law_help.getId());
        } else {
            lawHelp.setPid(0);
        }
        // 发送信息的用户id
        lawHelp.setFrom_user_id(userId);
        // 接收留言的用户id
        lawHelp.setTo_user_id(0);
        // 留言来自普通用户
        lawHelp.setFrom_mark((byte) 1);
        // 此条留言待回复
        lawHelp.setReply_mark((byte) 1);
        //留言的数据状态
        lawHelp.setStatus((byte) 1);
        //创建时间
        String date = TimeUtils.DateToTimeStamp(new Date());
        lawHelp.setCreate_time(Integer.valueOf(date));
        boolean temp = lawHelpService.saveUserLawHelp(lawHelp);
        JSONResult result = new JSONResult();
        if (temp) {
            return result;
        } else {
            result.setErrorMsg("数据保存失败！");
        }
        return result;
    }

    /**
     * 将LawHelp转为Map
     *
     * @param lawHelps
     * @param clazz
     * @return
     */
    public List<Map<String, Object>> LawHelpToJsonObject(List<Law_help> lawHelps, Class<Law_help> clazz) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        BeanInfo beanInfo = null;
        int userId = lawHelps.get(0).getUid();
        User user = userService.selectUser(userId);
        try {
            for (Law_help lawHelp : lawHelps) {
                Map<String, Object> map = new HashMap<String, Object>();
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
                if ((byte) map.get("from_mark") == 1) {
                    int adminId = (int) map.get("to_user_id");
                    Admin admin = adminService.selectAdmin(adminId);
                    if (admin != null) {
                        map.put("to_user_id", admin.getRealname());
                    } else {
                        map.put("to_user_id", "管理员");
                    }
                    map.put("from_user_id", user.getRealname());
                } else {
                    int adminId = (int) map.get("from_user_id");
                    Admin admin = adminService.selectAdmin(adminId);
                    if (admin != null) {
                        map.put("from_user_id", admin.getRealname());
                    }
                    map.put("to_user_id", user.getRealname());
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


    /**
     * 切割出需要传到前台的属性
     *
     * @param list
     * @param fields 需要传到前台的属性
     * @return
     */
    private String[] keepFields = {"from_user_id", "create_time", "from_mark", "content"};

    public List<Map<String, Object>> CutLawHelpFields(List<Law_help> list, String[] fields, Class<Law_help> clazz) {
        //将需要传到前台的属性转为map
        Set<String> set = new HashSet<>();
        for (String field : keepFields) {
            set.add(field);
        }
        List<Map<String, Object>> fieldsList = new ArrayList<>();
        int userId = list.get(0).getUid();
        User user = userService.selectUser(userId);
        // 切割出需要的属性
        try {
            for (Law_help lawHelp : list) {
                HashMap<String, Object> map = new HashMap<>();
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 过滤class属性
                    if (!key.equals("class") && set.contains(key)) {
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
                if ((byte) map.get("from_mark") == 1) {
                    if (user != null) {
                        map.put("from_user_id", user.getRealname());
                    } else {
                        map.put("from_user_id", "用户");
                    }

                } else {
                    int adminId = (int) map.get("from_user_id");
                    Admin admin = adminService.selectAdmin(adminId);
                    if (admin != null) {
                        map.put("from_user_id", admin.getRealname());
                    } else {
                        map.put("from_user_id", "管理员");
                    }
                }
                fieldsList.add(map);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fieldsList;
    }

    @PostMapping("/edit")   //回复留言
    @ResponseBody
    public JSONResult edit(HttpServletRequest request, Law_help lawHelp) {
        System.out.println(lawHelp);
        User user = (User) request.getSession().getAttribute("user");//获取管理人员的信息
        //留言内容
        String content = lawHelp.getContent();

        //在保存新的留言
        Law_help help = new Law_help();
        help.setPid(lawHelp.getId());   //设置留言父id
        help.setUid(lawHelp.getUid());
        help.setFrom_user_id(user.getId());
        help.setTo_user_id(0);
        help.setContent(content);
        help.setFrom_mark((byte) 1);
        help.setReply_mark((byte) 1);
        help.setStatus((byte) 1);
        help.setTag(lawHelp.getTag());
        Date date = new Date();
        String timeStamp = TimeUtils.DateToTimeStamp(date);
        help.setCreate_time(Integer.valueOf(timeStamp));

        lawHelpService.saveAdminLawHelp(help);
        //查出最新的留言记录
        //封装查询条件
        LawQuery lawQuery = new LawQuery();
        lawQuery.setTag(lawHelp.getTag());
        Law_help law_help = lawHelpService.searchByUid(lawQuery, lawHelp.getUid());
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(law_help);
        return jsonResult;
    }

    @PostMapping
    public JSONResult createLawHelpLog(String request, HttpSession session) {
        if (StringUtils.isEmpty(request)) {
            return JSONResult.error("content params must not null!");
        }

        Law_help lawHelp = JSON.parseObject(request, Law_help.class);
        if (StringUtils.isEmpty(lawHelp.getContent())) {
            return JSONResult.error("content params must not null!");
        }
        User user = (User) session.getAttribute("user");

        lawHelp.setFrom_user_id(user.getId());
        lawHelp.setUid(user.getId());
        lawHelp.setPid(0);
        String s = TimeUtils.DateToTimeStamp(new Date());
        lawHelp.setCreate_time(Integer.valueOf(s));
        boolean b = lawHelpService.createLawHelpLog(lawHelp);
        return JSONResult.success(b);
    }
}
