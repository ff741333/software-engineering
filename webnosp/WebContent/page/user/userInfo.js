var form, $,areaData;
layui.config({
    base : "../../js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    form = layui.form;
    $ = layui.jquery;
    use = layui.$;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        max : 0,
    });

    // //获取省信息
    // address.provinces();

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        use.ajax({
            url:'../../changecustomer'
            ,dataType:'text'
            ,data:{
                idUser:sessionStorage.getItem('user'),
                sex:data.field.sex,
                numPhone:$(".userPhone").val(),
                name:$(".realName").val(),
                birthday:$(".userBirthday").val(),
            }
            ,type:'post'
            ,success:function (data) {
                if(data==1){
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("提交成功！");
                    },2000);
                }
                else alert("提交失败！");
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})