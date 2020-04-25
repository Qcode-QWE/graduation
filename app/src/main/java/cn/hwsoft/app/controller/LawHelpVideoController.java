package cn.hwsoft.app.controller;

import cn.hwsoft.wisdom.core.domain.Law_help_video_log;
import cn.hwsoft.wisdom.core.query.JSONResult;
import cn.hwsoft.wisdom.core.query.LawQuery;
import cn.hwsoft.wisdom.core.service.AdminService;
import cn.hwsoft.wisdom.core.service.LawHelpVideoService;
import cn.hwsoft.wisdom.core.service.UserService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台管理：咨询，援助，救助（列表查看，留言回复）
 * Created by Lenovo on 2019/7/15.
 */
@RestController
@RequestMapping("/law/help/video")
public class LawHelpVideoController {
    @Autowired
    private LawHelpVideoService lawHelpVideoService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @PostMapping
    public JSONResult createLawHelpLog(String request) {
        if (request != null && StringUtils.isEmpty(request)) {
            return JSONResult.error("title params must not null!");
        }

        Law_help_video_log lawHelpVideoLog = JSON.parseObject(request, Law_help_video_log.class);
        /**
         * todo 获取当前用户id，name
         */
        lawHelpVideoLog.setUserId(10001);
        lawHelpVideoLog.setUserName("陈小鱼");
        boolean b = lawHelpVideoService.createLawHelpLog(lawHelpVideoLog);
        return JSONResult.success(b);
    }

    @PutMapping
    public JSONResult updateLawHelpLog(String request) {
        Law_help_video_log lawHelpVideoLog = JSON.parseObject(request, Law_help_video_log.class);
        boolean b = lawHelpVideoService.updateLawHelpLog(lawHelpVideoLog);
        return JSONResult.success(b);
    }

    @PostMapping("/getRoomByUserId")
    public JSONResult getRoomByUserId(Integer userId){
        Law_help_video_log room  = lawHelpVideoService.getRoomByUserId(userId);
        return JSONResult.success(room);
    }

    @GetMapping("/list")  //分页显示
    public JSONResult list(LawQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Law_help_video_log> lawHelpVideoLogs = lawHelpVideoService.list(query);
        PageInfo<Law_help_video_log> pageInfo = new PageInfo<>(lawHelpVideoLogs);
        return JSONResult.success(pageInfo);
    }
}
