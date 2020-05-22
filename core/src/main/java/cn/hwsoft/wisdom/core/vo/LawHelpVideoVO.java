package cn.hwsoft.wisdom.core.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenjinpeng
 * @date 2020/04/23 09:02:11
 * @description 描述信息
 */
@Data
@Accessors(chain = true)
public class LawHelpVideoVO{

    private Integer id;

    private String room;

    private String title;

    private String question;

    private String url;

    private Integer userId;

    private String userName;

    private String adminName;

    private Integer adminId;

    private String content;

    private Integer replyMark;

    private Integer status;

    private Integer tag;

    private Integer createTime;
}