package cn.hwsoft.wisdom.core.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * 用于返回数据的类1
 * Created by Lenovo on 2019/7/17.
 */
@Setter@Getter@ToString
public class LawJsonResult<T> {
    private  boolean success = true;
    private String code="1";  //记录数据状态  正确的成功状态码0
    private String errorMsg;
    private Integer count; //数据总量
    private ArrayList<T> data=null;
    private Object obj;
    public  void mark(String errorMsg){
        this.success = false;
        this.errorMsg = errorMsg;
    }

}



