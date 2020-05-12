package cn.hwsoft.wisdom.core.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 咨询，援助，救助....分页查询分页类
 * Created by Lenovo on 2019/7/15.
 */
@Getter@Setter@ToString
public class LawQuery {
    private Integer userId;
    private String keyword;
    private int page=1;//页码
    private int limit=10;//每页显示数量
    private byte reply_mark=(byte)0;//表示留言是否回复  '0':表示显示所有 '1:此条信息待回复，2:此条信息已回复'
    private Byte tag;  //状态  '1:法律咨询，2：法律援助，3：司法救助'
}
