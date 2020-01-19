package cn.hwsoft.wisdom.core.domain;

import lombok.ToString;

@ToString
public class Law_help {
    private Integer id;

    private Integer uid;

    private Integer pid;

    private Integer from_user_id;

    private Integer to_user_id;

    private String content;

    private Byte from_mark;

    private Byte reply_mark;

    private Byte status;

    private Byte tag;

    private Integer create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(Integer from_user_id) {
        this.from_user_id = from_user_id;
    }

    public Integer getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Integer to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getFrom_mark() {
        return from_mark;
    }

    public void setFrom_mark(Byte from_mark) {
        this.from_mark = from_mark;
    }

    public Byte getReply_mark() {
        return reply_mark;
    }

    public void setReply_mark(Byte reply_mark) {
        this.reply_mark = reply_mark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTag() {
        return tag;
    }

    public void setTag(Byte tag) {
        this.tag = tag;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }
}