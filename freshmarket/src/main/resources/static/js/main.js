//��ȡϵͳʱ��
var newDate = '';
getLangDate();
//ֵС��10ʱ����ǰ�油0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //��ʾ��ǰϵͳʱ���Date����
    var year = dateObj.getFullYear(); //��ǰϵͳʱ����������ֵ
    var month = dateObj.getMonth()+1; //��ǰϵͳʱ����·�ֵ
    var date = dateObj.getDate(); //��ǰϵͳʱ����·��е���
    var day = dateObj.getDay(); //��ǰϵͳʱ���е�����ֵ
    var weeks = ["������","����һ","���ڶ�","������","������","������","������"];
    var week = weeks[day]; //��������ֵ���������л�ȡ��Ӧ�������ַ���
    var hour = dateObj.getHours(); //��ǰϵͳʱ���Сʱֵ
    var minute = dateObj.getMinutes(); //��ǰϵͳʱ��ķ���ֵ
    var second = dateObj.getSeconds(); //��ǰϵͳʱ�������ֵ
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "����" : "����" : "����" ); //��ǰʱ���������硢���ϻ�������
    newDate = dateFilter(year)+"��"+dateFilter(month)+"��"+dateFilter(date)+"�� "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = "�װ��������o��"+timeValue+"�ã� ��ӭʹ��layuiCMS 2.0ģ�档��ǰʱ��Ϊ�� "+newDate+"��"+week;
    setTimeout("getLangDate()",1000);
}

layui.use(['form','element','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
        $ = layui.jquery;
    //�ϴε�¼ʱ�䡾�˴�Ӧ�ôӽӿڻ�ȡ��ʵ��ʹ���������и�����
    $(".loginTime").html(newDate.split("��")[0]+"��</br>"+newDate.split("��")[1]);
    //icon����
    $(".panel a").hover(function(){
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    },function(){
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function(){
        parent.addTab($(this));
    })
    //ϵͳ��������
    if(window.sessionStorage.getItem("systemParameter")){
        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        fillParameter(systemParameter);
    }else{
        $.ajax({
            url : "../json/systemParameter.json",
            type : "get",
            dataType : "json",
            success : function(data){
                fillParameter(data);
            }
        })
    }
    //������ݷ���
    function fillParameter(data){
        //�ж��ֶ������Ƿ����
        function nullData(data){
            if(data == '' || data == "undefined"){
                return "δ����";
            }else{
                return data;
            }
        }
        $(".version").text(nullData(data.version));      //��ǰ�汾
        $(".author").text(nullData(data.author));        //��������
        $(".homePage").text(nullData(data.homePage));    //��վ��ҳ
        $(".server").text(nullData(data.server));        //����������
        $(".dataBase").text(nullData(data.dataBase));    //���ݿ�汾
        $(".maxUpload").text(nullData(data.maxUpload));    //����ϴ�����
        $(".userRights").text(nullData(data.userRights));//��ǰ�û�Ȩ��
    }

    //���������б�
    $.get("../json/newsList.json",function(data){
        var hotNewsHtml = '';
        for(var i=0;i<5;i++){
            hotNewsHtml += '<tr>'
                +'<td align="left"><a href="javascript:;"> '+data.data[i].newsName+'</a></td>'
                +'<td>'+data.data[i].newsTime.substring(0,10)+'</td>'
                +'</tr>';
        }
        $(".hot_news").html(hotNewsHtml);
        $(".userAll span").text(data.length);
    })

    //�û�����
    $.get("../json/userList.json",function(data){
        $(".userAll span").text(data.count);
    })

    //�ⲿͼ��
    $.get(iconUrl,function(data){
        $(".outIcons span").text(data.split(".icon-").length-1);
    })

})
