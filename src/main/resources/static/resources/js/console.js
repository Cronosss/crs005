"use strict";
layui.use(["okUtils", "table", "countUp", "okMock","carousel"], function () {
    var countUp = layui.countUp;
    var table = layui.table;
    var okUtils = layui.okUtils;
    var okMock = layui.okMock;
    var $ = layui.jquery;

    var carousel = layui.carousel;

    var ins ;
    //建造实例
    ins = carousel.render({
        elem: '#carouselView'
        , width: '100%' //设置容器宽度
        , height: '300px' //设置容器高度
        , interval: 3000
        ,arrow: 'hover' //切换箭头默认显示状态||不显示：none||悬停显示：hover||始终显示：always
        // , full:'true' //是否全屏轮播,默认false
        // , arrow: 'always' //始终显示箭头和点击按钮
        // ,anim: 'updown' //切换动画方式，可从各个方向滚动
    });

    /**
     * 车辆、人数
     */
    function statText() {
        var elem_nums = $(".stat-text");
        $.get("/console/initConInfo",function (obj) {
            elem_nums.each(function (i, j) {
                !new countUp({
                    target: j,
                    endVal: obj.data[i]
                }).start();
            });
        });
    }

    function getShowCarImg() {
        $.get("/console/getNewCarInfo",function (obj) {
            $.each(obj.data,function (index,item) {
                var str = '<div>\n' +
                    '         <img style="width: 99%;height: 300px;margin: 0px 5px" src="/upload/downloadPhoto?type=car&path='+item+'" />\n' +
                    '      </div>';
                $('#showNewCar').append(str);
            });
            ins.reload({
                elem: '#carouselView'
                , width: '100%' //设置容器宽度
                , height: '300px' //设置容器高度
                , interval: 3000
                ,arrow: 'hover' //切换箭头默认显示状态||不显示：none||悬停显示：hover||始终显示：always
            });
        });
    }

    function getNewArticle() {
        //最新公告
        $.get("/console/getNewArticle",function(data){
            $.each(data.data,function (index,item) {
                //时间判断 小于一天要显示最新的标志
                if ((Math.floor((new Date().getTime() - new Date(item.createTime).getTime()) / 86400000) <= 1)) {
                    var str = '<tr onclick=lookArticleInfo('+item.articleId+')>'
                        +'<td align="left"><a href="javascript:;"> '+item.articleTitle+'</a>' +
                        '<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;"> New</i></td>'
                        +'<td align="right">'+item.createTime.substring(0,10)+'</td>'
                        +'</tr>';
                }else {
                    var str = '<tr onclick=lookArticleInfo('+item.articleId+')>'
                        +'<td align="left"><a href="javascript:;"> '+item.articleTitle+'</a></td>'
                        +'<td align="right">'+item.createTime.substring(0,10)+'</td>'
                        +'</tr>';
                }
                $('#newArticle').append(str);
            });
        });
    }

    function getRankingListUser() {
        //用户排行榜
        $.get("/console/getRankingListUser",function(data){
            var res = data.data;
            var str = '<tr>'
                +'<td style="text-align: center;"><a href="javascript:;"> '+res[0]+'</a><img style="height: 40px"/></td>'
                +'<td style="text-align: center;"><a href="javascript:;"> '+res[1]+'</a><img style="height: 40px"/></td>'
                +'<td style="text-align: center;"><a href="javascript:;"> '+res[2]+'</a><img style="height: 40px"/></td>'
                +'</tr>';
            $('#rankingListUser').append(str);
        });
    }

    function getRankingListCar() {
        //车辆排行榜
        $.get("/console/getRankingListCar",function(data){
            var res = data.data;
            var str ;
            $.each(res,function (index,item) {
                if(item==null){
                    str = '<td style="text-align: center;">无</td>' ;
                }else{
                    str = '<td style="text-align: center;"><img style="width: 70px;height: 40px" src="/upload/downloadPhoto?type=car&path='+item.carImg+'" /></td>';
                }
                $('#rankingListCar').append(str);
            });
        });
    }

    window.lookCarInfo =function lookCarInfo(id) {
        //向服务端发送删除指令
        $.get("/console/getCarInfo?id="+id,function(data) {
            var res = data.data;
            layer.open({
                type:1,
                title:"车辆详情",
                content:$("#carInfoShowDiv"),
                area:['720px','600px'],
                success:function () {
                    form.val("carInfo",res);
                    $("#nickName2").val(res.user.nickName);
                    $("#phone2").val(res.user.phone);
                    //获取图片路径
                    $("#carImgShow2").attr("src","/upload/downloadPhoto?path="+res.carImg+"&type=car");
                }
            });
        });
    }

    window.lookArticleInfo =function lookArticleInfo(id) {
        //向服务端发送删除指令
        $.get("/console/getArticleById?articleId="+id,function(data) {
            var res = data.data;
            layer.open({
                type:1,
                title:"查看公告",
                content:$("#showArticleDiv"),
                area:['600px','400px'],
                success:function () {
                    $("#articleTitle").html(res.articleTitle);
                    $("#articleContent").html(res.articleContent);
                    $("#sendName").html(res.sendName);
                    $("#createTime").html(res.createTime);
                }
            });
        });
    }

    //统计数据
    statText();
    //最新车辆
    getShowCarImg();
    //最新公告
    getNewArticle();
    //用户排行榜
    getRankingListUser();
    //车辆排行榜
    getRankingListCar();
});






