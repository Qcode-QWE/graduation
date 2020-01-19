package cn.hwsoft.wisdom.core.domain;
import lombok.ToString;

@ToString
public class Admin_authoritys {
    private Integer id;

    private Integer admin_id;

    private Integer authoruth_id;

    private Byte status;

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

    public Integer getAuthoruth_id() {
        return authoruth_id;
    }

    public void setAuthoruth_id(Integer authoruth_id) {
        this.authoruth_id = authoruth_id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}