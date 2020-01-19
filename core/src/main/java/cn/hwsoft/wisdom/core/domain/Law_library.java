package cn.hwsoft.wisdom.core.domain;

import lombok.ToString;

@ToString
public class Law_library {
    private Integer id;

    private Integer law_type_id;

    private String title;

    private String content;

    private Integer read;

    private Byte status;

    private Integer create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaw_type_id() {
        return law_type_id;
    }

    public void setLaw_type_id(Integer law_type_id) {
        this.law_type_id = law_type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
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