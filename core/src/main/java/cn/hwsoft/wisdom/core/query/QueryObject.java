package cn.hwsoft.wisdom.core.query;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QueryObject {

    private int pageSize = 15 ; //每页显示的条数
    private int currentPage = 1; // 当前页
    private int state = 1 ;
    private int type = -1;
    private String keyword; // 关键字搜索

}
