package cn.hwsoft.wisdom.core.domain;

import lombok.ToString;

@ToString
public class Admin_authority {
    private Integer id;

    private Integer admin_id;

    private Byte appeal_view;

    private Byte appeal_download;

    private Byte inform_view;

    private Byte inform_download;

    private Byte sue_view;

    private Byte sue_download;

    private Byte user_deal;

    private Byte help_reply;

    private Byte status;

    private Integer create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Byte getAppeal_view() {
        return appeal_view;
    }

    public void setAppeal_view(Byte appeal_view) {
        this.appeal_view = appeal_view;
    }

    public Byte getAppeal_download() {
        return appeal_download;
    }

    public void setAppeal_download(Byte appeal_download) {
        this.appeal_download = appeal_download;
    }

    public Byte getInform_view() {
        return inform_view;
    }

    public void setInform_view(Byte inform_view) {
        this.inform_view = inform_view;
    }

    public Byte getInform_download() {
        return inform_download;
    }

    public void setInform_download(Byte inform_download) {
        this.inform_download = inform_download;
    }

    public Byte getSue_view() {
        return sue_view;
    }

    public void setSue_view(Byte sue_view) {
        this.sue_view = sue_view;
    }

    public Byte getSue_download() {
        return sue_download;
    }

    public void setSue_download(Byte sue_download) {
        this.sue_download = sue_download;
    }

    public Byte getUser_deal() {
        return user_deal;
    }

    public void setUser_deal(Byte user_deal) {
        this.user_deal = user_deal;
    }

    public Byte getHelp_reply() {
        return help_reply;
    }

    public void setHelp_reply(Byte help_reply) {
        this.help_reply = help_reply;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }
}