/**
 * Created by USER on 2019/7/15.
 */


//军检模块行操作栏的页面显示
//data:数据 , type:军检类型,1:举报,2:控告,3:申述
function toolWindow(title,url,w,h,data,type,end) {
    layui.layer.open({
        type: 2,
        area: ['70%', '80%'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        success:function (layero,index) {
            var iframe = window['layui-layer-iframe' + index];
            iframe.child(data,type);
        },
        end:end
    });
}


//查看用户
function  userWindow(title,url,w,h) {
    layui.layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: true, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url,
        end:function () {

        }
    });
}

//批量下载文件
function loadAll(obj,checkStatus,type,form) {
    var datas = checkStatus.data;
    if(datas.length==0){
        layer.confirm("请选择用户!");
        return;
    }
    var ids = [];
    var receipts = [];
    for (var i in datas){
        var id = datas[i].id;
        ids.push(id);
        receipts.push(datas[i].receipt);
    }

    // layer.confirm('确认要下载吗？',function(index){
    var $eleForm = $("<form method='post'></form>");
    for(var i in ids){
        var $input = $("<input name='ids'/>");
        $eleForm.append($input);
        $input.val(ids[i]);
    }
    $(document.body).append($eleForm);
    //构造表单
    var url = "";
    if (type==1){
        url = path+"/inspect/inform/loads.do";
    }else if (type==2){
        url = path+"/inspect/sue/loads.do";
    }else if (type==3){
        url = path+"/inspect/appeal/loads.do";
    }
    $eleForm.attr("action",url);
    //构造输入框
    //提交表单，实现下载
    $eleForm.submit();
    $eleForm.remove();
    /*$('table input[type=checkbox]').each(function(index, el) {
        if($(this).prop('checked')){
            $(this).prop('checked',false);
            console.log($(this));
        }
    });*/
    form.render();
    //});
}

function edit(title,url,w,h,obj,end) {
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: true, //固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        end:end
    });
}