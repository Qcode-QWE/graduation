package cn.hwsoft.wisdom.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Law_help_video_log {
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