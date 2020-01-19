/**
 * Created by USER on 2019/7/24.
 */
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};


//获取url中"?"符后的字串
function GetRequest() {
    var url = decodeURI(location.search); //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//  数据表格,头工具栏,批量删除
//获取所有选中行对象 obj=头工具栏对象
function getChecked(obj,table){
    obj.trObjs=[];
    var that={};
    that.elem=obj.config.elem.next();
    that.layBody = that.elem.find('.layui-table-body');
    that.key=obj.config.id;
    that.layBody.find('.layui-form-checked').each(function(){
        obj.trObjs.push(commonMember(that,$(this),null,table));
    });
    return obj.trObjs;
}
//数据行中的事件监听返回的公共对象成员
var commonMember = function(that,othis,sets,table){
    var ELEM_CELL = '.layui-table-cell';
    var index = othis.parents('tr').eq(0).data('index')
        ,tr = that.layBody.find('tr[data-index="'+ index +'"]')
        ,data = table.cache[that.key][index];
    return $.extend({
        tr: tr //行元素
        ,data: table.clearCacheKey(data) //当前行数据
        ,del: function(){ //删除行数据
            table.cache[that.key][index] = [];
            tr.remove();
            //that.scrollPatch();
        }
        ,update: function(fields){ //修改行数据
            fields = fields || {};
            layui.each(fields, function(key, value){
                if(key in data){
                    var templet, td = tr.children('td[data-field="'+ key +'"]');
                    data[key] = value;
                    table.eachCols(function(i, item2){
                        if(item2.field == key && item2.templet){
                            templet = item2.templet;
                        }
                    });
                    td.children(ELEM_CELL).html(function(){
                        return templet ? function(){
                            return typeof templet === 'function'
                                ? templet(data)
                                : laytpl($(templet).html() || value).render(data)
                        }() : value;
                    }());
                    td.data('content', value);
                }
            });
        }
    }, sets);
};

//弹窗显示信息
function message(text,type,layer) {
    layer.open({
        type: 0
        ,offset: 'auto'
        ,id: 'message' //防止重复弹出
        ,content: '<div style="padding: 20px 100px;">'+ text +'</div>'
        ,btn: '关闭'
        ,btnAlign: 'c' //按钮居中
        ,shade: 0 //不显示遮罩
        ,yes: function(index, layero){
            layer.close(index);
            if (type == 1){
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }
        }
    });
}


//判断值是否为空
function CheckIsNullOrEmpty(value) {
    var reg = /\s+/g//正则表达式用于判斷是否有空格或空字符串
    //reg.test(value)判斷是否有空格，有空格為true,如果首尾空格要求不嚴格的話可以加上trim(),如reg.test(value.trim()
    return (value != null && value != undefined && !reg.test(value))
}

function  hasAuthority(name) {
    var authorities = $.parseJSON(window.sessionStorage.authorities);
    var authorityList = $.parseJSON(window.sessionStorage.authorityList);
    for (var i in authorities){
        var auth1 = authorities[i];
        var auth2 = authorityList[i];
        var name1 = auth2["name"];
        if (name == name1 && auth1.status==1){
            return true;
        }
    }
    return false;
}

function getPath(){
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    if (result=="/view"){
        result = "";
    }
    return result;
}

function getIp() {
    var urlPath = window.document.location.href;  //浏览器显示地址 http://10.15.5.83:5555/ISV/demo.aspx
    var docPath = window.document.location.pathname; //文件在服务器相对地址 /ISV/demo.aspx
    index = urlPath.indexOf(docPath);
    var serverPath = urlPath.substring(0, index);//服务器地址 http://10.15.5.83:5555
    // return ip = "http://localhost:80";
    return serverPath;
}

var path = getPath();