package cn.hwsoft.wisdom.core.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//结果封装类
@Getter @ToString
@Setter
public class JSONResult{

    private  boolean success = true;

    private Object data;
    private String errorMsg;

    public  void mark(String errorMsg){
        this.success = false;
        this.errorMsg = errorMsg;
    }
}
