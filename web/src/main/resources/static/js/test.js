layui.use(['layer', 'form', 'element', 'jquery', 'table', 'laydate'], function () {
    var form = layui.form, element = layui.element, $ = layui.$, layer = layui.layer,
        table = layui.table, laydate = layui.laydate;
    /**
     * 数据表格:定义表头数据
     * @type {{}}
     */
    var lan = {};
    lan.cpbm = "菜品编号";
    // lan.cpjm = "产品简码";
    lan.cpmc = "菜品名称";
    lan.ckjj = "价格";
    // lan.lpbz = "礼品标志";
    lan.spbh = "菜品编号";
    lan.spmc = "菜品名称";
    lan.sl = "数量";
    lan.dj = "单价";
    lan.je = "金额";
    lan.cz = "操作";
    /**
     *得到数据库中food表的数据
     * showfood是servlet
     */
    function showfood(){
        $.ajax({
            url:'login',
            // data:data.field,
            dataType:'text',
            type:'post',
            async:false,
            success: function (data) {
                result=data;
            }
        });
        return result;
    }
    /**
     * 购物车数据
     * @type {Array}
     */
    var goodsData = [];
    /**
     * 商品数据
     * @type {string}
     */
        // var GoodsDataStr = "[{\"FoodsID\":\"1\",\"FoodsName\":\"锅包肉\",\"NameCode\":\"guo\",\"XPrice\":55},{\"FoodsID\":\"20190301183528\",\"FoodsName\":\"测试2\",\"NameCode\":\"CS2\",\"GoodsClass\":\"14148378555485184\",\"GoodsType\":1,\"Price\":60,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":null,\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190301183538,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"次\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14148379235683328\",\"LAY_TABLE_INDEX\":1},{\"FoodsID\":\"20190301183516\",\"FoodsName\":\"测试1\",\"NameCode\":\"CS1\",\"GoodsClass\":\"14148378482019328\",\"GoodsType\":1,\"Price\":50,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":null,\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190301183522,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"次\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14148378983274496\",\"LAY_TABLE_INDEX\":2},{\"FoodsID\":\"6914068013626\",\"FoodsName\":\"纸巾\",\"NameCode\":\"zj\",\"GoodsClass\":\"13819788947571712\",\"GoodsType\":1,\"Price\":5,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":null,\"IsPoint\":1,\"PointType\":0.1,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":\"%3Cp%3Enull%3C/p%3E\",\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190217092044,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"盒\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14130846812542976\",\"LAY_TABLE_INDEX\":3},{\"FoodsID\":\"20190126103924660\",\"FoodsName\":\"测试呀1\",\"NameCode\":\"CSY1\",\"GoodsClass\":\"13659654677299200\",\"GoodsType\":1,\"Price\":20,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":\"/FileSys/MemPhoto/198/20190126103959902.jpg\",\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":\"\",\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190126103959,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"次\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14099782024565760\",\"LAY_TABLE_INDEX\":4},{\"FoodsID\":\"20190124202652\",\"FoodsName\":\"是是是\",\"NameCode\":\"SSS\",\"GoodsClass\":\"13659603389480960\",\"GoodsType\":1,\"Price\":11,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":1,\"Images\":null,\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190124202657,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"1\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14097527869279232\",\"LAY_TABLE_INDEX\":5},{\"FoodsID\":\"699\",\"FoodsName\":\"自助烤肉\",\"NameCode\":\"ZZKR\",\"GoodsClass\":\"13819785158099968\",\"GoodsType\":1,\"Price\":50,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":50,\"Images\":\"/FileSys/MemPhoto/198/20190114153729870.jpg\",\"IsPoint\":1,\"PointType\":800,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":0,\"ExchangePoint\":0,\"CreateTime\":20190114153730,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":null,\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14083087561366528\",\"LAY_TABLE_INDEX\":6},{\"FoodsID\":\"20190114153056\",\"FoodsName\":\"积分衣服\",\"NameCode\":\"JFYF\",\"GoodsClass\":\"13819785158099968\",\"GoodsType\":1,\"Price\":0,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":\"/FileSys/GoodsIMG/198/20190114154132974.png\",\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":\"%3Cp%3Enull%3C/p%3E\",\"IsDelete\":0,\"IsGift\":1,\"ExchangePoint\":500,\"CreateTime\":20190114153221,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"1\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14083082487470080\",\"LAY_TABLE_INDEX\":7},{\"FoodsID\":\"20190114152247\",\"FoodsName\":\"积分裤子\",\"NameCode\":\"JFKZ\",\"GoodsClass\":\"13819785158099968\",\"GoodsType\":1,\"Price\":0,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":null,\"IsPoint\":1,\"PointType\":800,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":1,\"ExchangePoint\":0,\"CreateTime\":20190114152321,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"1\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14083073653020672\",\"LAY_TABLE_INDEX\":8},{\"FoodsID\":\"20190114151536\",\"FoodsName\":\"裤子\",\"NameCode\":\"KZ\",\"GoodsClass\":\"13819785158099968\",\"GoodsType\":1,\"Price\":0,\"PriceUnit\":0,\"PriceNum\":0,\"XPrice\":0,\"Images\":null,\"IsPoint\":0,\"PointType\":0,\"MinDiscount\":0,\"IsDiscount\":0,\"Remark\":null,\"IsDelete\":0,\"IsGift\":1,\"ExchangePoint\":500,\"CreateTime\":20190114151658,\"CompID\":198,\"ShopID\":\"237\",\"MasterID\":\"237\",\"MeasureUnit\":\"1\",\"Specials\":0,\"IsShelf\":0,\"FreightTemplateID\":null,\"Id\":\"14083067377571840\",\"LAY_TABLE_INDEX\":9}]";
        // var GoodsDataStr ="[{\"FoodsID\":\"1\",\"FoodsName\":\"锅包肉\",\"LAY_TABLE_INDEX\":1,\"XPrice\":55}]";
    var GoodsDataStr = showfood();
    console.log(GoodsDataStr);
    var GoodsData = JSON.parse(GoodsDataStr);
    /**
     * 页面加载时执行
     */
    $(function () {
        console.table(GoodsData);

        /**
         * List1数据表格渲染(商品列表)
         */
        var ListTable1 = table.render({
            data:GoodsData,
            elem: '#List1',
            cellMinWidth: 95,
            //height: 'full-190',
            height: '460',
            minheight:430,
            page: true,
            limit: 10,
            limits: [10, 20, 30, 40],
            done: function (res) {
                if (res.rows && res.rows.length == 1) {
                    var data = res.rows[0];

                    GoodsList_onDblClickRow(data);
                }
            },
            cols: [
                [
                    { field: 'FoodsID', title: lan.cpbm, align: 'left' },
                    { field: 'FoodsName', title: lan.cpmc, align: 'left' },
                    {
                        field: 'XPrice', title: lan.ckjj, align: 'right', templet: function (d) {
                            return "<span style='color:red'>￥" + d.XPrice + "</span>";
                        }
                    },
                    // {
                    //     field: 'IsGift', title: lan.lpbz, align: 'center', templet: function (d) {
                    //         var _fm = '<div class="padding_top4 padding_left5">';
                    //         _fm += d.IsGift == 1 ? '<i class="layui-icon layui-icon-ok" title=' + lan.lp + '></i>' : '<i class="layui-icon layui-icon-close" title=' + lan.flp + '></i>';
                    //         _fm += "</div>";
                    //         return _fm;
                    //     }
                    // }
                ]
            ]
        });

        /**
         * List2数据表格渲染(购物车)
         */
        var ListTable2 = table.render({
            data:goodsData,
            elem: '#List2',
            cellMinWidth: 95,
            //height: 'full-190',
            height: '460',
            minheight:430,
            page: true,
            limit: 10,
            limits: [10, 20, 30, 40],
            cols: [
                [
                    { field: 'FoodsID', title: lan.spbh },
                    { field: 'FoodsName', title: lan.spmc },
                    { field: 'Qty', title: lan.sl, edit: 'text' },
                    { field: 'Price', title: lan.dj, edit: 'text' },
                    { field: 'Money', title: lan.je },
                    {
                        title: lan.cz, align: "center", fixed: 'right', templet: function (d) {
                            var html = '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delet">删除</a> ';
                            return html;
                        }
                    }
                ]
            ],
            done: function (res) {
                $("#List2").siblings().find("td").each(function () {
                    if ($(this).attr("data-edit") == 'text') {
                        var num = $(this).find("div").text();
                        $(this).append('<input class="layui-input layui-table-edit" value="' + num + '">')
                    }
                })
            }

        });

        /**
         * 监听商品列表'行'单击事件
         */
        table.on('row(List1)', function (obj) {
            var data = obj.data;
            var isNew = true;
            var vKey = data.FoodsID; //产品编码
            //遍历购物车，是否存在产品；存在，数量+1 计算金额
            for (var i = 0; i < goodsData.length; i++) {
                var tRow = goodsData[i];
                if (vKey == tRow.FoodsID) {
                    tRow.Qty = parseInt(tRow.Qty) + 1;
                    tRow.Money = accMul(tRow.Qty, tRow.Price,2);
                    isNew = false;
                    break;
                }
            }
            //判断当前数据是否为新增数据
            if (isNew) {
                var tmpRow = {};
                tmpRow.Id = data.Id;
                tmpRow.FoodsID = data.FoodsID;
                tmpRow.FoodsName = data.FoodsName;
                tmpRow.Price = data.XPrice;
                tmpRow.Qty = 1;
                tmpRow.Money =(data.XPrice).toFixed(2);
                tmpRow.SalesCode = "";
                goodsData.push(tmpRow);
            }
            //console.log(goodsData);

            /**
             * 开始刷新购物车
             */
            shoppingCartRefresh();
        });


        /**
         * 监听购物车行删除事件
         */
        table.on('tool(List2)', function (obj, index) {
            var data = obj.data;
            if (obj.event === 'delet') {
                for (var i = 0; i < goodsData.length; i++) {
                    var gd = goodsData[i];
                    if (data.FoodsID == gd.FoodsID) {
                        goodsData.splice(i, 1);
                    }
                }
                /**
                 * 开始刷新购物车
                 */
                shoppingCartRefresh();
            }
        });

        /**
         * 购物车行编辑事件
         */
        table.on('edit(List2)', function (obj) {
            var data = goodsData;
            for (var i = 0; i < data.length; i++) {
                if (obj.data.Id == data[i].Id) {
                    if (isNaN(data[i].Qty) || data[i].Qty < 0) {
                        data[i].Qty = 0;
                    }
                    if (isNaN(data[i].Price) || data[i].Price < 0) {
                        data[i].Price = 0;
                    }
                    data[i].Qty = Number(data[i].Qty); //数量:声明数字类型
                    data[i].Price = parseFloat(data[i].Price).toFixed(2); //金额:声明小数类型
                    data[i].Money = accMul(data[i].Qty, data[i].Price,2); //计算总金额:保留2位小数
                }
            }
            /**
             * 开始刷新购物车
             */
            shoppingCartRefresh();
        });


        /**
         * 购物车刷新函数
         */
        function shoppingCartRefresh(){
            var TotalQty = 0;
            var TotalMoney = 0;
            //数据表格重载
            ListTable2.reload({ data: goodsData });
            //计算总数 合计金额
            for (var i = 0; i < goodsData.length; i++) {
                TotalQty = accAdd(TotalQty, goodsData[i].Qty);
                TotalMoney = (accAdd(TotalMoney, parseFloat(goodsData[i].Money))).toFixed(2);
            }
            // console.log("总数量:"+TotalQty);
            // console.log("总金额:"+TotalMoney);
            $("#RechargeCount_TotalQty").html(TotalQty);
            $("#RechargeCount_TotalMoney").html(TotalMoney);
        }

        /**
         * 小数加法
         * @param arg1
         * @param arg2
         * @returns {number}
         */
        function accAdd(arg1,arg2){
            var r1,r2,m;
            try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
            try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
            m=Math.pow(10,Math.max(r1,r2));
            return (arg1*m+arg2*m)/m;
        }
        /**
         * 小数乘法
         * @param arg1
         * @param arg2
         * @param fix
         * @returns {*}
         */
        function accMul(arg1,arg2,fix) {
            if(!parseInt(fix)==fix)
            {
                return;
            }
            var m=0,s1=arg1.toString(),s2=arg2.toString();
            try{m+=s1.split(".")[1].length}catch(e){}
            try{m+=s2.split(".")[1].length}catch(e){}
            if(m>fix){
                return (Math.round(Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m-fix))/Math.pow(10,fix));
            }else if(m<=fix){
                return (Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)).toFixed(fix);
            }else{
                return (arg1*arg2).toFixed(fix);
            }
        }

    });

});