package cn.hwsoft.wisdom.core.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//结果封装类
@Getter
@ToString
@Setter
public class JSONResult {

    private boolean success = true;

    private Object data;
    private String errorMsg;

    public void mark(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }

    public static JSONResult success(Object data) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(data);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    public static JSONResult error(String errorMsg) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setErrorMsg(errorMsg);
        jsonResult.setSuccess(false);
        return jsonResult;
    }
}
