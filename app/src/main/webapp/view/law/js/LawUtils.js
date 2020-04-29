/**
 * Created by Lenovo on 2019/7/27.
 */

//留言管理操作栏的页面显示
//data:数据 , type:留言类型  1:法律咨询，2：法律援助，3：司法救助
function toolWindow(title,url,tag,tableResult) {
    layui.layer.open({
        type: 2,
        area: ['70%', '90%'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        success:function (layero,index) {
            var iframe = window['layui-layer-iframe' + index];
        },
        end:function () {
            //重载表格
            console.log("aaa");
            tableResult.reload({
                url:path+'/law/help/list.do',
                method: 'get',
                where:{
                    tag: tag
                },
                page:{
                    curr:1
                }
            });
            return false;
        }
        // , cancel: function(index, layero){
        //     layer.close(index);
        //     parent.location.reload();
        // }
    });
}

//法务学习操作栏页面显示
function toolWindowLibray(title,url,w,h,data,end) {
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
            iframe.mydata(data);
        },
        end:end
    });
}


function toolWindowForOnline(title,url,tag,tableResult) {
    layui.layer.open({
        type: 2,
        area: ['60%', '80%'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        success:function (layero,index) {
            console.log(layero, index);
            var iframe = window['layui-layer-iframe' + index];
        },
        end:function () {
            //重载表格
            console.log("aaa");
            tableResult.reload({
                url:path+'/law/help/video/list.do',
                method: 'get',
                where:{
                    tag: tag
                },
                page:{
                    curr:1
                }
            });
            return false;
        }
        // , cancel: function(index, layero){
        //     layer.close(index);
        //     parent.location.reload();
        // }
    });
}



function toolWindowForOnlineAdd(title,url) {
    layui.layer.open({
        type: 2,
        area: ['70%', '90%'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        success:function (layero,index) {
            console.log(layero, index);
            var iframe = window['layui-layer-iframe' + index];
        },
        end:function () {
            //重载表格
            console.log("aaa");
            tableResult.reload({
                url:path+'/law/help/video/list.do',
                method: 'get',
                where:{
                    tag: 0
                },
                page:{
                    curr:1
                }
            });
            return false;
        }
        // , cancel: function(index, layero){
        //     layer.close(index);
        //     parent.location.reload();
        // }
    });
}

