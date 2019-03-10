/**
 * Created by asus on 2016/5/26.
 */
$(function(){
    //统计建议数量
    StatisticsSuggestion();
    //获取建议数据
    ajaxPostInfo(1);
    //页面转换
    {
        $(".pagebtn").click(function(){
            var CurrentPage = Number($(".CurrentPage").html());
            if( $(this).val() == "previous" ) CurrentPage = CurrentPage - 1;
            else if( $(this).val() == "next" ) CurrentPage = CurrentPage + 1;
            // 删除原先的元素
            $("ul.list-group").find("li").remove();
            ajaxPostInfo( CurrentPage );
            return false;
        });
    }
    //删除用户建议
    $(".deletebtn").click(function(){
        var json = "";
        var Mysize = $("input[type='checkbox']:checked").length;
        if( Mysize<=0 ){
            swal(
                {   title: "请选择要删除的用户建议",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
        }else{
            $("input[type='checkbox']:checked").each(function (n,value) {
                if( n+1<Mysize ){
                    json = json+$(this).val()+"@";
                }else{
                    json = json+$(this).val();
                }
            });
            json = "{'suggestId':'"+json+"'}";
            ajaxDeleteInfo(json);
        }
    });
    //回复用户意见
    $(".replayAction").click(function(){
        var textarea = $("#textarea1").val().trim();
        var Id = $(".suggestId").text();
        var json = Id+"@"+textarea;
        json = "{'replay':'"+json+"'}";
        ajaxReplayInfo(json);
    });
});
function StatisticsSuggestion(){
    json = "{'StatisticsSuggestion':'true'}";
    // console.log(json);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Comments_StatisticsSuggestion.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                $(".mail-navigation").find("li:eq(0)").find("span").text(jsonObj.SuggestionForm1);
                $(".mail-navigation").find("li:eq(1)").find("span").text(jsonObj.SuggestionForm2);
                $(".mail-navigation").find("li:eq(2)").find("span").text(jsonObj.SuggestionForm3);
                $(".mail-navigation").find("li:eq(3)").find("span").text(jsonObj.SuggestionForm0);
            }else{
                swal("", "系统异常！", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function ajaxPostInfo( page ) {
    {
        var json = "{'page':'"+page+"'}";
        $.ajax({
            type : "post",
            url : "/PhotoShow/Comments_QueryForPageSuggest.action",
            // async : false, // 注意此处需要同步，
            dataType: 'json',//服务器返回的数据类型。如果不填写可以为xml,script,json,html。
            data : json,
            contentType : "application/json;charset=utf-8",
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                    // 更新页标
                    $(".CurrentPage").html(jsonObj.currentPage.toString().trim());
                    // 遍历相册
                    $.each(jsonObj.Suggestionboxs, function(n,value) {
                        insertElement(n,value);
                    });
                    $(".replay").click(function(){
                        $(".replayImg").attr("src",$(this).prev().children("img").attr("src"));
                        $(".replayId").text($(this).children("strong").find("span:eq(0)").text());
                        $(".replayName").text($(this).children("strong").find("span:eq(1)").text());
                        $(".replayUserSuggest").text($(this).next().text());
                        $(".suggestId").text($(this).prev().prev().children().val());
                    });
                }else{
                    swal(
                        {   title: "没有搜索到结果",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                }
            },
            error:function(data){
                swal(
                    {   title: "服务器异常",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "返回主页",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "index1.html";
                    }
                );
            }
        });
    }
}
function ajaxDeleteInfo( json ) {
    {
        $.ajax({
            type : "post",
            url : "/PhotoShow/Comments_DeleteUserSuggest.action",
            // async : false, // 注意此处需要同步，
            dataType: 'json',//服务器返回的数据类型。如果不填写可以为xml,script,json,html。
            data : json,
            contentType : "application/json;charset=utf-8",
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                    swal(
                        {   title: "删除成功",
                            type: "success",
                            confirmButtonText: "close"
                        }
                    );
                    // 删除原先的元素
                    $("ul.list-group").find("li").remove();
                    ajaxPostInfo( 1 );
                }else{
                    swal(
                        {   title: "删除失败，请稍后再试...",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                }
            },
            error:function(data){
                swal(
                    {   title: "服务器异常",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "返回主页",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "index.html";
                    }
                );
            }
        });
    }
}
function ajaxReplayInfo( json ) {
    {
        $.ajax({
            type : "post",
            url : "/PhotoShow/Comments_ReplayUserSuggest.action",
            // async : false, // 注意此处需要同步，
            dataType: 'json',//服务器返回的数据类型。如果不填写可以为xml,script,json,html。
            data : json,
            contentType : "application/json;charset=utf-8",
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                    swal(
                        {   title: "回复成功",
                            type: "success",
                            confirmButtonText: "close"
                        }
                    );
                    // 删除原先的元素
                    $("ul.list-group").find("li").remove();
                    ajaxPostInfo( 1 );
                }else{
                    swal(
                        {   title: "回复失败，请稍后再试...",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                }
            },
            error:function(data){
                swal(
                    {   title: "服务器异常",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "返回主页",
                        closeOnConfirm: false
                    }
                    // function(){
                    //     location.href = "index.html";
                    // }
                );
            }
        });
    }
}
function insertElement(n, value){
    var lable = new Array("label-warning", "label-success", "label-info", "label-danger", "label-primary");
    var li = '<li class="list-group-item"> ' +
                    '<span class="pull-left chk"> ' +
                        '<input type="checkbox" name="suggestId" value="'+value['id']+'"/> ' +
                    '</span> ' +
                    '<a class="thumb pull-left"> <img src="../imgs/'+value['userId']+'/'+value['cstext']+'"> </a> ' +
                    '<a class="replay" href="" data-toggle="modal" data-target="#myModal"> <span class="label Mylabel '+lable[Math.floor(n%5)]+' pull-right">回复</span><strong>ID:<span>'+value['userId']+'</span>&nbsp;<span>'+value['csId']+'</span></strong>&nbsp;<span>'+limitLength(value['sugtext'])+'</span></a> ' +
                    '<span style="display: none;">'+value['sugtext']+'</span>'
              '</li>';
    $("ul.list-group").append(li);
}
function limitLength( Content ){
    //   MAX=210
    var PLength = Content.length;
    if( PLength>=45 ){
        Content = Content.substring(0,42);
        Content = Content+"...";
    }
    return Content;
}