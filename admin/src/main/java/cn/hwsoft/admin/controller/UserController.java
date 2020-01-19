package cn.hwsoft.admin.controller;

import cn.hwsoft.admin.annotation.AuthorityCheck;
import cn.hwsoft.wisdom.core.domain.User;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.QueryObject;
import cn.hwsoft.wisdom.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @program: court
 * @description:
 * @author: QEcode
 * @create: 2019-07-14 09:21
 **/

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @AuthorityCheck("user_deal")
    @GetMapping("/{id}")
    @ResponseBody
    public JSONResult getUser(@PathVariable("id") Integer id){
        User user = userService.selectUser(id);
        JSONResult result = new JSONResult();
        result.setData(user);
        return result;
    }

    /**
     * 获取用户列表,分页,带条件
     * @param qo
     * @return
     */
    @AuthorityCheck("user_deal")
    @PostMapping("/list")
    @ResponseBody
    public JSONResult list(QueryObject qo,int searchType,int status){
        Map<String,Object> map = userService.selectList(qo,searchType,status);
        JSONResult result = new JSONResult();
        result.setData(map);
        return result;
    }

    /**
     * 删除指定用户
     * @param ids
     * @return
     */
    @AuthorityCheck("user_deal")
    @PostMapping("/del")
    @ResponseBody
    public JSONResult delete(@RequestParam("ids")int[] ids){
        JSONResult result = new JSONResult();
        if(ids.length>0){
            ArrayList<Integer> list = new ArrayList();
            for(int id:ids){
                list.add(id);
            }
            userService.deleteByids(list);
        }else {
            result.mark("");
        }
        return  result;
    }

    @AuthorityCheck("user_deal")
    @PostMapping("/edit")
    @ResponseBody
    public JSONResult update(User user){
        JSONResult result = new JSONResult();
        userService.update(user);
        result.setData(user);
        return result;
    }

    /**
     * 初始化密码
     * @param id
     * @return
     */
    @AuthorityCheck("user_deal")
    @PostMapping("/reset")
    @ResponseBody
    public JSONResult resetPassword(int id){
        userService.resetPassword(id);
        return  new JSONResult();
    }


}
