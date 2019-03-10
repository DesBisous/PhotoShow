/**
 * Created by dairuihe on 2016/4/14.
 */
$(function () {
    $("#login").find(".modal-footer").find(".btn-primary").click(function () {
       login();
    });
    $("#login").keydown(function(e){
        if(e.keyCode==13){
            login();
            if (window.event.keyCode==13) window.event.keyCode=0 　　//这样就取消回车键了
            $(this).modal('hide');
        }

    });
    $(".Mycolse").click(function () {
        exitSession();
    });
    $("#personalWeb").click(function () {
        personalWeb();
    });
    // 进入我的服务记录是需要判断是否已登录,绑定点击事件
    $("#MyServiceRecord").click(function(){
        if( IsMyServiceRecord() == false ) {  return false;}
    });
    // 进入意见箱是需要判断是否已登录,绑定点击事件
    $("#MyServiceRecord").parent().prev().click(function(){
        if( IsSuggestionBox() == false ) {  return false;}
    });

    existSession();
});

function existSession() {
    var jsonObj;
    var url = "/PhotoShow/session_existSession.action";
    $.post(url, {}, function(data) {
        jsonObj=eval("("+data+")");
        console.log(jsonObj);
        if(jsonObj.state=="success"){
            $(".login").text(jsonObj.userId);
            $(".login").removeAttr("href");
            $(".login").removeAttr("data-target");
            $(".Mycolse").removeClass("Mydisplay-none");
            $(".register").addClass("Mydisplay-none");
        }
/*        if(jsonObj.state=="error"){
            location.href = "/index3.html";
        }*/
    }, "json");
}

function personalWeb() {
    var jsonObj;
    var url = "/PhotoShow/session_existSession.action";
    $.post(url, {}, function(data) {
        jsonObj=eval("("+data+")");
        if(jsonObj.state=="success"){
            location.href="PersonalWeb.html";
        }
        if(jsonObj.state=="error"){
            swal("", "请登录！", "error");
            // console.log(jsonObj);
        }
    }, "json");
}

function exitSession(){
    var jsonObj;
    var url = "/PhotoShow/session_exitSession.action";
    $.post(url, {}, function(data) {
        jsonObj=eval("("+data+")");
        console.log(jsonObj);
        if(jsonObj.state=="success"){
            location.href = "/index3.html";
        }
    }, "json");
}

function jsonStr(objectName, key, value) {
    var result = "{'" + objectName + "':{'";
    for ( var i in key) {
        if (i < key.length - 1)
            result += key[i] + "':'" + value[i] + "','";
        else
            result += key[i] + "':'" + value[i] + "'}}";
    }
    return result;
}

function login() {
    var key = new Array("userId","userPwd");
    var value = new Array($("#exampleInputUser").val(),$("#exampleInputPassword1").val());
    var json = jsonStr("loginForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/user_userAction_login.action",
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                $(".login").text(jsonObj.userId);
                $(".login").removeAttr("href");
                $(".login").removeAttr("data-target");
                $(".Mycolse").removeClass("Mydisplay-none");
                $(".register").addClass("Mydisplay-none");
                swal("", "登录成功", "success");
            }else {
                swal("", "登录信息有误！", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function IsMyServiceRecord(){
    var jsonObj;
    $.ajax({
        type : "post",
        url : "/PhotoShow/session_existSession.action",
        async : false, // 注意此处需要同步，
        success:function (data){
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                return true;
            }else{
                swal("", "请先登录后才能查看服务记录！", "error");
                return false;
            }
        }
    });
    if(jsonObj.state=="error"){
        return false;
    }
}
function IsSuggestionBox(){
    var jsonObj;
    $.ajax({
        type : "post",
        url : "/PhotoShow/session_existSession.action",
        async : false, // 注意此处需要同步，
        success:function (data){
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                return true;
            }else{
                swal("", "请先登录后才能进入意见箱！", "error");
                return false;
            }
        }
    });
    if(jsonObj.state=="error"){
        return false;
    }
}